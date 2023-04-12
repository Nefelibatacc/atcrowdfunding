package cn.edu.ccut.crowd.mvc.controller;

import cn.edu.ccut.crowd.entity.Menu;
import cn.edu.ccut.crowd.service.api.MenuService;
import cn.edu.ccut.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

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

    /**
    * @Description: 获取所有节点，并将父节点与子节点组装起来(菜单维护页面显示树形结构)
    * @Param: []
    * @return: cn.edu.ccut.crowd.util.ResultEntity<cn.edu.ccut.crowd.entity.Menu>
    * @Author: Fengshi
    * @Date: 2023/4/12
    */
    @ResponseBody
    @RequestMapping("/menu/get/whole/tree")
    public ResultEntity<Menu> getWholeTree(){

        // 1、查询所有的menu对象
        List<Menu> menuList = menuService.getAll();

        // 2、声明一个变量用来存储找到的根节点
        Menu root = null;

        // 3、遍历menuList
        for (Menu menu : menuList){

            // 4、获取当前menu对象的pid属性值
            Integer pid = menu.getPid();

            // 5、检查pid是否为null
            if (pid == null){

                // 6、把当前正在遍历的这个menu对象赋值给root
                root = menu;

                // 7、停止本次循环，继续执行下一次循环
                continue;
            }

            // 8、如果pid不为null 说明当前节点有父节点，找到父节点就可以进行组装,建立父子关系
            for (Menu maybeFather : menuList) {

                // 9、获取maybeFather的id属性
                Integer id = maybeFather.getId();

                // 10、将子节点的pid和疑似父节点id进行比较
                if (Objects.equals(pid, id)) {

                    // 11、将子节点存入父节点的children集合
                    maybeFather.getChildren().add(menu);

                    // 12、找到即可停止运行循环
                    break;

                }

            }

        }

        // 13、将组装好的树形结构（也就是根节点对象）返回给浏览器
        return ResultEntity.successWithData(root);
    }


}
