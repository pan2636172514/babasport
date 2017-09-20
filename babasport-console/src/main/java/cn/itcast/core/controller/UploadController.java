package cn.itcast.core.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

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
	
	//上传商品图片 批量
	@RequestMapping(value = "/upload/uploadPics.do")
	public @ResponseBody
	List<String> uploadPics(@RequestParam (required = false) MultipartFile[] pics)throws Exception{
		//创建URL集群
		List<String> urls = new ArrayList<>();
		for (MultipartFile pic : pics) {
			//实际 保存图片到分布式文件系统
			String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
			urls.add(Constants.IMG_URL + path);
		}
		//转成json
		return urls;
	}
	
	//富文本上传商品图片 批量
	@RequestMapping(value = "/upload/uploadFck.do")
	public void uploadFck(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		//可以接受任意文件和图片
		MultipartRequest mr = (MultipartRequest)request;
		//只有图片和文件 支持多张
		Map<String, MultipartFile> fileMap = mr.getFileMap();
		//遍历map
		Set<Entry<String,MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile pic = entry.getValue();
			//上传图片到fastDFS
			//实际 保存图片到分布式文件系统
			String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
			String url = Constants.IMG_URL + path;
			JSONObject jo = new JSONObject();
			jo.put("url", url);
			jo.put("error", 0);
			//返回路径
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jo.toString());
			
			
			
		}
	}
	
}
