package cn.edu.ccut.crowd.service.api;

import cn.edu.ccut.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Fengshi
 * @Date: 2023/04/16/13:42
 * @Description:
 */
public interface AuthService {
    
    /**
    * @Description: 查询所有数据（权限数据名称）
    * @Param: []
    * @return: java.util.List<cn.edu.ccut.crowd.entity.Auth>
    * @Author: Fengshi
    * @Date: 2023/4/16
    */
    List<Auth> getAll();

    /**
    * @Description: 根据roleid查询authid
    * @Param: [roleId]
    * @return: java.util.List<java.lang.Integer>
    * @Author: Fengshi
    * @Date: 2023/4/16
    */
    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    /**
    * @Description: 保存角色到权限的关联关系
    * @Param: [map]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/16
    */
    void saveRoleAuthRelationship(Map<String, List<Integer>> map);
}
