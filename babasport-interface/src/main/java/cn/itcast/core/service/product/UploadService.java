package cn.itcast.core.service.product;

public interface UploadService {
	
	//实际 保存图片到分布式文件系统 
	public String uploadPic(byte[] pic,String name,long size) throws Exception;

}
