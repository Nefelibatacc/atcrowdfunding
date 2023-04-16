package cn.edu.ccut.crowd.service.impl;

import cn.edu.ccut.crowd.entity.Auth;
import cn.edu.ccut.crowd.entity.AuthExample;
import cn.edu.ccut.crowd.mapper.AuthMapper;
import cn.edu.ccut.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
