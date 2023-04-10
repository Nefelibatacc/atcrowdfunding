package cn.edu.ccut.crowd.service.api;

import cn.edu.ccut.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Fengshi
 * @Date: 2023/04/06/12:57
 * @Description:
 */
public interface RoleService {

    /**
    * @Description: 分页功能的方法
    * @Param: [pageNum, pageSize, keyword]
    * @return: com.github.pagehelper.PageInfo<cn.edu.ccut.crowd.entity.Role>
    * @Author: Fengshi
    * @Date: 2023/4/10
    */
    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    /**
    * @Description: 保存模态框中新增的角色维护规则用户的方法
    * @Param: [role]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/10
    */
    void saveRole(Role role);

    /**
    * @Description: 角色维护更新用户信息的方法
    * @Param: [role]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/10
    */
    void updateRole(Role role);
}
