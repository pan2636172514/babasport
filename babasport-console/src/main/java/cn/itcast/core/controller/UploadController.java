package cn.itcast.core.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import cn.itcast.common.web.Constants;
import cn.itcast.core.service.product.UploadService;

/**
 * 上传图片管理
 * @author lx
 *
 */
@Controller
public class UploadController {

	
	@Autowired
	private UploadService uploadService;
	
	//异步上传品牌的图片
	@RequestMapping(value = "/upload/uploadPic.do")
	public void uploadPic(MultipartFile pic,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		System.out.println(pic.getOriginalFilename());
		//实际 保存图片到分布式文件系统
		String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
		
		JSONObject jo = new JSONObject();
		jo.put("path", Constants.IMG_URL + path);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jo.toString());
	}
}
