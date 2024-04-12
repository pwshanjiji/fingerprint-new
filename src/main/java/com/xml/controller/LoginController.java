package com.xml.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xml.entity.Admin;
import com.xml.entity.User;
import com.xml.mapper.AdminMapper;
import com.xml.mapper.UserMapper;




@Controller
public class LoginController {
	
	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private UserMapper userMapper;

	
	@RequestMapping(value="/login", method = {RequestMethod.POST})
	@ResponseBody
	public String login(Admin admin,String type,HttpSession session) {
		if(type.equals("admin")) {
			String username= admin.getUsername();
			String password = admin.getPassword();
			Admin resultUser = adminMapper.findAdminByNameAndPwd(username, password);
			if(resultUser!=null) {
				session.setAttribute("admin", resultUser);
				System.out.println("登录成功");
				return "success";
			}
		}else {
			String username= admin.getUsername();
			String password = admin.getPassword();
			User resultUser = userMapper.findAdminByNameAndPwd(username, password);
			if(resultUser!=null) {
				session.setAttribute("user", resultUser);
				System.out.println("登录成功");
				return "success";
			}
		}
		return "fail";
	}
	
	
	
	/**
	 * 退出登录
	 */
	@RequestMapping(value="loginOut")
	public String userOut(HttpSession session){
		session.invalidate();
		return "main/login";
	}
	
	
	
}
