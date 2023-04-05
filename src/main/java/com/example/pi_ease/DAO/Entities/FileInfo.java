package com.example.pi_ease.DAO.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class FileInfo {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
  private String name;
  private String url;
  
  

  public FileInfo() {
	super();
	// TODO Auto-generated constructor stub
}



  public FileInfo( String name, String url) {
	
	
//	this.id = id;
	this.name = name;
	this.url = url;
}



public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }



//public long getId() {
//	return id;
//}
//
//
//
//public void setId(long id) {
//	this.id = id;
//}
  
  
}
