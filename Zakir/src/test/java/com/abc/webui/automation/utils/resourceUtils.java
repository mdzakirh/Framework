package com.abc.webui.automation.utils;

import java.io.File;

public class resourceUtils {
	
	public static File getFile(String resourceName) {
		ClassLoader loader = resourceUtils.class.getClassLoader();
//		File file = new File(loader.getResource(resourceName).getFile());
		File file = new File(resourceName);
		return file;
	}
	
	public static String getFilePath(String resourceName) {
		File file = getFile(resourceName);
		return file.getPath();
	}
}
