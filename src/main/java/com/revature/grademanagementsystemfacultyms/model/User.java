package com.revature.grademanagementsystemfacultyms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="users")
public class User {
		 @Id
		 @GeneratedValue(strategy=GenerationType.IDENTITY)
		 @Column(name="id")
		 private int id;
		 @Column(name="name")
		 private String name;
		 @Column(name="email")
		 private String email;
		 @Column(name="mobile")
		 private Long mobile;
		 @Column(name="password")
		 private String password;
		 @Column(name="role")
		 private char role;
}
