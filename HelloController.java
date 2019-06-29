package com.app.controller;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller//mandatory
public class HelloController {
	
public HelloController() {
System.out.println("in ctor of"+getClass().getName());
}
@PostConstruct
public void init123()
{
	System.out.println("in init of"+getClass().getName());
}
//request handling method to test the flow
@RequestMapping("/hello")//mandatory
public String test1()
{
	
	System.out.println("in test1");
	return "welcome";
}
//request handling method to test model and view

@RequestMapping("/hello2")//mandatory
public ModelAndView test2()
{
	
	System.out.println("in test2");
	return new ModelAndView("welcome","server_dt",new Date());
}

//request handling method to test Model Map
@RequestMapping("/hello3")//mandatory
public String test3(Model map)
{
	
	System.out.println("in test3"+map);
	map.addAttribute("server_dt",new Date());
	map.addAttribute("num_list",Arrays.asList(1,20,30,40));
	System.out.println(map);
	
	return "welcome";
}

}
