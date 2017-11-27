package com.akaiha.mobmoney.util;

import org.bukkit.ChatColor;

public class Format {
	
	public String color(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}
  
	public String replace(String str, String search, String replacement) {
		int searchCharsLength = search.length();
		StringBuilder buf = new StringBuilder(str);
		for (int i = 0; i < str.length(); i++) {
			int start = buf.indexOf(search, i);
		    if (start == -1) {
		    	break;
		    }	
		    buf = buf.replace(start, start + searchCharsLength, replacement);
		}
		return buf.toString();
	}
}