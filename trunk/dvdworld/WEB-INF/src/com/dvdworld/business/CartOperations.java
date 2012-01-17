package com.dvdworld.business;

public enum CartOperations {
    ADDTOCART, REMOVEFROMCART, CHECKOUT, EMPTYCART, PROCEEDRENT, ENDRENT, INVALIDOP;
    
    public static CartOperations getEnum(String s) {
		if(ADDTOCART.name().equals(s)) {
			return ADDTOCART;
		} else if(REMOVEFROMCART.name().equals(s)) {
			return REMOVEFROMCART;
		} else if(CHECKOUT.name().equals(s)) {
			return CHECKOUT;
		} else if (EMPTYCART.name().equals(s)) {
			return EMPTYCART;
		} else if (PROCEEDRENT.name().equals(s)) {
			return PROCEEDRENT;
		} else if (ENDRENT.name().equals(s)) {
			return ENDRENT;
		} else if (INVALIDOP.name().equals(s)) {
			return INVALIDOP;
		}
		
		throw new IllegalArgumentException("No CartOperations Enum specified for this string (\"" + s + "\")");
	}
}
