package cn.itcast.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.core.dao.product.ProductDao;
import cn.itcast.core.pojo.product.Product;
import cn.itcast.core.pojo.product.ProductQuery;

/**
 * 测试
 * @author lx
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestProduct {
	
	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testAdd() throws Exception {
		
		
//		Product p = productDao.selectByPrimaryKey(7L);
//		System.out.println(p);
		String name = "包";
		ProductQuery productQuery = new ProductQuery();
		productQuery.createCriteria().andBrandIdEqualTo(7L).andNameLike("%" + name +"%");
		List<Product> products = productDao.selectByExample(productQuery);
		for (Product product : products) {
			System.out.println(product);
		}
	}

}
