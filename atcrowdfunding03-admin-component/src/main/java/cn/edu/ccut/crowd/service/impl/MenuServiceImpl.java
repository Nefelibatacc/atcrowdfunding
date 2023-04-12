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
    * @Description: 
    * @Param: []
    * @return: java.util.List<cn.edu.ccut.crowd.entity.Menu>
    * @Author: Fengshi
    * @Date: 2023/4/12
    */
    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }
}
