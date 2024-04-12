package com.xml.controller.user;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xml.entity.Admin;
import com.xml.entity.User;
import com.xml.mapper.UserMapper;


@Controller
@RequestMapping("/user")
public class UserUserController {

	@Autowired
	private UserMapper userMapper;


	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(value="/gotoEditUser")
	public String gotoEditHotelType(HttpSession session,Model model){
		User user = (User) session.getAttribute("user");
		User userdb =userMapper.findById(user.getId());
		model.addAttribute("pa"	, userdb);
		return "user/user/edit";
	}
	
	
	
	@RequestMapping(value="/editUser", method = {RequestMethod.POST})
	public ModelAndView editUser(User user) {
		System.out.println("user:"+user); 
		userMapper.update(user);
		ModelAndView modelAndView = new ModelAndView("redirect:/user/gotoEditUser");
		return modelAndView;
	}

	
	
	
    
}