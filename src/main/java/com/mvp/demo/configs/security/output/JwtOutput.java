package com.mvp.demo.configs.security.output;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtOutput implements Serializable {
	
	private static final long serialVersionUID = -8091879091924046844L;
	private final String token;
}