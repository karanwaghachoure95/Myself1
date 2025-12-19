package com.example.Myself.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Myself.dao.MyselfDao;
import com.example.Myself.entity.Account;
import com.example.Myself.entity.LoginOtp;
import com.example.Myself.entity.Protected;

@Service
public class MyselfService {
	@Autowired
	private MyselfDao dao;

	public boolean start(Account user) {

		boolean yes = dao.start(user);

		if (yes) {
			return true;
		} else {
			return false;
		}
	}

	public List<Account> findAllAccountData() {
		return dao.findAllAccountData();
	}

	public boolean deleteAccount(int id) {
		return dao.deleteAccount(id);
		
	}

	public Account getAccountById(int id) {
		return dao.getAccountById(id);
		
	}

	public boolean updateProfile(Account account) {
		return dao.updateAccount(account);
		
	}

	public Protected getUsernameAndPassword() {
		return dao.getUsernameAndPassword();
		
	}

	public boolean storedLoginOtp(String otp) {
		return dao.storedLoginOtp(otp);
		
	}

	public boolean verifyOtp(LoginOtp user) {
		return dao.verifyOtp(user);
		
	}

	public Protected getbyId() {
		return dao.getbyId();
		
	}

	public boolean updateProtected(Protected protectedObj) {
        return dao.updateProtected(protectedObj);		
	}

}
