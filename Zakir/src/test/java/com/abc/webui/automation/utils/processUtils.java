package com.abc.webui.automation.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class processUtils {
	
	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM";
	
	public static boolean isProcessRunning(String serviceName) {
		try {
			Process p = Runtime.getRuntime().exec(TASKLIST);
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			
			while((line = reader.readLine()) != null) {
				if(line.contains(serviceName)) {
					return true;
				}
			}
		}catch (Exception ex) {
			//TODO: handle exception
		}
		return false;
	}
	
	public static void killProcess(String serviceName) {
		if(isProcessRunning(serviceName)) {
			try {
				Runtime.getRuntime().exec(KILL+serviceName);
				killProcess(serviceName);
			}catch(Exception ex) {
				//TODO: hande exception
			}
		}
	}

}
