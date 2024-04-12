package com.xml.entity;


import java.util.Date;


import lombok.Data;

@Data
public class Clock {

	private Integer id;
	private Integer user_id;
	private String username;
	
	
	private String time;
	private String status;
}
