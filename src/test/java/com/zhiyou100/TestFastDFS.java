package com.zhiyou100;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

/**
 * 
 * @author Administrator
 * @date 2019年9月23日
 * @desc测试FastDFS 的JavaAPI 上传: 下载: 删除..
 */
public class TestFastDFS {

	/*
	 * 1.依赖 /jar 
	 * 2. FastDFS 配置文件 
	 * 3. javaapi 
	 * 		1加载配置文件 
	 * 		2创建Tracker客户端
	 * 		3通过Tracker客户端得到Tracker对象
	 * 	    4通过Tracker得到Storage客户端 
	 * 		5创建文件属性存储对象 
	 * 		6通过客户端执行上传
	 */
	@Test
	public void upload() throws IOException, MyException {
		// 1. 加载配置文件
		// 绝对路径
		ClientGlobal.init("E:\\221-2\\A_Hospital\\src\\main\\resources\\fastdfs-client.properties");
		// 2.创建Tracker客户端
		TrackerClient trackerClient = new TrackerClient();
		// 3. 通过Tracker客户端得到Tracker对象
		TrackerServer connection = trackerClient.getConnection();
		// 4. 通过Tracker得到Storage客户端
		StorageClient1 storageClient1 = new StorageClient1(connection, null);

		// 5.创建文件属性存储对象
		NameValuePair[] list = new NameValuePair[1];
		list[0] = new NameValuePair("fileName", "11.jpg");

		// 6.通过客户端执行上传
		/*
		 * 参数1:要上传的文件地址 :绝对路径 参数2:要上传的文件类型 参数3:文件属性信息对象数组 返回值 : 存储在Storage中的地址
		 */
		String fid = storageClient1.upload_file1("C:\\Users\\Administrator\\Desktop\\11.jpg", "jpg", list);
		System.out.println("上传成功fid : " + fid);
		// group1/M00/00/00/wKhIgV2JdSqAZmLZAACKjdwlUbE796.jpg
	}

	
// ============================= 下载 =============================
	@Test
	public void download() throws IOException, MyException {
		String fid = "group1/M00/00/00/wKhIgV2JdSqAZmLZAACKjdwlUbE796.jpg";
		// 1. 加载配置文件
		// 绝对路径
		ClientGlobal.init("E:\\221-2\\A_Hospital\\src\\main\\resources\\fastdfs-client.properties");
		// 2.创建Tracker客户端
		TrackerClient trackerClient = new TrackerClient();
		// 3. 通过Tracker客户端得到Tracker对象
		TrackerServer connection = trackerClient.getConnection();
		// 4.通过Tracker得到Storage客户端
		StorageClient1 storageClient1 = new StorageClient1(connection, null);
		// 下载
		byte[] bytes = storageClient1.download_file1(fid);
		OutputStream out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\110.jpg");
		
		out.write(bytes);
		out.close();
		System.out.println("下载成功");
	}
//===================================== 删除 ========================================
	@Test
	public void delete() throws IOException, MyException {
		String fid = "group1/M00/00/00/wKhIgV2IqHOAJQCOAACKjdwlUbE308.jpg";
		// 1. 加载配置文件
		// 绝对路径
		ClientGlobal.init("E:\\221-2\\A_Hospital\\src\\main\\resources\\fastdfs-client.properties");
		// 2.创建Tracker客户端
		TrackerClient trackerClient = new TrackerClient();
		// 3. 通过Tracker客户端得到Tracker对象
		TrackerServer connection = trackerClient.getConnection();
		// 4.通过Tracker得到Storage客户端
		StorageClient1 storageClient1 = new StorageClient1(connection, null);
		// 删除
		storageClient1.delete_file1(fid);
		
		System.out.println("删除成功");
	}
	
}