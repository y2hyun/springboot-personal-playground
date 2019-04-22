package com.y2hyun.playground.service;

import com.y2hyun.playground.vo.UserVo;

public interface UserService {

	/**
	 * ユーザー登録
	 * @param user
	 */
	void registUser(UserVo user);
	
	/**
	 * ユーザー登録後、ログイン処理
	 * @param email
	 * @param password
	 */
	void loginAfterRegistration(String email, String password);
}
