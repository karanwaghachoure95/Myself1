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
	
	@GetMapping("/")
	public String showAccount() {
		return "accountForm";
	}

	@GetMapping("/accountForm")
	public String startAccount() {
		return "accountForm";
	}

	@PostMapping("/accountForm")
	public String startAccount(@ModelAttribute Account user, RedirectAttributes redirectAttributes) {
		boolean yes = myselfservice.start(user);

		if (yes) {
			redirectAttributes.addFlashAttribute("msg1", "Create Account Successfully....");
			return "redirect:/accountForm";
		} else {
			redirectAttributes.addFlashAttribute("msg2", "Something went wrong...");
			return "redirect:/accountForm";
		}
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/loginProcess")
	public String checkingPage(@RequestParam String username, @RequestParam String password, Model model,
			RedirectAttributes ra) {
		List<Account> infAccount = myselfservice.findAllAccountData();
		Protected user1 = myselfservice.getUsernameAndPassword();
		String otp = LoginOtpSend.emailOtpsendToCollege();
		
		  myselfservice.storedLoginOtp(otp);
		 
		if (user1.getUsername().equals(username) && passwordencoder.matches(password, user1.getPassword())) {
			model.addAttribute("correct", "Successfully login...");
			model.addAttribute("accounts", infAccount);
			return "/accountFormSave";
		} else if ("Karan__2151".equals(username) && "Karan@2004".equals(password)){
			if(otp !=null) {
				model.addAttribute("msg1","otp send successfully...");
				return "/otp";
			}else {
				ra.addFlashAttribute("error", "Invalid username or password ");
				return "redirect:/login";
			}
		
		}else {
			ra.addFlashAttribute("error", "Invalid username or password");
			return "redirect:/login";
		}
	}

   @PostMapping("/verifyOtp")
   public String verifyOtp(@ModelAttribute LoginOtp user ,Model model) {
	  boolean yes= myselfservice.verifyOtp(user);
	  
	  if(yes) {
		  model.addAttribute("msg1","verify Sucessfully...");
		  Protected protectedObj = myselfservice.getbyId();
		  model.addAttribute("protectedObj" , protectedObj);
		  return "/updateProtected";
	  }else {
		  model.addAttribute("msg","something went wrong...");
		  return"/otp";
	  }
	   
   }
	
   
	
	@PostMapping("/updateProtected")
	public String updateProtected(@ModelAttribute Protected protectedObj , Model model ,RedirectAttributes ra) {
		boolean yes = myselfservice.updateProtected(protectedObj);
		
		if(yes) {
			ra.addFlashAttribute("msg1","update successfully...");
			return "redirect:/login";
		}else {
			model.addAttribute("msg2","Something went wrong...");
			return "updateProtected";
		}
	}
   
	@GetMapping("/accountFormSave")
	public String findAllAccountData(Model model, RedirectAttributes ra) {
		List<Account> infAccount = myselfservice.findAllAccountData();
		if (infAccount != null) {
			model.addAttribute("accounts", infAccount);

			return "/accountFormSave";
		} else {
			ra.addFlashAttribute("error", "Invalid username or password");
			return "redirect:/login";
		}

	}

	@GetMapping("deleteAccount/{id}")
	public String deleteAccount(@PathVariable int id, RedirectAttributes redirectAttribute) {
		boolean accId = myselfservice.deleteAccount(id);
		if (accId) {
			redirectAttribute.addFlashAttribute("msg3", "deleted Account Successfully in database");
			return "redirect:/accountFormSave";
		} else {
			redirectAttribute.addFlashAttribute("msg4", "deleted information problem");
			return "redirect:/accountFormSave";
		}
	}

	@GetMapping("updateProfile/{id}")
	public String getAccountById(@PathVariable int id, Model model) {
		Account account = myselfservice.getAccountById(id);
		model.addAttribute("account", account);
		return "/updateAccount";
	}

	@PostMapping("/updateAccount")
	public String updateProfile(@ModelAttribute Account account, RedirectAttributes ra, Model model) {
		boolean yes = myselfservice.updateProfile(account);

		if (yes) {
			ra.addFlashAttribute("msg5", "Update profile successfully...");
			return "redirect:/accountFormSave";
		} else {
			model.addAttribute("msg6", "Something Problem...");
			return "/updateAccount";
		}

	}
	
	
	
}
