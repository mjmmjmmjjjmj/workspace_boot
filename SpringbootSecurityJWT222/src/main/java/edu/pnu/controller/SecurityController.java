package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController
public class SecurityController {

//	@GetMapping({"/", "/index"})
//	public String index() {
//	System.out.println("index 요청");
//	return "index";
//	}
//	@GetMapping("/member")
//	public void member() {
//	System.out.println("Member 요청");
//	}
//	@GetMapping("/manager")
//	public void manager() {
//	System.out.println("Manager 요청");
//	}
//	
//	@GetMapping("/admin")
//	public void admin() {
//	System.out.println("Admin 요청");
//	}
//	@GetMapping("/loginSuccess")
//	public void loginSuccess() {
//	System.out.println("loginSuccess 요청");
//	}

	@GetMapping("/")		public String index()		{	return "index";	}
	@GetMapping("/member")	public String member()		{	return "member"; }
	@GetMapping("/manager")	public String manager()		{	return "manager";}
	@GetMapping("/admin")	public String admin()		{	return "admin";} 

}
