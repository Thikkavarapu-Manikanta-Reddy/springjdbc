package com.example.demo;

public class Hello {

	private int id;
	private String email;
	private String password;
	
	public Hello() {

	}
	
	public Hello(int id,String email,String password) {
		super();
		this.id =id;
		this.email = email;
		this.password = password;
	}

	public int getid() {
		return id;
	}
	
	public String getemail() {
		return email;
	}

	public String getpassword() {
		return password;
	}
	
	 public void setid(int num) {
		   id = num;
		  }
	
	 public void setemail(String name) {
		   email = name;
		  }
		  public void setpassword(String programme) {
			 password = programme;
		  }
	
}
