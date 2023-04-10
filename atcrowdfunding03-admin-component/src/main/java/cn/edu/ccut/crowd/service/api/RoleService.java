package cn.edu.ccut.crowd.service.api;

import cn.edu.ccut.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Fengshi
 * @Date: 2023/04/06/12:57
 * @Description:
 */
public interface RoleService {

    /**
    * @Description: 分页功能
    * @Param: [pageNum, pageSize, keyword]
    * @return: com.github.pagehelper.PageInfo<cn.edu.ccut.crowd.entity.Role>
    * @Author: Fengshi
    * @Date: 2023/4/10
    */
    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    /**
    * @Description: 保存模态框中新增的角色维护规则用户
    * @Param: [role]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/10
    */
    void saveRole(Role role);

    /**
    * @Description: 角色维护更新用户信息
    * @Param: [role]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/10
    */
    void updateRole(Role role);

    /**
    * @Description: 角色维护实现删除（单个或批量）的
    * @Param: [roleIdList]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/10
    */
    void removeRole(List<Integer> roleIdList);
}
