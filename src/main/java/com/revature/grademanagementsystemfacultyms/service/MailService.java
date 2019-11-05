package com.revature.grademanagementsystemfacultyms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.revature.grademanagementsystemfacultyms.dto.ForgotPasswordDto;
import com.revature.grademanagementsystemfacultyms.dto.MailDTO;

@Service
public class MailService {
	@Autowired
	RestTemplate restTemplate;

	private String apiUrl = "https://charity-notification.herokuapp.com";

	public void sendMail(final MailDTO mailDTO) {
		ResponseEntity<Void> postForEntity = restTemplate.postForEntity(apiUrl + "/mail/registeruser", mailDTO,
				Void.class);
		System.out.println(postForEntity);
	}

	public void sendMailToUser(final ForgotPasswordDto forgotPasswordDto) {

		ResponseEntity<Void> postForEntity = restTemplate.postForEntity(apiUrl + "/mail/send", forgotPasswordDto,
				Void.class);
		System.out.println(postForEntity);
	}
}
