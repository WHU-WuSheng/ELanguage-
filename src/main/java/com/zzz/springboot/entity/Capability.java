package com.zzz.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Title: Capability</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年4月2日 下午10:50:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Capability {
	private String username;
	private String language;
	private Integer level;
}
