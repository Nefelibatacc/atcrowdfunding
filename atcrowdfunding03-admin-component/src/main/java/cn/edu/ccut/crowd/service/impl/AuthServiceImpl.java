package cn.edu.ccut.crowd.service.impl;

import cn.edu.ccut.crowd.mapper.AuthMapper;
import cn.edu.ccut.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
