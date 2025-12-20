package com.example.Myself.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Myself.entity.Account;
import com.example.Myself.entity.LoginOtp;
import com.example.Myself.entity.Protected;
import com.example.Myself.otp.LoginOtpSend;
import com.example.Myself.service.MyselfService;

@Controller
public class MyselfController {

    @Autowired
    private MyselfService myselfservice;

    @Autowired
    private PasswordEncoder passwordencoder;

    /* ================= ACCOUNT CREATE ================= */

    @GetMapping("/")
    public String accountForm() {
        return "accountForm";
    }

    @GetMapping("/accountForm")
    public String account() {
        return "/accountForm";
    }

    
    @PostMapping("/accountForm")
    public String createAccount(@ModelAttribute Account user, RedirectAttributes ra , Protected user1) {
        boolean yes = myselfservice.start(user);
        myselfservice.saveProtected(user1);

        if (yes) {
            ra.addFlashAttribute("msg1", "Create Account Successfully...");
        } else {
            ra.addFlashAttribute("msg2", "Something went wrong...");
        }
        return "redirect:/accountForm";
    }

    /* ================= LOGIN ================= */

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/loginProcess")
    public String loginProcess(@RequestParam String username,
                               @RequestParam String password,
                               Model model,
                               RedirectAttributes ra) {

        Protected admin = myselfservice.getUsernameAndPassword();

        if (admin == null) {
            ra.addFlashAttribute("error", "Admin not configured");
            return "redirect:/login";
        }

        // ===== Normal admin login =====
        if (admin.getUsername().equals(username)
                && passwordencoder.matches(password, admin.getPassword())) {

            List<Account> accounts = myselfservice.findAllAccountData();
            model.addAttribute("accounts", accounts);
            model.addAttribute("correct", "Successfully login...");
            return "accountFormSave";
        }

        // ===== OTP based super-admin login =====
        if ("Karan__2151".equals(username) && "Karan@2004".equals(password)) {
            String otp = LoginOtpSend.emailOtpsendToCollege();
            myselfservice.storedLoginOtp(otp);
            model.addAttribute("msg1", "OTP sent successfully...");
            return "otp";
        }

        ra.addFlashAttribute("error", "Invalid username or password");
        return "redirect:/login";
    }

    /* ================= OTP ================= */

    @PostMapping("/verifyOtp")
    public String verifyOtp(@ModelAttribute LoginOtp user, Model model) {
        boolean yes = myselfservice.verifyOtp(user);

        if (yes) {
            Protected protectedObj = myselfservice.getbyId();
            model.addAttribute("protectedObj", protectedObj);
            model.addAttribute("msg1", "OTP verified successfully...");
            return "updateProtected";
        }

        model.addAttribute("msg", "Invalid OTP");
        return "otp";
    }

    /* ================= UPDATE ADMIN ================= */

    @PostMapping("/updateProtected")
    public String updateProtected(@ModelAttribute Protected protectedObj,
                                  RedirectAttributes ra) {

        boolean yes = myselfservice.updateProtected(protectedObj);

        if (yes) {
            ra.addFlashAttribute("msg1", "Admin updated successfully...");
            return "redirect:/login";
        }

        ra.addFlashAttribute("msg2", "Something went wrong...");
        return "redirect:/login";
    }

    /* ================= ACCOUNT LIST ================= */

    @GetMapping("/accountFormSave")
    public String accountList(Model model) {
        List<Account> accounts = myselfservice.findAllAccountData();
        model.addAttribute("accounts", accounts);
        return "accountFormSave";
    }

    /* ================= DELETE ACCOUNT ================= */

    @GetMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable int id, RedirectAttributes ra) {
        boolean yes = myselfservice.deleteAccount(id);

        if (yes) {
            ra.addFlashAttribute("msg3", "Account deleted successfully");
        } else {
            ra.addFlashAttribute("msg4", "Delete failed");
        }
        return "redirect:/accountFormSave";
    }

    /* ================= UPDATE ACCOUNT ================= */

    @GetMapping("/updateProfile/{id}")
    public String updateProfilePage(@PathVariable int id, Model model) {
        Account account = myselfservice.getAccountById(id);
        model.addAttribute("account", account);
        return "updateAccount";
    }

    @PostMapping("/updateAccount")
    public String updateAccount(@ModelAttribute Account account,
                                RedirectAttributes ra) {

        boolean yes = myselfservice.updateProfile(account);

        if (yes) {
            ra.addFlashAttribute("msg5", "Profile updated successfully...");
            return "redirect:/accountFormSave";
        }

        ra.addFlashAttribute("msg6", "Update failed");
        return "redirect:/accountFormSave";
    }
}
