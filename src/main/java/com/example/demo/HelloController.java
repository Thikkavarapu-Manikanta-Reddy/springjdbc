package com.example.demo;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HelloController extends JdbcDaoSupport {

	 @Autowired
	    DataSource dataSource;
	  
	 @PostConstruct
	 private void initialize(){
	  setDataSource(dataSource);
	 }
	
 
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public List<Hello> HelloWorld( @RequestBody Hello myvar3 )
	{
		List<Hello> customers = new ArrayList<>();
		String query = "INSERT INTO user ( ID,NAME, PASSWORD) VALUES (?,?,?)" ;
		  getJdbcTemplate().update(query, new Object[]{ myvar3.getid(),myvar3.getemail(), myvar3.getpassword()});
		customers.add(myvar3);
		  return customers;
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<Hello> HelloUser()
	{
		 String query = "SELECT * FROM user";
		  List<Map<String, Object>> studentRow = getJdbcTemplate().queryForList(query);
		   
		  List<Hello> students = new ArrayList<Hello>();
		  for(Map<String, Object> row:studentRow){
			  Hello student = new Hello();
			  student.setid((int)row.get("ID"));
		   student.setemail((String)row.get("NAME"));
		   student.setpassword((String)row.get("PASSWORD"));
		   students.add(student);
		  }
		   
		  return students;
	}
	
	  @RequestMapping(value = "/delete/{myvar}",method = RequestMethod.DELETE)
	  public List<Hello> delete(@PathVariable("myvar") String myvar){
		  String query1 = "DELETE FROM user WHERE ID= ?";
		  getJdbcTemplate().update(query1, myvar);
		  String query2 = "SELECT * FROM user";
		  List<Map<String, Object>> studentRow = getJdbcTemplate().queryForList(query2);
		   
		  List<Hello> students = new ArrayList<Hello>();
		  for(Map<String, Object> row:studentRow){
			  Hello student = new Hello();
			  student.setid((int)row.get("ID"));
		   student.setemail((String)row.get("NAME"));
		   student.setpassword((String)row.get("PASSWORD"));
		   students.add(student);
		  }
		   
		  return students;
		  
		  
	  }
	  
		@RequestMapping(value = "/update/{myvar}", method = RequestMethod.PUT)
		public List<Hello> update( @RequestBody Hello myvar3,@PathVariable("myvar") String myvar)
		{
			List<Hello> customers = new ArrayList<>();
			String query = "UPDATE user SET ID=?,NAME=?,PASSWORD=? WHERE ID=?";
			  getJdbcTemplate().update(query, new Object[]{ myvar3.getid(),myvar3.getemail(), myvar3.getpassword(),myvar});

				customers.add(myvar3);
				  return customers;
	  
		}
		
		@RequestMapping(value = "/agents/{myvar}", method = RequestMethod.GET)
		public boolean exis(@PathVariable("myvar") String myvar)
		{
			boolean result = false;
			String sql = "SELECT count(*) FROM user WHERE NAME = ?";
			int count = getJdbcTemplate().queryForObject(
                 sql, new Object[] { myvar }, Integer.class);
			
			if (count > 0) {
				result = true;
			}
			
				  return result;
	  
		}
		
		@RequestMapping(value = "/user/{myvar3}", method = RequestMethod.GET)
		public List<Hello> user(@PathVariable("myvar3") String myvar3)
		{
			String query = "SELECT * FROM user WHERE ID LIKE ? OR NAME LIKE ? OR PASSWORD LIKE ? ";
			  List<Map<String, Object>> studentRow = getJdbcTemplate().queryForList(query,"%"+myvar3+"%","%"+myvar3+"%","%"+myvar3+"%");   
			  List<Hello> students = new ArrayList<Hello>();
			  for(Map<String, Object> row:studentRow){
				  Hello student = new Hello();
				  student.setid((int)row.get("ID"));
			   student.setemail((String)row.get("NAME"));
			   student.setpassword((String)row.get("PASSWORD"));
			   students.add(student);
			  }
			   
			  return students;
	  
		}
		
		@RequestMapping(value = "/user", method = RequestMethod.GET)
		public List<Hello> user()
		{
				String query = "SELECT * FROM user";
				List<Map<String, Object>> studentRow = getJdbcTemplate().queryForList(query);
				  List<Hello> students = new ArrayList<Hello>();
				  for(Map<String, Object> row:studentRow){
					  Hello student = new Hello();
					  student.setid((int)row.get("ID"));
				   student.setemail((String)row.get("NAME"));
				   student.setpassword((String)row.get("PASSWORD"));
				   students.add(student);
				  }
				  return students;
		}
		@RequestMapping(value = "/totpage/{limit}", method = RequestMethod.GET)
		public List<Integer> pages(@PathVariable("limit") Integer limit)
		{
			String sql = "SELECT count(*) FROM user";
			int count = getJdbcTemplate().queryForObject(
                 sql, Integer.class);
			double count1 =  Math.ceil((double)count/(double)limit);
			List<Integer> pages = new ArrayList<Integer>();
			for(int i=0;i<count1;i++)
			{
				pages.add(i);
			}
				  return pages;
		}
		
		@RequestMapping(value = "/count/{limit}", method = RequestMethod.GET)
		public List<Double> counting(@PathVariable("limit") Integer limit)
		{
			String sql = "SELECT count(*) FROM user";
			int count = getJdbcTemplate().queryForObject(
                 sql, Integer.class);
			double count1 =  Math.ceil((double)count/(double)limit);
			List<Double> pages = new ArrayList<Double>();
				pages.add(count1);
				  return pages;
		}
		
		@RequestMapping(value = "/pages/{limit}/{offset}", method = RequestMethod.GET)
		public List<Hello> pages(@PathVariable("limit") Integer limit,@PathVariable("offset") Integer offset)
		{
			String query = "SELECT * FROM user ORDER BY ID LIMIT ? OFFSET ?";
			List<Map<String, Object>> studentRow = getJdbcTemplate().queryForList(query,limit,offset);
			  List<Hello> students = new ArrayList<Hello>();
			  for(Map<String, Object> row:studentRow){
				  Hello student = new Hello();
				  student.setid((int)row.get("ID"));
			   student.setemail((String)row.get("NAME"));
			   student.setpassword((String)row.get("PASSWORD"));
			   students.add(student);
			  }
			  return students;
		}
		
		@RequestMapping(value = "/userdet/{myvar3}/{limit}", method = RequestMethod.GET)
		public List<Hello> checkpageuser(@PathVariable("myvar3") String myvar3,@PathVariable("limit") Integer limit)
		{
			String query = "SELECT * FROM user WHERE ID LIKE ? OR NAME LIKE ? OR PASSWORD LIKE ? ORDER BY ID LIMIT ?";
			  List<Map<String, Object>> studentRow = getJdbcTemplate().queryForList(query,"%"+myvar3+"%","%"+myvar3+"%","%"+myvar3+"%",limit);   
			  List<Hello> students = new ArrayList<Hello>();
			  for(Map<String, Object> row:studentRow){
				  Hello student = new Hello();
				  student.setid((int)row.get("ID"));
			   student.setemail((String)row.get("NAME"));
			   student.setpassword((String)row.get("PASSWORD"));
			   students.add(student);
			  }
			   
			  return students;
	  
		}
			
}
