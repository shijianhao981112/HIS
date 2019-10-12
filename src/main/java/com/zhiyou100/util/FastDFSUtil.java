package com.zhiyou100.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.model.ResponseObject;

/**
 * 
 * @author Administrator
 * @date 2019年9月23日
 * @desc测试FastDFS 的JavaAPI 上传: 下载: 删除..
 */
public class FastDFSUtil {

	// 上传
	public static ResponseObject upload(MultipartFile img) throws IOException, MyException {
		// 获得文件名
		String fileName = img.getOriginalFilename();
		// 获得后缀
		String[] split = fileName.split("\\.");
		String suffix = split[1];

		// 1.加载配置文件
		// 绝对路径
		ClientGlobal.init("E:\\221-2\\A_Hospital\\src\\main\\resources\\fastdfs-client.properties");
		// 2.创建Tracker客户端
		TrackerClient trackerClient = new TrackerClient();
		// 3.通过Tracker客户端得到Tracker对象
		TrackerServer connection = trackerClient.getConnection();
		// 4.通过Tracker得到Storage客户端
		StorageClient1 storageClient1 = new StorageClient1(connection, null);
		// 5.创建文件属性存储对象
		NameValuePair[] list = new NameValuePair[1];
		list[0] = new NameValuePair("fileName", fileName);
		// 6.通过客户端执行上传
		// 参1 文件的地址 : 绝对路径 参2 文件类型 参3 文件属性对象数组

		String fid = storageClient1.upload_file1(img.getBytes(), suffix, list);
		System.out.println("上传成功 : " + fid);
		
		String path = "http://java2101:80/" + fid;
		Map<String,String> map = new HashMap<>();
		map.put("path", path);
		map.put("fid", fid);
		return new ResponseObject("200","成功",map);
	}
	
	// 下载
	public static byte[] download(String fid) throws IOException, MyException {
		
		// 1.加载配置文件
		// 绝对路径
		ClientGlobal.init("E:\\221-2\\A_Hospital\\src\\main\\resources\\fastdfs-client.properties");
		// 2.创建Tracker客户端
		TrackerClient trackerClient = new TrackerClient();
		// 3.通过Tracker客户端得到Tracker对象
		TrackerServer connection = trackerClient.getConnection();
		// 4.通过Tracker得到Storage客户端
		StorageClient1 storageClient1 = new StorageClient1(connection, null);
		
		byte[] bytes = storageClient1.download_file1(fid);
		
		return bytes;
	}
	
	
	
	
}