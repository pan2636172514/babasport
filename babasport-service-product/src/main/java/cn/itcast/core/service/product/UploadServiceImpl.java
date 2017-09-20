package cn.itcast.core.service.product;

import org.springframework.stereotype.Service;

import cn.itcast.common.fdfs.FdfsUtils;

/**
 * 上传图片
 * @author lx
 *
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService {

	
	//实际 保存图片到分布式文件系统 
	public String uploadPic(byte[] pic,String name,long size) throws Exception{
		return FdfsUtils.uploadPic(pic, name, size);
		
	}
	//上传商品图片 
	
	
}
