package cn.edu.ccut.crowd.mvc.controller;

import cn.edu.ccut.crowd.entity.Auth;
import cn.edu.ccut.crowd.entity.Role;
import cn.edu.ccut.crowd.service.api.AdminService;
import cn.edu.ccut.crowd.service.api.AuthService;
import cn.edu.ccut.crowd.service.api.RoleService;
import cn.edu.ccut.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Fengshi
 * @Date: 2023/04/14/14:31
 * @Description: 权限分配
 */
@Controller
public class AssignController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    /**
    * @Description: 权限分配-权限页面查询
    * @Param: [adminId, modelMap]
    * @return: java.lang.String
    * @Author: Fengshi
    * @Date: 2023/4/14
    */
    @RequestMapping("/assign/to/assign/role/page.html")
    public String toAssignRolePage(

            @RequestParam("adminId") Integer adminId,

            ModelMap modelMap

    ){

        // 1、查询已分配角色
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);

        // 2、查询未分配角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);

        // 3、存入模型（本质上其实是request.setAttribute("attrName", attrValue); ）
        // (未分配和已分配后端查询时就分开)
        modelMap.addAttribute("assignedRoleList", assignedRoleList);
        modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);

        return "assign-role";
    }

    /**
    * @Description: 保存分配的角色
    * @Param: [adminId, pageNum, keyword, roleIdList]
    * @return: java.lang.String
    * @Author: Fengshi
    * @Date: 2023/4/16
    */
    @RequestMapping("/assign/do/role/assign.html")
    public String saveAdminRoleRelationship(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,
            // 我们允许用户在页面上取消所有已分配角色再提交表单，所以可以不提供roleIdList请求参数
            // 设置required = false表示这个请求参数不是必须的
            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList
    ){

        adminService.saveAdminRoleRelationship(adminId, roleIdList);

        return "redirect:/admin/get/page.html?pageNum=" +pageNum+ "&keyword=" +keyword;
    }

    /**
    * @Description: 查询所有的权限名称
    * @Param: []
    * @return: cn.edu.ccut.crowd.util.ResultEntity<java.util.List<cn.edu.ccut.crowd.entity.Auth>>
    * @Author: Fengshi
    * @Date: 2023/4/16
    */
    @ResponseBody
    @RequestMapping("/assign/get/all/auth.json")
    public ResultEntity<List<Auth>> getAllAuth(){

        List<Auth> authList = authService.getAll();

        return ResultEntity.successWithData(authList);
    }

    /**
    * @Description: 根据权限id获取分配的权限列表
    * @Param: [roleId]
    * @return: cn.edu.ccut.crowd.util.ResultEntity<java.util.List<java.lang.Integer>>
    * @Author: Fengshi
    * @Date: 2023/4/16
    */
    @ResponseBody
    @RequestMapping("/assign/get/assigned/auth/id/by/role/id.json")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(
            @RequestParam("roleId") Integer roleId){

        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);

        return ResultEntity.successWithData(authIdList);
    }

}
