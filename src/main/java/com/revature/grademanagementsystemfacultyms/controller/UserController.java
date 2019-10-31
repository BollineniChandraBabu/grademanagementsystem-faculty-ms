package com.revature.grademanagementsystemfacultyms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.revature.grademanagementsystemfacultyms.configuration.Message;
import com.revature.grademanagementsystemfacultyms.exception.ServiceException;
import com.revature.grademanagementsystemfacultyms.model.User;
import com.revature.grademanagementsystemfacultyms.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("addUser")
    @ApiOperation(value = "Register API")
     @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Registered", response = User.class),
             @ApiResponse(code = 400, message = "Invalid Details", response = Message.class) })
  public ResponseEntity<?> register(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("mobile") Long mobile,@RequestParam("password") String password,@RequestParam("role") char role) {
      String errorMessage = null;
      User user=new User();
      user.setName(name);
      user.setEmail(email);
      user.setMobile(mobile);
      user.setPassword(password);
      user.setRole(role);
      try 
      {
         user= userService.insert(user);
          return new ResponseEntity<>(user, HttpStatus.OK );
      } 
      catch (ServiceException e) {
    	  LOGGER.error(e.getMessage(), e);
          errorMessage = e.getMessage();
          Message message = new Message(errorMessage);
          return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST );
      }
	}
	@GetMapping("login")
    @ApiOperation(value = "Login API")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully LogedIn", response = User.class),
            @ApiResponse(code = 400, message = "Invalid Access", response = Message.class) })
	  public ResponseEntity<?> loginController(@RequestParam("email") String email,@RequestParam("password") String password) {
		 String errorMessage = null;
	      User user=null;
		 try {
	         user= userService.login(email,password);
	          return new ResponseEntity<>(user, HttpStatus.OK );
	      } catch (ServiceException e) {
	          errorMessage = e.getMessage();
	          Message message = new Message(errorMessage);
	          return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST );
	      }
}}
