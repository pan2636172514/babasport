package cn.itcast.core.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.core.pojo.TestTb;
import cn.itcast.core.service.TestTbService;

/**
 * 后台管理系统
 * @author 潘金鑫
 *
 */
@Controller
public class CenterController {

	@Autowired
	private TestTbService testTbService;
	
	//测试入口
	@RequestMapping(value="/test/index.do")
	public String index(){
		TestTb testTb = new TestTb();
		testTb.setName("柳岩");
		testTb.setBirthday(new Date());
		testTbService.insertTestTb(testTb);
		return "index";
	}
	
}
