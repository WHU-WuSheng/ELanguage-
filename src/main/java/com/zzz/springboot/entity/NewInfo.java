package com.zzz.springboot.entity;

import com.zzz.springboot.entity.User.UserBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewInfo {

	String username;
	int count;
	
}