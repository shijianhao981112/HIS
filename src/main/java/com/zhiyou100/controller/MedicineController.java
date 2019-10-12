package com.zhiyou100.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyou100.model.Drug;
import com.zhiyou100.service.MedicineService;

@Controller
public class MedicineController {
	
	@Autowired
	private MedicineService service;

	@RequestMapping("/medicine/index")
	public String findAllMedicine(Model model,@RequestParam Map<String,String> keywordMap,@RequestParam(defaultValue="1")int pageSize) {
		
		PageHelper.startPage(pageSize, 4);
		
		List<Drug> lists = service.findAll(keywordMap);
		PageInfo<Drug> pageInfo = new PageInfo<>(lists);
		model.addAttribute("lists", pageInfo.getList());
		model.addAttribute("keywordMap", keywordMap);
		model.addAttribute("page", pageInfo);
		
		return "/medicine/index";
		
	}
	
	// add
	@RequestMapping(value="/medicine/add",method=RequestMethod.GET)
	public String addMedicine(Model model) {
		long time = new Date().getTime();
		model.addAttribute("id", time);
		return "/medicine/add";
	}

	@RequestMapping(value = "/medicine/add", method = RequestMethod.POST)
	public String addMedicine(Drug drug, Model model, 
			@RequestParam("img") MultipartFile img,
			HttpServletRequest request) throws IOException {
	
		// 1.获得上传的对象
        // 2. 获得最终上传的目的地路径(上传至服务器中当前项目下)
        String realPath = request.getServletContext().getRealPath("/medicine");
        System.out.println("要上传的目的地路径: "+realPath);
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
        System.out.println("获得文件名: "+fileName);
        /*
         * 获得文件后缀名
         * 因为正则表达式原因,通过这种方式转义该.
         */
        String[] split = fileName.split("\\.");
        System.out.println(Arrays.toString(split));
        String suffix = split[1];
        // 以当前毫秒值为文件名
        long prefix = new Date().getTime();
        // 组装文件名
        String newFileName = prefix+"."+suffix;
        System.out.println("文件名 : "+fileName);
        System.out.println("新的文件名 : "+newFileName);
        // 2.3 确定上传路径
        File newFile = new File(file,newFileName);
        // 3. 上传
        FileUtils.writeByteArrayToFile(newFile, img.getBytes());
        /*
         * /upload/xx.jpg
         *
         *  <img src="${}/upload/xx.jpg">
         */
       // request.setAttribute("path", "/medicine/"+newFileName);
        
       
		drug.setDrug_url("/medicine/"+newFileName);
		
		service.insertDrug(drug);
		return "forward:/medicine/index";
	}
	
	// look
	@RequestMapping("/medicine/look")
	public String lookMedicine(Model model,String drug_num) {
		Drug drug = service.findMedicineById(drug_num);
		model.addAttribute("medicine", drug);
		return "/medicine/look";
	}
	
	//edit
	
	@RequestMapping(value="/medicine/edit",method=RequestMethod.GET)
	public String editMedicine(Model model,String drug_num) {
		Drug drug = service.findMedicineById(drug_num);
		model.addAttribute("medicine", drug);
		return "/medicine/edit";
	}
	@RequestMapping(value="/medicine/edit",method=RequestMethod.POST)
	public String editMedicine(Drug drug) {
		
		service.editMedicine(drug);
		
		return "forward:/medicine/index";
	}
	// delete
	@RequestMapping("/medicine/delete")
	public String deleteMedicine(String drug_num) {
		
		service.deleteMedicine(drug_num);
		
		return "forward:/medicine/index";
		
	}
	
	
	
	
	
	
	
	
}
