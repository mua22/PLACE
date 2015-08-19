package epclaim.utils;

import antlr.StringUtils;

public class CommonStringUtils {
	private static int tabCount = 1;
	public static String CurlyBraceOpen(boolean newline){
		if(newline)
			return "{\n"+ CommonStringUtils.repeat("\t", ++tabCount);
		else return "{"+CommonStringUtils.repeat("\t", ++tabCount);
	}
	public static String tabs(){
		return CommonStringUtils.tabs(CommonStringUtils.tabCount);
	}
	public static String tabs(int n){
		return CommonStringUtils.repeat("\t", n);
	}
	public static String CurlyBraceClose(boolean newline){
		if(newline)
			return "\n"+CommonStringUtils.repeat("\t", --tabCount)+"}";
		else return CommonStringUtils.repeat("\t", --tabCount)+"}\n";
	}
	public static String repeat(String s,int n){
		return String.format(String.format("%%0%dd", n), 0).replace("0",s);
	}
}
