package cn.edu.ccut.crowd.mvc.controller;

import cn.edu.ccut.crowd.entity.Admin;
import cn.edu.ccut.crowd.entity.ParamData;
import cn.edu.ccut.crowd.entity.Student;
import cn.edu.ccut.crowd.service.api.AdminService;
import cn.edu.ccut.crowd.util.CrowdUtil;
import cn.edu.ccut.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     *  @RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
     *  而最常用的使用请求体传参的无疑是POST请求了，所以使用@RequestBody接收数据时，一般都用POST方式进行提交。
     *  在后端的同一个接收方法里，@RequestBody与@RequestParam()可以同时使用，@RequestBody最多只能有一个，而@RequestParam()可以有多个。
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping("/send/compose/object.json")
    public ResultEntity<Student> testReceiveComposeObject(@RequestBody Student student, HttpServletRequest request){

        boolean judgeResult = CrowdUtil.judgeRequestType(request);

        logger.info("judgeResult="+judgeResult);

        logger.info(student.toString());

        //将查询到的student对象封装到ResultEntity里返回
        ResultEntity<Student> resultEntity = ResultEntity.successWithData(student);

        return resultEntity;
    }

    @ResponseBody
    @RequestMapping("/send/array/two.html")
    public String testReceiveArrayTwo(ParamData paramData){

        List<Integer> array = paramData.getArray();

        for (Integer number : array){
            System.out.println("number= " +number);
        }

        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/array/one.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array){

        for (Integer number : array){
            System.out.println("number= " +number);
        }

        return "success";
    }

    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap, HttpServletRequest request){

        boolean judgeResult = CrowdUtil.judgeRequestType(request);

        logger.info("judgeResult="+judgeResult);

        List<Admin> adminList = adminService.getAll();

        modelMap.addAttribute("adminList" ,adminList);

        /*String a = null;
        System.out.println(a.length());*/

        System.out.println(10/0);

        return "target";
    }

}
