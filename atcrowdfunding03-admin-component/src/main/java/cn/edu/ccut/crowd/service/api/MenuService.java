package cn.edu.ccut.crowd.service.api;

import cn.edu.ccut.crowd.entity.Menu;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Fengshi
 * @Date: 2023/04/12/14:28
 * @Description:
 */
public interface MenuService {

    /**
    * @Description: 获取所有节点
    * @Param: []
    * @return: java.util.List<cn.edu.ccut.crowd.entity.Menu>
    * @Author: Fengshi
    * @Date: 2023/4/12
    */
    List<Menu> getAll();

}
