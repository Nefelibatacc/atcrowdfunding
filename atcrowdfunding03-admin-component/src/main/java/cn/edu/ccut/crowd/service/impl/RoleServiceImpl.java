package cn.edu.ccut.crowd.service.impl;

import cn.edu.ccut.crowd.entity.Role;
import cn.edu.ccut.crowd.mapper.RoleMapper;
import cn.edu.ccut.crowd.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Fengshi
 * @Date: 2023/04/06/12:57
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleMapper roleMapper;

    /**
    * @Description: 开启PageHelper分页功能查询后返回
    * @Param: [pageNum, pageSize, keyword]
    * @return: com.github.pagehelper.PageInfo<cn.edu.ccut.crowd.entity.Role>
    * @Author: Fengshi
    * @Date: 2023/4/6
    */
    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        
        // 1、开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        
        // 2、执行查询
        List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
        
        // 3、封装为PageInfo对象返回
        return new PageInfo<>(roleList);
    }

    /**
    * @Description: 新增角色维护用户的service实现 不需要try/catch 因为有框架的异常映射实现
    * @Param: [role]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/10
    */
    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }
}
