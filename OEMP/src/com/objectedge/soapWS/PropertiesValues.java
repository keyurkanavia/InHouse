package com.objectedge.soapWS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class PropertiesValues {
	private static final String PARTNERS = "Partners";
	String result = "";
	InputStream inputStream;
	private static final String ENVIRONMENT = "Environments";

	 
	Properties prop;
	String propFileName = "config.properties";
	
	
	public PropertiesValues() throws IOException {
		prop = new Properties();
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
	
			Date time = new Date(System.currentTimeMillis());
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
	}
	
	@Deprecated
	public String getPropValues() throws IOException {
	
		try {
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
			// get the property value and print it out
			String combinations = prop.getProperty("combinations");
			result = combinations;
			System.out.println(result + "\nProgram Ran on " + time + " by combinations=" + combinations);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
	
	public static void main(String []args){
		PropertiesValues pop = null;
		try {
			pop = new PropertiesValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pop.getCodeMap();
	}
	
	public HashMap<String, HashMap<String,HashMap<String,String>>> getCodeMap(){
		HashMap<String,HashMap<String,HashMap<String,String>>> envrtPartnerCodeMap = new HashMap<String, HashMap<String,HashMap<String,String>>>();
		
		String[] envrntList = getListForProp(ENVIRONMENT);
		String[] partnerList = getListForProp(PARTNERS);
		
		for (String env : envrntList) {
			for (String partner : partnerList) {
				System.out.println(env+" , "+partner);
				String[] codeList = getListForProp(env+"."+partner);
				if(codeList != null){
					HashMap<String,HashMap<String,String>> partnerCodeMap = new HashMap<String, HashMap<String,String>>();
					partnerCodeMap.put(partner, getcodeMap(codeList));
					envrtPartnerCodeMap.put(env, partnerCodeMap);
				}else{
					System.out.println("WARNING : cod list not found for combination "+env+" , "+partner);
				}
			}
		}
		
		return envrtPartnerCodeMap;
	}

	private HashMap<String,String> getcodeMap(String[] codeList) {
		HashMap<String,String> loyaltyCodeAndPartCodeMap = new HashMap<String,String>();
		for (String code : codeList) {
			String[] pair = code.split("=");
			loyaltyCodeAndPartCodeMap.put(pair[0], pair[1]);
		}
		return loyaltyCodeAndPartCodeMap;
	}

	private String[] getListForProp(String envrnt) {
		String envs = prop.getProperty(envrnt);
		
		if(envs!=null)
			return envs.split(",");
		else
			return null;
	}
}
