package com.dvdworld.business;

public enum CartOperations {
    ADDTOCART, REMOVEFROMCART, CHECKOUT, EMPTYCART;
    
    public static CartOperations getEnum(String s){
		if(ADDTOCART.name().equals(s)){
			return ADDTOCART;
		} else if(REMOVEFROMCART.name().equals(s)){
			return REMOVEFROMCART;
		} else if(CHECKOUT.name().equals(s)){
			return CHECKOUT;
		} else if (EMPTYCART.name().equals(s)){
			return EMPTYCART;
		}
		
		throw new IllegalArgumentException("No Enum specified for this string (\"" + s + "\")");
	}
}
