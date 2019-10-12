package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Aa {

	public static void main(String[] args) {
		
		Date d1 = new Date();
		 

         SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss SS");
         String format = sdf.format(d1);
        System.out.println("格式化后 : "+format);
		System.out.println(new Date().getTime());
		System.out.println(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));

	}

}
