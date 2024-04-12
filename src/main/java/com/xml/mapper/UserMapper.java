package com.xml.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xml.entity.User;



public interface UserMapper {
	
	 //根据username和password查询用户
	User findAdminByNameAndPwd(@Param("username") String username,@Param("password") String password);

	
	List<User> findList(User user);
	
	Integer delete(@Param("id") Integer id);
	
	User findById(@Param("id") Integer id);
	
	Integer insert(User user);
	
	Integer update(User user);

}
