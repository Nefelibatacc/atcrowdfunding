package cn.edu.ccut.crowd.service.api;

import cn.edu.ccut.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdminService {

    /**
    * @Description: 保存管理员信息
    * @Param: [admin]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/4
    */
    void saveAdmin(Admin admin);

    /**
    * @Description: 显示管理员列表
    * @Param: []
    * @return: java.util.List<cn.edu.ccut.crowd.entity.Admin>
    * @Author: Fengshi
    * @Date: 2023/4/4
    */
    List<Admin> getAll();

    /**
    * @Description: 输入账号密码查询admin信息（登录方法）
    * @Param: [loginAcct, userPswd]
    * @return: cn.edu.ccut.crowd.entity.Admin
    * @Author: Fengshi
    * @Date: 2023/4/4
    */
    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    /**
    * @Description: 分页
    * @Param: [keyword, pageNum, pageSize]
    * @return: com.github.pagehelper.PageInfo<cn.edu.ccut.crowd.entity.Admin>
    * @Author: Fengshi
    * @Date: 2023/4/4
    */
    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    /**
    * @Description: 删除管理员信息
    * @Param: [adminId]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/4
    */
    void remove(Integer adminId);

    /**
    * @Description: 根据id查询管理员信息
    * @Param: [adminId]
    * @return: cn.edu.ccut.crowd.entity.Admin
    * @Author: Fengshi
    * @Date: 2023/4/4
    */
    Admin getAdminById(Integer adminId);

    /**
    * @Description: 更新管理员信息
    * @Param: [admin]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/4
    */
    void update(Admin admin);
}
