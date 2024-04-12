package com.xml.controller.user;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xml.utils.FingerUtils;
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
import com.xml.entity.Clock;
import com.xml.entity.User;
import com.xml.mapper.ClockMapper;
import com.xml.mapper.UserMapper;
import com.xml.utils.SimilarImageSearch;
import com.xml.utils.StringTools;


@Controller
@RequestMapping("/user")
public class UserClockController {

	@Autowired
	private ClockMapper clockMapper;

	@Autowired
	private UserMapper userMapper;


	@Value("${file.path}")
	private String fileBasePath;

	@Value("${host.ip}")
	private String ip;


	@Value("${server.port}")
	private String serverPort;



	@RequestMapping("/clock")
	public String clock(Integer pageNum,
			String name,
			Model model,HttpSession session){
		User user = (User) session.getAttribute("user");

		if(pageNum==null) {
			pageNum=1;
		}
		Clock clock = new Clock();
		clock.setUser_id(user.getId());
		//分页并查询
		PageHelper.startPage(pageNum,5);
		List<Clock> clocks = clockMapper.findList(clock);
		System.out.println(clocks);
		PageInfo pageInfo = new PageInfo<Clock>(clocks, 5);

        //startPage后紧跟的这个查询就是分页查询
	    model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("firstPage", "1");
		return "user/clock/list";
	}


	@RequestMapping(value="/gotoAddClock")
	public String gotoAddClock(Model model,HttpServletRequest request){
		return "user/clock/add";
	}


	/*
	 * 上传
	 */
	@RequestMapping(value="/addClock", method = {RequestMethod.POST})
	public String addUser(HttpSession session,@RequestParam(value = "img", required = true) MultipartFile img,HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		User userdb = userMapper.findById(user.getId());
		String source = userdb.getLocal();
		String local ="";
		String imgfilename=img.getOriginalFilename();
		if(!imgfilename.equals("")) {
			//保存图片
			//获取文件的保存路径
			String imgpath=fileBasePath+"userUpload";
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
			local = imgpath + "/" + imgtempFileName;
		}

		boolean flag = FingerUtils.metch(local, source);

        Clock clock = new Clock();
		clock.setUser_id(user.getId());
		if(flag) {
			clock.setStatus("成功");
		}else {
			clock.setStatus("失败");
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatStr =formatter.format(new Date());
		System.out.println(formatStr);//2017-九月-15 13:17:08:355
		clock.setTime(formatStr);
		clockMapper.insert(clock);

		if(flag) {
			request.setAttribute("info", "打卡成功！！！");
			return "user/clock/info";
		}else {
			request.setAttribute("info", "打卡失败！！！");
			return "user/clock/info";
		}


	}




}
