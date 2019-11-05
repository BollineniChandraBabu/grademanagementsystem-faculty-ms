package com.revature.grademanagementsystemfacultyms.dto;

import lombok.Data;

@Data
public class ForgotPasswordDto {
	private String subject="ForgotPassword";
	private String text;
	private String to;
}
