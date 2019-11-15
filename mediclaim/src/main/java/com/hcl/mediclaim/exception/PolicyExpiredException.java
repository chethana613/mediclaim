package com.hcl.mediclaim.exception;

public class PolicyExpiredException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7097799874938339052L;
	

	public PolicyExpiredException(String s){
		super(s); 
	}

}
