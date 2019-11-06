package com.revature.grademanagementsystemfacultyms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.revature.grademanagementsystemfacultyms.configuration.MessageConstants;
import com.revature.grademanagementsystemfacultyms.dto.ForgotPasswordDto;
import com.revature.grademanagementsystemfacultyms.dto.MailDTO;
import com.revature.grademanagementsystemfacultyms.exception.DBException;
import com.revature.grademanagementsystemfacultyms.exception.ServiceException;
import com.revature.grademanagementsystemfacultyms.exception.ValidatorException;
import com.revature.grademanagementsystemfacultyms.model.User;
import com.revature.grademanagementsystemfacultyms.repository.UserRepository;
import com.revature.grademanagementsystemfacultyms.validator.FacultyValidator;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MailService mailService;
	@Autowired
	private FacultyValidator facultyValidator;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Transactional
	public User login(String email, String password) throws ServiceException {
		User user = new User();
		try {
			user = userRepository.login(email, password);
		} catch (DBException e) {
			throw new ServiceException(MessageConstants.UNABLE_TO_LOGIN);
		}
		return user;
	}

	@Transactional
	public User insert(User user) throws ServiceException {
		try {
			facultyValidator.addedEmployeeValidation(user);
			
			MailDTO mailDto=new MailDTO();
			user = userRepository.save(user);
	
			mailDto.setName(user.getName());
			mailDto.setEmail(user.getEmail());
			mailService.sendMail(mailDto);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(MessageConstants.UNABLE_TO_INSERT);
		}
		return user;
	}

	@Transactional
	public User forgotPasswordService(String email) throws ServiceException {
		
		User user = userRepository.findByEmail(email);
		if(user == null)
			throw new ServiceException(MessageConstants.DOESNOT_REGISTERED);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear user,\n");
		sb.append("		Your Password is, ").append("'"+user.getPassword()+"'");
		
		ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
		forgotPasswordDto.setText(sb.toString());
		forgotPasswordDto.setTo(user.getEmail());
		
		mailService.sendMailToUser(forgotPasswordDto);
		return user;
	}
}
