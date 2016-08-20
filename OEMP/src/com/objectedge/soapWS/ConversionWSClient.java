package com.objectedge.soapWS;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import br.com.multiplusfidelidade.ebs.v1.ConsultarProdutoResgateOutput;
import br.com.multiplusfidelidade.framework.business.datatype.CalcularPrecoPontosInterface4SaidaDTO;

public class ConversionWSClient {
	
	 private CalcMPPoints calculateMPPoints;
	 private ConversionRateService conversionRateService;
	 private PropertiesValues propValues;
	 
	 ConversionWSClient() throws IOException{
		 calculateMPPoints = new CalcMPPoints();
		 conversionRateService = new ConversionRateService();
		 propValues = new PropertiesValues();
	 }
	 
	 public static void main(String[] args){
		 ConversionWSClient client = null;
		 HashMap<String, HashMap<String, HashMap<String, String>>> map = null;
		 
		try {
			client = new ConversionWSClient();
			map = client.getPropvalues().getCodeMap();
			System.out.println(map.get("TI").get("CB").keySet());
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 
		System.out.println("Hashmap = ");
		 client.calculatePriceToPoints("1-OWIFV1","17629627",map);
	 }
	 	 
	 public boolean calculatePriceToPoints(String loyaltyCode,String skuPartnerCode, HashMap<String, HashMap<String, HashMap<String, String>>> map) {
	        try {
	        	ConsultarProdutoResgateOutput output = getWSRespWithWorkingCodesFromProperties(map);
	        	//ConsultarProdutoResgateOutput output = getWSResp(loyaltyCode,skuPartnerCode);
	        	if(output != null){
		            CalcularPrecoPontosInterface4SaidaDTO priceResultObj = getCalculateMPPoints().getPriceResultObj(new BigDecimal(3),
	                		new BigDecimal(4), output);
	                Long listPoints = ((CalcularPrecoPontosInterface4SaidaDTO) priceResultObj).getPrecoDePontos();
					Long salePoints = ((CalcularPrecoPontosInterface4SaidaDTO) priceResultObj).getPrecoPorPontos();
					
					System.out.println("List Points = "+listPoints);
					System.out.println("Sale Points = "+salePoints);
	        	}else{
	        		System.out.println("The is no output from the ConsultarProdutoresgate Web Service!!");
	        	}
	        } catch (Exception ex) {
	            throw ex;
	        }
	        return true;
	    }
	
    private ConsultarProdutoResgateOutput getWSResp(String loyaltyCode,
			String skuPartnerCode) {
    	ConsultarProdutoResgateOutput output = null;
    	Map<String, Object> returnMap = getConversionRateService().fetchConversionRate(loyaltyCode,skuPartnerCode);
        
        
    	if (returnMap.get(loyaltyCode) != null) {
    		output = (ConsultarProdutoResgateOutput) returnMap.get(loyaltyCode);
        
    	}
		return output;
	}

	private ConsultarProdutoResgateOutput getWSRespWithWorkingCodesFromProperties(
			HashMap<String, HashMap<String, HashMap<String, String>>> map) {
    	ConsultarProdutoResgateOutput output = null;
    	Iterator<Map.Entry<String, HashMap<String, HashMap<String, String>>>> envEntries = map.entrySet().iterator();
        while(envEntries.hasNext() && output== null){	
        	Map.Entry<String, HashMap<String, HashMap<String, String>>> envEntry = envEntries.next();
        	
        	Iterator<Map.Entry<String, HashMap<String, String>>> partnerEntries = envEntry.getValue().entrySet().iterator();
            while(partnerEntries.hasNext() && output== null){
            	Map.Entry<String, HashMap<String, String>> prtEntry = partnerEntries.next();
            	
            	Iterator<Map.Entry<String, String>> codeEntries = prtEntry.getValue().entrySet().iterator();
	            while(codeEntries.hasNext() && output== null){
	            	Map.Entry<String, String> codeEntry = codeEntries.next();
	            	String loyaltyCode = codeEntry.getKey();
	            	String skuPartnerCode = codeEntry.getValue();
	            	Map<String, Object> returnMap = getConversionRateService().fetchConversionRate(loyaltyCode,skuPartnerCode);
            
	            
	            	if (returnMap.get(loyaltyCode) != null) {
	            		output = (ConsultarProdutoResgateOutput) returnMap.get(loyaltyCode);
	                
	            	}
	            }
            }
        }
		return output;
	}

	public CalcMPPoints getCalculateMPPoints() {
        return calculateMPPoints;
    }

	public ConversionRateService getConversionRateService() {
		return conversionRateService;
	}

	public void setConversionRateService(ConversionRateService conversionRateService) {
		this.conversionRateService = conversionRateService;
	}

	public void setCalculateMPPoints(CalcMPPoints calculateMPPoints) {
		this.calculateMPPoints = calculateMPPoints;
	}

	public PropertiesValues getPropvalues() {
		return propValues;
	}

	public void setPropvalues(PropertiesValues propValues) {
		this.propValues = propValues;
	}
	
}
