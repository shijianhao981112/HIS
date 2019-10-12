package com.zhiyou100;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class TestPOI {

	/**
	 * 导出: 从系统(程序) 中导出到 本地
	 * @throws IOException
	 */
	@Test
	public void export() throws IOException {
		// 1.创建工作表
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.创建sheet
		HSSFSheet sheet1 = wb.createSheet("挂号信息");
		// 3.在sheet中创建行 : 
		// 参数 : rownum 指创建第几行  从0开始
		HSSFRow row0 = sheet1.createRow(1); 
		
		
		// 设置
		row0.setHeight((short) 500);
		// 4.在行中创建列
		// 参数 : column 指创建第几列 从0开始
		HSSFCell r0r0 = row0.createCell(1);
		
		// 5.单元格内写内容
		r0r0.setCellValue("门诊编号"); // 第二行第二个
		// 6.导出 : 输出流导出 文件后缀只能是xls
		OutputStream os  = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\1.xls");
		
		// 使用工作表导出
		wb.write(os);
		os.close();
		System.out.println("成功");
	}
	/**
	 * 导入: 从本地 导入项目(程序)
	 * @throws Exception 
	 */
	@Test
	public void importExcel() throws Exception {
		// 1. 将本地文件以输入流的形式读入程序
		InputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\1.xls");
		// 2. 通过流创建工作表
		HSSFWorkbook wb = new HSSFWorkbook(in);
		// 3. 获得sheet  : 根据sheet下标获得
		HSSFSheet sheet0 = wb.getSheetAt(0);
		// 4. 获得行 : 获得第二行对象
		HSSFRow row1 = sheet0.getRow(1);
//		Iterator<Row> iterator = sheet0.iterator();
		
		
		// 5. 获得列 : 获得第二列
		HSSFCell r1c1 = row1.getCell(1);
		// 6. 获得值
		String value = r1c1.getStringCellValue();
		System.out.println("导入Excel,读出数据 : "+value);
	}
}
