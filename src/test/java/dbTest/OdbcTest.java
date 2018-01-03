/**
 * 
 */
package dbTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author bin
 *
 */
public class OdbcTest {
   public static void main(String[] args) throws Exception {
	   Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		 Properties p = new Properties();
		 Connection connection =  DriverManager.getConnection("jdbc:mysql://45.77.152.163:3306/forumSys?useUnicode=true&characterEncoding=utf8","forumSys","1234");
		 PreparedStatement statement = connection.prepareStatement("select {FN SUBSTRING('123456789', 2)} from dual");
		 ResultSet resultSet = statement.executeQuery();
		 resultSet.next();
		 System.out.println(resultSet.getObject(1));
}
}
