package com.ae.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import com.ae.springboot.service.AirthmaticExpression;



@SpringBootTest
public class ApplicationTest {
	
	 @Autowired
	    private AirthmaticExpression ae;
	
	@Test
	void contextLoads() {
		String a= "(2+4)*5";
		String b= " (2+4)*4";
		String c= "(2+4)*3";
		String d= "(4-2)*2 ";
		
		
		System.out.println(a+"=="+ae.eval(a));
		System.out.println(b+"=="+ae.eval(b));
		System.out.println(c+"=="+ae.eval(c));
		System.out.println(d+"=="+ae.eval(d));
	}
	
	
	

}
