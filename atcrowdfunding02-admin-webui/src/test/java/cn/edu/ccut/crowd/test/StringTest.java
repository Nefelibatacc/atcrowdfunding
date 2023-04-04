package cn.edu.ccut.crowd.test;

import cn.edu.ccut.crowd.util.CrowdUtil;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Fengshi
 * @Date: 2023/03/29/14:27
 * @Description:
 */
public class StringTest {

    @Test
    public void testMd5(){
        String source = "123123";
        String encoded = CrowdUtil.md5(source);
        System.out.println(encoded);
    }

}
