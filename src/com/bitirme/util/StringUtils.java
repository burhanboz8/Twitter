package com.bitirme.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static String trimStart(String str) {
		String leftRemoved = str.replaceAll("^\\s+", "");
	
		return leftRemoved;
	}
	public static String trimStartAndEnd(String str){
		return StringUtils.trimEnd(StringUtils.trimStart(str));
	}
	public static String removeUrl(String commentstr)
    {
      /*  String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(commentstr);
        int i = 0;
        while (m.find()) {
            commentstr = commentstr.replaceAll(m.group(i),"").trim();
            i++;
        }*/
		commentstr =commentstr.replaceAll("(http|https):(\\/\\/|\\\\)([^ ]*)"," ");
        return commentstr;
    }
	public static String removeAllWhiteSpaces(String str){
		str = str.replaceAll("\\s+","");
		return str;
	}
	public static String removeRT(String str){

		if(str.charAt(0) == 'R' && str.charAt(1) == 'T')
			str = str.substring(2);		
		return str;
	}
	public static String removeHashtag(String str){
		str = str.replaceAll("(#)[\\w]*","");
		str = str.replaceAll("(@)[\\w\\:]*", "");
		str = str.replaceAll("[(\\.)|(\\?)|(\\!)|(\\,)"
				+ "|(\\;)|(\\:)|(\\<)|(\\>)|[\\)]|(\\()"
				+ "|(\\^)|(\\+)|(\\%)|(\\&)|(\\/)"
				+ "|(\\=)|(\\*)|(\\-)|(\\_)|(\")|(\\')|(\\é)]", " ");
		return str;
	}

	public static String trimEnd(String str) {
		String rightRemoved = str.replaceAll("\\s+$", "");
		return rightRemoved;
	}
	public static String removeLastCharacter(String str) {
		if(str!=null){
			return str.substring(0,str.length()-1);
		}
		return null;
	} 
	public static String removeLastCharacterIfExistsAndTrim(String str,char cr){
		String str2 = str;
		if(str!=null && str.charAt(str.length()-1)==cr){
			str2 = str.substring(0,str.length()-1);
		}
		return StringUtils.trimEnd(str2);
	}
	public static String removeLastCharacterIfExists(String str,char cr){
		if(str!=null && str.charAt(str.length()-1)==cr){
			return str.substring(0,str.length()-1);
		}
		return str;
	}
	public static boolean checkIfLastCharacterExists(String str,char cr){
		if(str!=null && str.charAt(str.length()-1)==cr)
			return true;
		return false;
	}
	public static String removeAllWhiteSpacesExceptOne(String str){
		boolean foundBefore = false;
		StringBuilder sb = new StringBuilder();
		for(int i =0;i<str.length();i++){
			if(str.charAt(i)==' '){
				if(!foundBefore){
					sb.append(' ');
					foundBefore = true;	
				}
			}else{
				sb.append(str.charAt(i));
				foundBefore = false;
			}
		}
		return sb.toString();
	}
}
