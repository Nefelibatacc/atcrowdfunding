package cn.edu.ccut.crowd.service.impl;

import cn.edu.ccut.crowd.constant.CrowdConstant;
import cn.edu.ccut.crowd.entity.Admin;
import cn.edu.ccut.crowd.entity.AdminExample;
import cn.edu.ccut.crowd.exception.LoginAcctAlreadyInUseException;
import cn.edu.ccut.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import cn.edu.ccut.crowd.exception.LoginFailedException;
import cn.edu.ccut.crowd.mapper.AdminMapper;
import cn.edu.ccut.crowd.service.api.AdminService;
import cn.edu.ccut.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Override
    public void saveAdmin(Admin admin) {

        // 1、密码的加密
        String userPswd = admin.getUserPswd();
        userPswd = CrowdUtil.md5(userPswd);
        admin.setUserPswd(userPswd);

        // 2、生成创建时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
        admin.setCreateTime(createTime);

        try {
            // 3、执行保存
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();

            logger.info("异常的全类名：" +e.getClass().getName());

            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseException(CrowdConstant.LOGIN_COUNT_ACCT_ALREADY_IN_USE);
            }
        }
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1.根据登录账号查询admin对象
        //   创建AdminExample对象
        AdminExample adminExample = new AdminExample();

        //   创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();

        //   在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);

        //   调用AdminMapper的方法执行查询
        List<Admin> list = adminMapper.selectByExample(adminExample);

        // 2.查询Admin对象是否为空
        if (list == null || list.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        if (list.size() > 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = list.get(0);

        // 3.如果admin对象为空则抛出异常
        if (admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 4.如果admin对象不为null则将数据库密码从Admin对象中取出
        String userPswdDB = admin.getUserPswd();

        // 5.将表单提交的明文密码进行加密
        String userPswdForm = CrowdUtil.md5(userPswd);

        // 6.对密码进行比较
        if (!Objects.equals(userPswdDB, userPswdForm)) {

            // 7.如果比较结果是不一致则抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 8.如果一致则返回Admin对象
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        // 1、调用PageHelper的静态方法开启分页功能
        // 这里充分体现了PageHelper的“非侵入式”设计：原本要做的查询不必有任何的修改
        PageHelper.startPage(pageNum, pageSize);

        // 2、执行查询
        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);

        // 3、封装到PageInfo对象中
        return new PageInfo<>(list);
    }

    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void update(Admin admin) {
        try {
            // “Selective”表示有选择的更新，对于null值的字段不更新
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            e.printStackTrace();

            logger.info("异常的全类名：" +e.getClass().getName());

            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.LOGIN_COUNT_ACCT_ALREADY_IN_USE);
            }
        }
    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {

        // 旧数据如下：
        // adminId  roleId
        // 1          1 （要删掉的）
        // 1          2 （要删掉的）
        // 1          3
        // 1          4
        // 1          5
        // 新数据如下：
        // adminId  roleId
        // 1          3 （本来就有）
        // 1          4 （本来就有）
        // 1          5 （本来就有）
        // 1          6 （新）
        // 1          7 （新）
        // 为了简化操作，先根据adminId删除旧的数据，再根据roleIdList保存全部新的数据

        // 1、根据adminId删除旧的关联关系数据
        adminMapper.deleteOldRelationship(adminId);

        // 2、根据roleIdList和adminId保存新的关联关系
        if (roleIdList != null && roleIdList.size() > 0) {
            adminMapper.insertNewRelationship(adminId, roleIdList);
        }

    }
}
