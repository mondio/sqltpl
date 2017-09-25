package com.mondio.template.sqltmp.util;

public class Util {
	public static String replaceFEchar(String s){
		if(s!=null && s.length()>1){
			s=s.trim();
			char[] c=s.toCharArray();
			if(c[0]=='\''){
				s=s.substring(1);
			}
			if(c[c.length-1]=='\''){
				s=s.substring(0, s.length()-1);	
			}
		}
		return s;
	}
	public static String[] replaceFEArrayQuotes(String[] s){
		for (int i = 0; i < s.length; i++) {
			s[i]=replaceFEchar(s[i]);
		}
		return s;
	}
	public static String creatSql(String parentName, String[] values) {
		String insertSql="insert into "+parentName+" values (";
		for (int i = 0; i < values.length; i++) {
			String str=values[i];
			if(!str.contains("nextval") && !str.equals("CURRENT_TIMESTAMP")){
				if(!str.equals("")){
					if(!str.substring(0, 1).equals("'")){
						str="'"+str;
					}
					if(!str.substring(str.length()-1).equals("'")){
						str=str+"'";
					}
				}else{
					str="''";
				}
			}
			if(i==0){
				insertSql+=str;
			}else{
				insertSql+=","+str;
			}
		}
		return insertSql+");";
	}
}

