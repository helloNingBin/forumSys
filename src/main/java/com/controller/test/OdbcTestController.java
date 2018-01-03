/**
 * 
 */
package com.controller.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bin
 *
 */
@Controller
public class OdbcTestController {
   @RequestMapping("/test/db/odbcTest")
   @ResponseBody
   public String odbcTest()throws Exception{
	   Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		 Connection connection =  DriverManager.getConnection("jdbc:mysql://45.77.152.163:3306/forumSys?useUnicode=true&characterEncoding=utf8","forumSys","1234");
		 PreparedStatement statement = connection.prepareStatement("select {FN SUBSTRING('123456789', 4)} from dual");
		 ResultSet resultSet = statement.executeQuery();
		 resultSet.next();
		 return (String) resultSet.getObject(1);
   }
}
