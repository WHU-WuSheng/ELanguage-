package com.zzz.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private String username;
	private String password;
	private String phone;
	private String email;
	private String profile;
	private String picture;
	private Integer credit;
	private Integer money;
	private String sex;
	private Integer age;
}
