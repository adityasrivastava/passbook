package com.mds.passbook.controller;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuthController {
	
	@RequestMapping("/oauth2callback")
	public int oauthCallBack(@RequestParam(value="code") String code){
		
		System.out.println(code);
		
		return HttpStatus.OK_200; 
		
	}

}
