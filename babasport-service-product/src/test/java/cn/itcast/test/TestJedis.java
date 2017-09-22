package cn.itcast.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestJedis {
	
	@Autowired
	private Jedis jedis;
	
	@Test
	public void testAdd() throws Exception {
		
		jedis.set("haha", "nihao");
		
		System.out.println(jedis.get("haha"));
		
		jedis.close();

	}

}
