package com.zzz.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Title: User</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年4月2日 下午10:50:08
 */
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
