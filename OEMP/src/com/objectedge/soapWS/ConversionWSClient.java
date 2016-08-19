package com.objectedge.soapWS;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.multiplusfidelidade.ebo.v1.CategoriaVeiculo;
import br.com.multiplusfidelidade.ebo.v1.CategoriaVeiculoList;
import br.com.multiplusfidelidade.ebs.v1.ConsultarProdutoResgateOutput;
import br.com.multiplusfidelidade.ebs.v1.CotarLocacaoVeiculoParceiroFaultMsg;
import br.com.multiplusfidelidade.ebs.v1.CotarLocacaoVeiculoParceiroOutput;
import br.com.multiplusfidelidade.ebs.v1.ErroTecnicoFaultMsg;
import br.com.multiplusfidelidade.framework.business.datatype.CalcularPrecoPontosInterface4SaidaDTO;

public class ConversionWSClient {
	
	 private CalcMPPoints calculateMPPoints;
	 private ConversionRateService conversionRateService;
	 
	 ConversionWSClient(){
		 calculateMPPoints = new CalcMPPoints();
		 conversionRateService = new ConversionRateService();
	 }
	 
	 public static void main(String[] args){
		 ConversionWSClient client = new ConversionWSClient();
		 client.calculatePriceToPoints("1-1MASJ1O","8740769");
	 }
	 
	 
	 public boolean calculatePriceToPoints(String loyaltyCode,String skuPartnerCode) {
	        try {
	            Map<String, Object> returnMap = getConversionRateService().fetchConversionRate(loyaltyCode,skuPartnerCode);
	            ConsultarProdutoResgateOutput output = null;
	            if (returnMap.get(loyaltyCode) != null) {
	                output = (ConsultarProdutoResgateOutput) returnMap.get(loyaltyCode);
	            }
	        } catch (Exception ex) {
	            throw ex;
	        }
	        return true;
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
	
}
