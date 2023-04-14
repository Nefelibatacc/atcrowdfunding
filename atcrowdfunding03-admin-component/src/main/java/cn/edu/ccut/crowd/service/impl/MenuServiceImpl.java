package cn.edu.ccut.crowd.service.impl;

import cn.edu.ccut.crowd.entity.Menu;
import cn.edu.ccut.crowd.entity.MenuExample;
import cn.edu.ccut.crowd.mapper.MenuMapper;
import cn.edu.ccut.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Fengshi
 * @Date: 2023/04/12/14:29
 * @Description:
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
    * @Description: 见接口
    * @Param: []
    * @return: java.util.List<cn.edu.ccut.crowd.entity.Menu>
    * @Author: Fengshi
    * @Date: 2023/4/12
    */
    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    /**
    * @Description: 见接口
    * @Param: [menu]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/12
    */
    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    /**
    * @Description: 更新节点 要使用有选择的更新
    * @Param: [menu]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/14
    */
    @Override
    public void updateMenu(Menu menu) {

        // 由于pid没有传入，一定要使用有选择的更新，保证“pid”字段不会被置空
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
    * @Description: 根据id删除节点
    * @Param: [id]
    * @return: void
    * @Author: Fengshi
    * @Date: 2023/4/14
    */
    @Override
    public void removeMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
