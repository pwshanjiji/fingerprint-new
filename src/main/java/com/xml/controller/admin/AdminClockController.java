package com.xml.controller.admin;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xml.entity.Clock;
import com.xml.mapper.ClockMapper;


@Controller
@RequestMapping("/admin")
public class AdminClockController {

	@Autowired
	private ClockMapper clockMapper;


	
	@RequestMapping("/clock")
	public String clock(Integer pageNum,
			String name,
			Model model){
		if(pageNum==null) {
			pageNum=1;
		}
		Clock clock = new Clock();
		clock.setUsername(name);
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
		return "admin/clock/list";
	}
	
	
	/**
	 */
	@RequestMapping(value="/clockDel")
	public ModelAndView deleteEmp(Integer id){
		clockMapper.delete(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/clock");
		return modelAndView;
	}
	
	
	
	
	
    
}