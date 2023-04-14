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

    /**
    * @Description: 保存新添加的子节点信息
    * @Param: [menu]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/12
    */
    void saveMenu(Menu menu);

    /**
    * @Description: 菜单维护-更新节点
    * @Param: [menu]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/14
    */
    void updateMenu(Menu menu);
}
