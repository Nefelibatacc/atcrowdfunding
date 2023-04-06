package cn.edu.ccut.crowd.test;

import cn.edu.ccut.crowd.entity.Admin;
import cn.edu.ccut.crowd.entity.Role;
import cn.edu.ccut.crowd.mapper.AdminMapper;
import cn.edu.ccut.crowd.mapper.RoleMapper;
import cn.edu.ccut.crowd.service.api.AdminService;
import cn.edu.ccut.crowd.service.api.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//在类上标记必要的注解，Spring整合JUnit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleMapper roleMapper;


    @Test
    public void roleSave(){
        for (int i = 0; i < 235; i++){
            roleMapper.insert(new Role(null, "role"+i));
        }
    }

    @Test
    public void test(){
        for (int i = 0; i < 238; i++){
            adminMapper.insert(new Admin(null, "loginAcct"+i, "userPswd"+i, "userName"+i, "email"+i, null));
        }
    }

    @Test
    public void testTX(){
        Admin admin = new Admin(null, "Jerry", "123456", "杰瑞", "jerry@qq.com", null);
        adminService.saveAdmin(admin);

        //throw new RuntimeException();
    }

    @Test
    public void testLog(){
        //1.获取Logger对象，这里传入的Class对象就是当前打印日志的类
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);

        //2.根据不同的日志级别打印日志
        logger.debug("Hello I am Debug level!!!");
        logger.debug("Hello I am Debug level!!!");
        logger.debug("Hello I am Debug level!!!");

        logger.info("Info Level");
        logger.info("Info Level");
        logger.info("Info Level");

        logger.warn("Warn level");
        logger.warn("Warn level");
        logger.warn("Warn level");

        logger.error("Error level");
        logger.error("Error level");
        logger.error("Error level");
    }

    @Test
    public void testInsertAdmin(){
        Admin admin = new Admin(null, "tom", "123123", "汤姆", "tom@qq.com", null);
        int count = adminMapper.insert(admin);

        // 如果在实际开发中 所有想查看数值的地方都使用sysout方式打印 会给项目上线运行带来问题
        // sysout本质是一个IO的操作 通常IO的操作是比较消耗性能的 如果项目中sysout很多 那么对性能影响就比较大了
        // 即使上线前专门花时间删除代码中的sysout 也很可能有遗漏 而且非常麻烦
        // 而如果使用日志系统 那么通过日志的级别就可以批量的控制信息的打印
        System.out.println("受影响的行数：" +count);

    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

}
