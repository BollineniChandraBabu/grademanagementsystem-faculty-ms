package com.revature.grademanagementsystemfacultyms.dto;

import lombok.Data;

@Data
public class UserDto {
	private int id;
	private String name;
	private String email;
	private Long mobile;
	private String password;
	private char role;
}
