package com.learningjavaspring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.learningjavaspring.service.UserService;
import com.learningtechspring.model.User;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value={"/","/login"})
	public ModelAndView login(){
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("login");
		return modelView;
	}
	
	@GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
	
	@PostMapping(value="/registration")
	public ModelAndView createNewUser(@Valid User user,BindingResult bindingResult){
		
		ModelAndView modelView = new ModelAndView();
		User userExist = userService.findUserByEmail(user.getEmail());
		if(userExist!=null){
			bindingResult.rejectValue("email", "error.user","There is already user exist!!");
		}
		
		if(bindingResult.hasErrors()){
			modelView.setViewName("registration"); 
		}else{
			userService.saveUser(user);
			modelView.addObject("user",new User());
			modelView.addObject("successMessage", "User has been registered successfully");
			modelView.setViewName("registration");
		}
		
		return modelView;
	}
	
	@GetMapping(value="/admin/home")
    public ModelAndView home(){
		ModelAndView modelView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelView.setViewName("admin/home");
		return modelView;
	}
	
}


/*
 * The HTTP response status code 302 Found is a common way of performing URL redirection. 
 * The HTTP/1.0 specification (RFC 1945) initially defined this code, and gave it the description phrase 
 * "Moved Temporarily" rather than "Found".
 */

