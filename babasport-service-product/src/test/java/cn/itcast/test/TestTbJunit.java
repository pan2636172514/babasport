package cn.itcast.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.core.dao.TestTbDao;
import cn.itcast.core.pojo.TestTb;
import cn.itcast.core.service.TestTbService;

/**
 * 测试数据库类
 * @author 潘金鑫
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestTbJunit {
	
	@Autowired
//	private TestTbDao testTbDao;
	private TestTbService testTbService;
	
	@Test
	public void testAdd() throws Exception{
		TestTb testTb  = new TestTb();
		testTb.setName("范冰冰");
		testTb.setBirthday(new Date());
		testTbService.insertTestTb(testTb);
		
	}
	
}
