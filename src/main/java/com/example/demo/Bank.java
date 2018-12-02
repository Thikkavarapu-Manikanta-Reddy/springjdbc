package com.example.demo;

public class Bank {

	private String id;
	private String email;
	private String password;
	
	public Bank() {

	}
	
	public Bank(String id,String email,String password) {
		super();
		this.id =id;
		this.email = email;
		this.password = password;
	}

	public String getid() {
		return id;
	}
	
	public String getemail() {
		return email;
	}

	public String getpassword() {
		return password;
	}
	
	 public void setid(String num) {
		   id = num;
		  }
	
	 public void setemail(String name) {
		   email = name;
		  }
		  public void setpassword(String programme) {
			 password = programme;
		  }
	
}
