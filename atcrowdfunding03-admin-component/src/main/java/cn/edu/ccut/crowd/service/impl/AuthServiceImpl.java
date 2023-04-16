package cn.edu.ccut.crowd.service.impl;

import cn.edu.ccut.crowd.entity.Auth;
import cn.edu.ccut.crowd.entity.AuthExample;
import cn.edu.ccut.crowd.mapper.AuthMapper;
import cn.edu.ccut.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Fengshi
 * @Date: 2023/04/16/13:42
 * @Description:
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAll() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return authMapper.selectAssignedAuthIdByRoleId(roleId);
    }

    @Override
    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {

        // 1、获取roleId的值
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);

        // 2、删除旧的关联关系数据
        authMapper.deleteOldRelationship(roleId);

        // 3、获取authIdList
        List<Integer> authIdList = map.get("authIdArray");

        // 4、判断authIdArray是否有效
        if(authIdList != null || authIdList.size() > 0) {
            authMapper.insertNewRelationship(roleId, authIdList);
        }

    }
}
