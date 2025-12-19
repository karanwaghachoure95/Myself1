package com.example.Myself.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Myself.dao.MyselfDao;
import com.example.Myself.entity.Account;
import com.example.Myself.entity.LoginOtp;
import com.example.Myself.entity.Protected;

@Service
@Transactional
public class MyselfService {

    @Autowired
    private MyselfDao dao;

    /* ================= ACCOUNT ================= */

    public boolean start(Account user) {
        return dao.start(user);
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

    /* ================= ADMIN (PROTECTED) ================= */

    public Protected getUsernameAndPassword() {
        return dao.getUsernameAndPassword();
    }

    public Protected getbyId() {
        return dao.getbyId();
    }

    public boolean updateProtected(Protected protectedObj) {
        if (protectedObj == null) {
            return false;
        }
        return dao.updateProtected(protectedObj);
    }

    /* ================= OTP ================= */

    public boolean storedLoginOtp(String otp) {
        if (otp == null || otp.isEmpty()) {
            return false;
        }
        return dao.storedLoginOtp(otp);
    }

    public boolean verifyOtp(LoginOtp user) {
        if (user == null) {
            return false;
        }
        return dao.verifyOtp(user);
    }

    /* ================= INITIAL SETUP (OPTIONAL) ================= */

    public void saveProtected(Protected user1) {
    	user1.setId(1);
    	user1.setUsername("admin");
    	user1.setPassword("1234");
        if (user1 != null) {
            dao.saveProtected(user1);
        }
    }
}
