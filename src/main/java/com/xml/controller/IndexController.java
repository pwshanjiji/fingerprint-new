package com.xml.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class IndexController {

	

	@RequestMapping(value="/")
	public String main1(){
		return "main/login";
	}
	
	@RequestMapping(value="/main/index")
	public String index(){
		return "main/index";
	}
	
	@RequestMapping(value="/gotoBuyEquipCharts")
	public String gotoBuyEquipCharts(){
		return "buyEquip/list2";
	}
	
	@RequestMapping(value="/gotoBuyTicketCharts")
	public String gotoBuyTicketCharts(){
		return "buyTicket/list2";
	}
	
	
	@RequestMapping(value="/gotoUserEquipCharts")
	public String gotoUserEquipCharts(){
		return "userEquip/list2";
	}
	
	@RequestMapping(value="/gotoUserLockerCharts")
	public String gotoUserLockerCharts(){
		return "userLocker/list2";
	}
	
	
}
