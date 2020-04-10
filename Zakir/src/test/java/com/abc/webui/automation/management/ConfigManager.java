package com.abc.webui.automation.management;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.abc.webui.automation.utils.resourceUtils;

public class ConfigManager {

	private Properties _properties;
	
	public ConfigManager load(String filePath) {
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(filePath));
			_properties = new Properties();
			
			try {
				_properties.load(reader);
				reader.close();
			}catch (IOException ex) {
				//TODO: handle exception
			}
		}catch (FileNotFoundException ex){
			//TODO: handle exception
		}
		
		return this;
	}
	
	public ConfigManager loadDefault() {
		String filePath = resourceUtils.getFilePath(com.abc.webui.automation.management.Constants.relativeResourcePathOfConfig);
		load(filePath);
		return this;
	}
	
	public String get(String key) {
		return this._properties.getProperty(key);
	}
	
	public String getUrl() {
		return get("url");
	}
	
	public String getBrowser() {
		return get("browser");
	}
	
	public String getExecutionLocation() {
		return get("executionLocation");
	}
	
	public int getImplicitlyWait() {
		return Integer.parseInt(get("implicitlyWait"));
	}
	
	public int getPageLoadTimeout() {
		return Integer.parseInt(get("pageLoadTimeout"));
	}
	
	public boolean getIsMaximize() {
		return Boolean.parseBoolean(get("isMaximize"));
	}
	
	public String getDefaultUserId() {
		return get("defaultUserId");
	}
	
	public String getDefaultPassword() {
		return get("defaultPassword");
	}
	
	public String getReportOutPath() {
		return get("reportOutPath");
	}
	
	public String getDownloadFilePath() {
		return get("fileDownloadPath");
	}
	
	private static ConfigManager _defaultConfigManager;
	
	public static synchronized ConfigManager getDefault() {
		if(_defaultConfigManager == null) {
			_defaultConfigManager = new ConfigManager();
			_defaultConfigManager.loadDefault();
		}
		return _defaultConfigManager;
	}
}
