package com.blackdroidstudios.cleansweep.reportlog;

public class ErrorReporting {
	public static ErrorReporting instance = null;
	
	private ErrorReporting(){}
	
	public static ErrorReporting GetInstance(){
		if(instance == null)
			return new ErrorReporting();
		else
			return instance;
	}
	
	public static void WriteError(String message){
		System.out.println(message);
	}
}
