package com.revature.grademanagementsystemfacultyms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.grademanagementsystemfacultyms.configuration.MessageConstants;
import com.revature.grademanagementsystemfacultyms.exception.ValidatorException;
import com.revature.grademanagementsystemfacultyms.model.User;
import com.revature.grademanagementsystemfacultyms.repository.UserRepository;

@Service
public class FacultyValidator {
	@Autowired
	private UserRepository userRepository;

	public void addedEmployeeValidation( User user) throws ValidatorException {
		
		if (user.getName() == null || "".equals(user.getName().trim())) {
			throw new ValidatorException("Invalid Name");
		} 
			
		User email = userRepository.findByEmail(user.getEmail());
		if( email != null )
			throw new ValidatorException(MessageConstants.MAIL_AREADY_EXIST);
			
		User mobileNo = userRepository.findByMobNo(user.getMobile());
		if( mobileNo != null )
			throw new ValidatorException(MessageConstants.MOBILE_AREADY_EXIST);
			
		if (user.getPassword() == null || "".equals(user.getPassword().trim()))
			 throw new ValidatorException(MessageConstants.INVALID_PASSWORD); 
		
}
}
