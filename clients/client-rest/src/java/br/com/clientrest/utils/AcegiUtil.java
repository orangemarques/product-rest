package br.com.clientrest.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AcegiUtil {

	public static UserDetails getUserDetails() {
		UserDetails details = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return details;	
	}
	
}
