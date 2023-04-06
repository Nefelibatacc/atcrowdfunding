package cn.edu.ccut.crowd.mapper;

import cn.edu.ccut.crowd.entity.Role;
import cn.edu.ccut.crowd.entity.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* @Description: MyBatis逆向工程生成的RoleMapper
* @Param:
* @return:
* @Author: Fengshi
* @Date: 2023/4/6
*/
public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleByKeyword(String keyword);
}