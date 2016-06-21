package com.nuova.facebook.util;

public class GenericValidation {
	
	public static boolean isNotNull(Object...objs){
		boolean isValid = false;
		if(objs != null && objs.length > 0){
			for(Object object : objs){
				if(object != null){
					isValid = true;
				}
			}
		}
		return isValid;
	}

}
