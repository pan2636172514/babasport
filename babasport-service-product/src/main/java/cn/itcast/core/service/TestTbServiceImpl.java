package cn.itcast.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.core.dao.TestTbDao;
import cn.itcast.core.pojo.TestTb;

/**
 * 测试实现类
 * @author lx
 *
 */
@Service("testTbService")
@Transactional
public class TestTbServiceImpl implements TestTbService {

	@Autowired
	private TestTbDao testTbDao;
	//添加
	public void insertTestTb(TestTb testTb){
		
		testTbDao.insertTestTb(testTb);
		
//		throw new RuntimeException();
	}
}
