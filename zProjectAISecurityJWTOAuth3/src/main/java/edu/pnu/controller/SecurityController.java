package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	@GetMapping("/login")			public String user()	{	return "user";	}
//	@GetMapping("/member")	public String member()	{	return "member";	}
//	@GetMapping("/manager")	public String manager()	{	return "manager";	}
//	@GetMapping("/admin")	public String admin()	{	return "admin";		}

}
