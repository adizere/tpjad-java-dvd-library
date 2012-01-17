package com.dvdworld.model;

public enum UserType {
	USERBORROWER, USERADMIN;
	
    public static UserType getEnum(String s){
		if(USERBORROWER.name().equals(s)){
			return USERBORROWER;
		} else if (USERADMIN.name().equals(s)){
			return USERADMIN;
		}
		
		throw new IllegalArgumentException("No UserType Enum specified for this string (\"" + s + "\")");
	}
}
