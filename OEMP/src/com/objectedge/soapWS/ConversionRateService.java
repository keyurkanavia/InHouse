package com.objectedge.soapWS;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import br.com.multiplusfidelidade.ebs.v1.ConsultarProdutoResgateFaultMsg;
import br.com.multiplusfidelidade.ebs.v1.ErroTecnicoFaultMsg;

/**
 * This class is responsible to connect to ConsultarProdutoResgate service and get shipping value and conversion rate
 * 
 * @author Object Edge Inc.
 */
public class ConversionRateService{	
	
	private static final String BARRAMENTO_ERROR = "BARRAMENTO_ERROR";
	private static final String GENERIC_EXCEPTION = "Exception while calling ConsultarProdutoResgate for loyaltyCode:[{0}], [{1}]";
	private static final String TECH_EXCEPTION = "Tech error while calling ConsultarProdutoResgate for loyaltyCode:[{0}], service errorCode:[{1}], errorMsg:[{2}]";
	private static final String FAULT_EXCEPTION = "Fault Exception while calling ConsultarProdutoResgate for loyaltyCode:[{0}], service errorCode:[{1}], errorMsg:[{2}]";
	private ConsultarProdutoResgateClient client;
	private String endpoint;
	private String userName;
	private String password;
	private String conversionRateTechErrorCode;
	private String conversionRateBusinessErrorCode;
	private boolean enabled;
	String ERROR = "error";
	String RETURN_CODE = "RETURN_CODE";
	

	
	
	ConversionRateService(){
		endpoint = "http://barramento:9000/EBS/ConsultarProdutoResgatev1?wsdl";
		userName = "ATGUser";
		password = "mplus2@13";
		conversionRateTechErrorCode = "conversionRateServiceTechErrorCode1";
		conversionRateBusinessErrorCode="conversionRateBusinessErrorCode1";
		enabled = true;
		client = new ConsultarProdutoResgateClient(this.endpoint, this.userName, this.password);
	}
	/**
	 * This is the main method to fetch conversion rate and shipping value.
	 * 
	 * @param loyaltyCode
	 * @param organizationPartnerCode
	 * @return returnMap - containing the output response
	 */
	public Map<String, Object> fetchConversionRate(String loyaltyCode, String organizationPartnerCode) {
		if(!isEnabled()) {
			System.out.println("ConversionRateService is not Enabled.");
			return null;
		}
		Map<String, Object> returnMap = new HashMap<String, Object>(3);
		try {
			
			if (loyaltyCode == null || loyaltyCode.isEmpty() || organizationPartnerCode == null || organizationPartnerCode.isEmpty()) {
				System.out.println("fetchConversionRate: loyalty code or organization code is null/empty. Loyalty code - [{0}]organization partner code - [{1}]"+loyaltyCode+ organizationPartnerCode);
				returnMap.put(ERROR, getConversionRateBusinessErrorCode() + ":"
						+ "loyalty code or organization code is empty. Loyalty code - ["+loyaltyCode+"] "
						+ "organization partner code - ["+organizationPartnerCode+"]");
				return returnMap;
			}
			returnMap = client.fetchConversionRate(loyaltyCode, organizationPartnerCode);
			Integer retval = (Integer) returnMap.get(RETURN_CODE);
			if (retval != null) {
				if (retval == 1) {
					returnMap.put(ERROR, getConversionRateTechErrorCode());
				}
				if (retval == 2) {
					returnMap.put(ERROR, getConversionRateBusinessErrorCode());
				}
			} 
		} catch (ConsultarProdutoResgateFaultMsg fex) {
			
		} catch (ErroTecnicoFaultMsg tex) {			
			
		} catch (Exception ex) {
			
		}
 		return returnMap;
	}
	
	/**
	 * 
	 * @return
	 */
	public ConsultarProdutoResgateClient getClient() {
		return client;
	}
	/**
	 * 
	 * @param client
	 */
	public void setClient(ConsultarProdutoResgateClient client) {
		this.client = client;
	}
	/**
	 * 
	 * @return
	 */
	public String getEndpoint() {
		return endpoint;
	}
	/**
	 * 
	 * @param endpoint
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	/**
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @return
	 */
	public String getConversionRateTechErrorCode() {
		return conversionRateTechErrorCode;
	}
	/**
	 * 
	 * @param conversionRateTechErrorCode
	 */
	public void setConversionRateTechErrorCode(String conversionRateTechErrorCode) {
		this.conversionRateTechErrorCode = conversionRateTechErrorCode;
	}
	/**
	 * 
	 * @return
	 */
	public String getConversionRateBusinessErrorCode() {
		return conversionRateBusinessErrorCode;
	}
	/**
	 * 
	 * @param conversionRateBusinessErrorCode
	 */
	public void setConversionRateBusinessErrorCode(String conversionRateBusinessErrorCode) {
		this.conversionRateBusinessErrorCode = conversionRateBusinessErrorCode;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * 
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
