package com.y2hyun.playground.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.y2hyun.playground.service.UserService;
import com.y2hyun.playground.vo.UserVo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {

	private final UserService userService;
	
	@GetMapping("/login")
	public String loinIndex() {
		return "login";
	}
	
	/* provided by Spring Security
	@PostMapping("/login")
	public String loginSubmit() {
		return "/";
	}
	*/
	
	@GetMapping("/join")
	public String joinIndex(Model model) {
		model.addAttribute("user", new UserVo());
		return "join";
	}
	
	@PostMapping("/join")
	public String joinSubmit(
			@ModelAttribute("user") @Valid UserVo user,
			BindingResult result, Errors errors) {
		
		if(result.hasErrors()) {
			return "join";
		}
		
		this.userService.registUser(user);
		
		// 会員登録後、自動ログイン
		this.userService.loginAfterRegistration(user.getEmail(), user.getPassword());
		
		return "forward:/";
	}
}
