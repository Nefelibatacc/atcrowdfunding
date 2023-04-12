package cn.edu.ccut.crowd.mvc.controller;

import cn.edu.ccut.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Fengshi
 * @Date: 2023/04/12/14:30
 * @Description:
 */
@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

}
