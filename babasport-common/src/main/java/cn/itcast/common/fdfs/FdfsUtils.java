package cn.itcast.common.fdfs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

/**
 * 上传图片
 * @author lx
 *
 */
public class FdfsUtils {
	
	
	public static String uploadPic(byte[] pic,String name,long size) throws Exception{
		
		ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
		
		ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());
		//连接Tracker服务器    
		TrackerClient trackerClient = new TrackerClient();
		//获取Storage的地址
		TrackerServer trackerServer = trackerClient.getConnection();
		//连接Storager服务器
		StorageClient1 storageClient1 = new StorageClient1();
		
		String ext = FilenameUtils.getExtension(name);
		
		NameValuePair[] meta_list = new NameValuePair[3];
		meta_list[0] = new NameValuePair("filename",name);
		meta_list[1] = new NameValuePair("fileext",ext);
		meta_list[2] = new NameValuePair("filesize",String.valueOf(size));
		//上传图片到Storage服务器 返回路径
		String path = storageClient1.upload_file1(pic, ext, meta_list);
//		http://192.168.200.128/
//		group1/M00/00/01/wKjIgFWOYc6APpjAAAD-qk29i78248.jpg
		return path;
	}

}
