package com.revature.grademanagementsystemfacultyms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.grademanagementsystemfacultyms.configuration.MessageConstants;
import com.revature.grademanagementsystemfacultyms.exception.DBException;
import com.revature.grademanagementsystemfacultyms.exception.ServiceException;
import com.revature.grademanagementsystemfacultyms.model.User;
import com.revature.grademanagementsystemfacultyms.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
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
			user = userRepository.save(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(MessageConstants.UNABLE_TO_INSERT);
		}
		return user;
	}
}
