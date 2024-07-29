package edu.pnu.controller;

import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomMyUtil {

	public static String getUsernameFromOAuth2User(OAuth2User user) {
		// TODO Auto-generated method stub
		String userString = user.toString();
		String regName = null;
		
		//userString 조사
		if(userString.contains("google"))			regName = "Google";
		else if(userString.contains("facebook"))	regName = "Facebook";
		else if(userString.contains("naver"))		regName = "Naver";
		else if(userString.contains("kakao"))		regName = "Kakao";
		else {
			if(userString.contains("id=") && userString.contains("resultcode=")&& userString.contains("response="))
					regName = "Naver";
			else
				return null;
		}
		String name = user.getName();
		if(name == null) return null;
		
		return regName + "_" + name;
	}
}
