package com.xml.controller.admin;



import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xml.entity.User;
import com.xml.mapper.UserMapper;
import com.xml.utils.StringTools;


@Controller
@RequestMapping("/admin")
public class AdminUserController {

	@Autowired
	private UserMapper userMapper;


	@Value("${file.path}")
	private String fileBasePath;
	
	@Value("${host.ip}")
	private String ip;
	
	
	@Value("${server.port}")
	private String serverPort;

	
	@RequestMapping("/user")
	public String user(Integer pageNum,
			String name,
			Model model){
		if(pageNum==null) {
			pageNum=1;
		}
		User user = new User();
		user.setName(name);
		//分页并查询
		PageHelper.startPage(pageNum,5);
		List<User> users = userMapper.findList(user);
		System.out.println(users);
		PageInfo pageInfo = new PageInfo<User>(users, 5);
        
        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "admin/user/list";
	}
	
	
	@RequestMapping(value="/gotoAddUser")
	public String gotoAddUser(Model model){
		return "admin/user/add";
	}
	
	
	
	/*
	 * 上传
	 */
	@RequestMapping(value="/addUser", method = {RequestMethod.POST})
	public ModelAndView addUser(User user,BindingResult bindingResult,@RequestParam(value = "img", required = true) MultipartFile img) {
		String imgfilename=img.getOriginalFilename();
		if(!imgfilename.equals("")) {
			//保存图片
			//获取文件的保存路径
			String imgpath=fileBasePath+"user";
			File dir = new File(imgpath);
			if(dir.exists() == true){
				System.out.println("dirs is exists");
			}else{
				dir.mkdirs();
				System.out.println(" created dirs");
			}
			
			//获取上传文件的名称
			//获取上传文件的扩展名
			String imgsuffix=imgfilename.substring(imgfilename.lastIndexOf("."));
			//为了防止上传文件同名，需要给上传文件重新命名
			String imgtempFileName=StringTools.getUUID().toString()+imgsuffix;
			File imgdest = new File(imgpath + "/" + imgtempFileName);
			try {
				img.transferTo(imgdest); //保存文件
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setLocal(imgpath + "/" + imgtempFileName);
			user.setImg("http://"+ip+":"+serverPort+ "/" +"user" + "/" + imgtempFileName);
		}
		
		userMapper.insert(user);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/user");
		return modelAndView;
	}
	
	
	/**
	 */
	@RequestMapping(value="/userDel")
	public ModelAndView deleteEmp( Integer id){
		userMapper.delete(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/user");
		return modelAndView;
	}
	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(value="/gotoEditUser")
	public String gotoEditHotelType(Integer id,Map<String, Object> map){
		
		User user =userMapper.findById(id);
		map.put("pa", user);
		return "admin/user/edit";
	}
	

	
	
	@RequestMapping(value="/editUser", method = {RequestMethod.POST})
	public ModelAndView editUser(User user,BindingResult bindingResult,@RequestParam(value = "img", required = true) MultipartFile img) {
		String imgfilename=img.getOriginalFilename();
		if(!imgfilename.equals("")) {
			//保存图片
			//获取文件的保存路径
			String imgpath=fileBasePath+"user";
			File dir = new File(imgpath);
			if(dir.exists() == true){
				System.out.println("dirs is exists");
			}else{
				dir.mkdirs();
				System.out.println(" created dirs");
			}
			
			//获取上传文件的名称
			//获取上传文件的扩展名
			String imgsuffix=imgfilename.substring(imgfilename.lastIndexOf("."));
			//为了防止上传文件同名，需要给上传文件重新命名
			String imgtempFileName=StringTools.getUUID().toString()+imgsuffix;
			File imgdest = new File(imgpath + "/" + imgtempFileName);
			try {
				img.transferTo(imgdest); //保存文件
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setLocal(imgpath + "/" + imgtempFileName);
			user.setImg("http://"+ip+":"+serverPort+ "/" +"user" + "/" + imgtempFileName);
			
		}
		userMapper.update(user);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/user");
		return modelAndView;
	}
	
	
	
    
}