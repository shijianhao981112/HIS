package com.zhiyou100.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.model.ResponseObject;
import com.zhiyou100.service.AjaxService;
import com.zhiyou100.util.FastDFSUtil;

@Controller
public class AjaxController {

	 //案例一:用户注册
	 @Autowired
	 AjaxService as;
	
	/**
	 *	演示:异步 验证用户是否注册
	 * @throws IOException 
	 */
	@RequestMapping("/checkUser.do")
	public void checkUser(String usernameJsonkey,HttpServletResponse resp) throws IOException {
		System.out.println("获得ajax发送的数据: "+usernameJsonkey);
		String code = as.checkUser(usernameJsonkey);
		resp.getWriter().write(code);
	}
	
	// 升级:用户注册
	/**
	 * @ResponseBody 注解
	 * 可以使返回的java类对象变为json对象
	 * 前提是导入jar包:Jackson
	 * return resObj;{"code":"200","msg":"成功","obj":{"id":"1"}  }
	 */
	@RequestMapping("/checkUser2.do")
	@ResponseBody
	public ResponseObject checkUser(String usernameJsonkey){
		System.out.println("获得ajax发送的数据: "+usernameJsonkey);
		ResponseObject resObj=as.checkUser2(usernameJsonkey);
		return resObj;
		
	}
	// 案例二:输入病例号,异步回显用户信息
	@RequestMapping("/findUserById.do")
	@ResponseBody
	public ResponseObject findUserById(String id) {
		System.out.println("获得ajax发送的数据: "+id);
		
		ResponseObject obj = as.findUserById(id);
		return obj;
		
	}
	// 案例三:二级联动->选科室出对应医生
	@RequestMapping("/findDoctorBySectionId")
	@ResponseBody
	public ResponseObject findDoctorBySectionId(String sectionId) {
		System.out.println("获得ajax发送的数据: "+sectionId);
		
		ResponseObject obj = as.findDoctorBySectionId(sectionId);
		return obj;
		
	}
	
	
	//==========  图表 echarts 
	
	/*
	 * 跳转展示echarts的页面
	 */
	@RequestMapping("/toEchartsUI.do")
	public String toEchartsUI() {
		
		return "forward:/echarts.jsp";
			
	}
	@RequestMapping("/showEcharts.do")
	@ResponseBody
	public double[] showEcharts() {
		
		double[] month = {100.0,90.0,80.0,70.0,60.0,50.0,40.0};
		return month;
		
	}
	// 1.后台返回的是ArrayList,ArrayList中存储了double数组,经过@ResponseBody 返回到页面
	// 二维数组
	@RequestMapping("/showEcharts2.do")
	@ResponseBody
	public ArrayList<Double[]> showEcharts2() {
		ArrayList<Double[]> lists = new ArrayList<>();
		Double[] k1 = {100.0,90.0,80.0,70.0,60.0,50.0,40.0};
		Double[] k2 = {30.0,30.0,30.0,30.0,30.0,30.0,30.0};
		Double[] k3 = {80.0,80.0,80.0,80.0,80.0,80.0,80.0};
		Double[] k4 = {40.0,50.0,60.0,70.0,80.0,90.0,100.0};
		lists.add(k1);
		lists.add(k2);
		lists.add(k3);
		lists.add(k4);
		System.out.println(lists);
		return lists;
		
	}
	
	
// ===============================================
	/*
	 * ajax实现上传并回显
	 * (上传至服务器)
	 */
	@RequestMapping("/upload.do")
	@ResponseBody
	public ResponseObject ajaxUpload(MultipartFile img,HttpServletRequest request) throws IOException {
		// 1.获得上传的对象
		// 2. 获得最终上传的目的地路径(上传至服务器中当前项目下)
		String realPath = request.getServletContext().getRealPath("/upload");
		System.out.println(realPath);
		// 2.1 将最终目的文件夹创建出来
		File file = new File(realPath);
		// 判断该文件是否存在
		if(file.exists()) {
			// 不存在则创建出
			file.mkdir();
		}
		// 2.2 获得文件名
		/*
		 * 文件名重复时不能重复上传文件
		 */
		String fileName = img.getOriginalFilename();
		System.out.println(fileName);
		/*
		 * 获得文件后缀名
		 */
		String[] split = fileName.split("\\.");
		System.out.println(Arrays.toString(split));
		String suffix = split[1];
		// 以当前毫秒值为文件名
		long prefix = new Date().getTime();
		// 组装文件名
		String newFileName = prefix+"."+suffix;
		System.out.println("新的文件名 : "+newFileName);
		System.out.println("文件名 : "+fileName);
		// 2.3 确定上传路径
		File newFile = new File(file,newFileName);
		// 3. 上传
		FileUtils.writeByteArrayToFile(newFile, img.getBytes());
		/*
		 * /upload/xx.jpg
		 * 
		 *  <img src="${}/upload/xx.jpg">
		 */
//		request.setAttribute("path", "/upload/"+newFileName);
		String path = "/upload/"+newFileName;
		return new ResponseObject("200","成功",path);
		
	}
	/**
	 * ajax 上传页面回显
	 * 上传至FastDFS  
	 * 使用工具类
	 * @throws MyException 
	 */
	@RequestMapping("/uploadFastDFS.do")
	@ResponseBody
	public ResponseObject ajaxUpload2(MultipartFile img,HttpServletRequest request) throws IOException, MyException {
		ResponseObject obj = FastDFSUtil.upload(img);
		return obj;
	
	}
	
// 下载
	
	@RequestMapping("/download.do")
	@ResponseBody
	public void ajaxDownload(String fid,HttpServletResponse resp) throws IOException, MyException {
		System.out.println("Controller 接收id : "+fid);
		byte[] bytes = FastDFSUtil.download(fid);
		// 解决中文文件名乱码问题
		//String filename = URLEncoder.encode("用户信息表", "utf-8");
		// 浏览器响应下载弹框
		resp.setHeader("Content-disposition", "attachment;filename="+new Date().getTime()+".png");
		resp.setContentType("image/png");
		ServletOutputStream outputStream = resp.getOutputStream();
		outputStream.write(bytes);// 输出数据
		outputStream.flush();
		outputStream.close();
	
	}
		
	
	
	
	
	
}
