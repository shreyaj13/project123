package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Vendor;
import com.app.service.IVendorService;

import sun.print.resources.serviceui;

@Controller //mandatory
@RequestMapping("/user")//optional but recommended for separation
public class UserController {
	@Autowired
	private IVendorService service;
public UserController() {
	
	// TODO Auto-generated constructor stub
	System.out.println("in ctor of"+getClass().getName());
}
@GetMapping("/login")
public String showLoginForm()
{
	System.out.println("in show login form");
	return "/user/login";
}
@PostMapping("/login")
public String processLoginForm(@RequestParam String email,@RequestParam String password,Model map,HttpSession hs)
{
	System.out.println("in process login form"+email+""+password);
	Vendor v=null;
	try
	{
	 v=service.validateUser(email, password);
	}
	catch (RuntimeException e) {
		System.out.println("invalid login"+e);
		map.addAttribute("status","invalid login.pls retry");
		return "/user/login";
		// TODO: handle exception
	}
	System.out.println("successful login");
	map.addAttribute("status","successful login");

	//map.addAttribute("user_dtls",v);
	hs.setAttribute("user_dtls",v);
	//chk role--
	//admin--list page
	if(v.getRole().equals("vendor"))
	return "/vendor/details";
	return "/admin/list";
}
@GetMapping("/vendorlist")
public String getVendorList(Model map)
{
	List<Vendor> vlist=service.listVendors();
	map.addAttribute("vlist",vlist);
	return "admin/list";
}

@RequestMapping("/cartlist")
public String ShowCart()
{
	
	return "vendor/cart";
}

@GetMapping("/delete")
public String deleteVendor(@RequestParam int vendorid)
{
	System.out.println(vendorid);
return 	"redirect:vendorlist";
}
}
