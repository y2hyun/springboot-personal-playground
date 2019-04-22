package com.y2hyun.playground.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	private static final String HEADER_XFF = "X-Forwarded-For";
	
	@RequestMapping("")
	public String home(HttpServletRequest request, Model model, @AuthenticationPrincipal User user) {
		log.debug(user == null ? "Not logged in." : user.toString());
		model.addAttribute("name", user == null ? getClientIp(request) : user.getUsername());
		return "home";
	}
	
	private String getClientIp(HttpServletRequest request) {
		String xff = request.getHeader(HEADER_XFF);
		if(StringUtils.isEmpty(xff)) {
			return request.getRemoteAddr();
		}
		return xff;
	}
}
