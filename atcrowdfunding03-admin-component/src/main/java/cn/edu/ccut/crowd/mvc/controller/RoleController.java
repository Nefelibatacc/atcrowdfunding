package cn.edu.ccut.crowd.mvc.controller;

import cn.edu.ccut.crowd.entity.Role;
import cn.edu.ccut.crowd.service.api.RoleService;
import cn.edu.ccut.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Fengshi
 * @Date: 2023/04/06/12:59
 * @Description: 角色维护的controller
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
    * @Description: 获取分页数据
    * @Param: [pageNum, pageSize, keyword]
    * @return: cn.edu.ccut.crowd.util.ResultEntity<com.github.pagehelper.PageInfo<cn.edu.ccut.crowd.entity.Role>>
    * @Author: Fengshi
    * @Date: 2023/4/6
    */
    @ResponseBody
    @RequestMapping("/role/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ){  // 因为是Ajax请求 所以返回的是ResultEntity
        
        // 调用service方法获取分页数据
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
        
        // 封装到ResultEntity对象中返回(如果上面的操作抛出异常，交给异常映射机制处理)
        return ResultEntity.successWithData(pageInfo);
    }

    @ResponseBody
    @RequestMapping("/role/save.json")
    public ResultEntity<String> saveRole(Role role){

        roleService.saveRole(role);

        return ResultEntity.successWithoutData();
    }

}
