package com.xml.mapper;

import org.apache.ibatis.annotations.Param;

import com.xml.entity.Admin;




public interface AdminMapper {

	
	
    //根据username和password查询用户
	public Admin findAdminByNameAndPwd(@Param("username") String username,@Param("password") String password);

	Admin findById(@Param("id") Integer id);
	
	Integer update(Admin admin);
	

}
