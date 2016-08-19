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
import br.com.multiplusfidelidade.framework.business.datatype.CalcularPrecoPontosInterface4DTO;
import br.com.multiplusfidelidade.framework.business.datatype.CalcularPrecoPontosInterface4SaidaDTO;
import br.com.multiplusfidelidade.framework.business.datatype.impl.CalcularPrecoPontosInterface4DTOImpl;

public class ConversionWSClient {
	
	 private CalcMPPoints calculateMPPoints;
	 private ConversionRateService conversionRateService;
	 
	 ConversionWSClient(){
		 calculateMPPoints = new CalcMPPoints();
		 conversionRateService = new ConversionRateService();
	 }
	 
	 public static void main(String[] args){
		 ConversionWSClient client = new ConversionWSClient();
		 client.calculatePriceToPoints("1-OWIFV1","17629627");
	 }
	 
	 
	 public boolean calculatePriceToPoints(String loyaltyCode,String skuPartnerCode) {
	        try {
	            Map<String, Object> returnMap = getConversionRateService().fetchConversionRate(loyaltyCode,skuPartnerCode);
	            ConsultarProdutoResgateOutput output = null;
	            if (returnMap.get(loyaltyCode) != null) {
	                output = (ConsultarProdutoResgateOutput) returnMap.get(loyaltyCode);
	                CalcularPrecoPontosInterface4SaidaDTO priceResultObj = getCalculateMPPoints().getPriceResultObj(new BigDecimal(3),
	                		new BigDecimal(4), output);
	                Long listPoints = ((CalcularPrecoPontosInterface4SaidaDTO) priceResultObj).getPrecoDePontos();
					Long salePoints = ((CalcularPrecoPontosInterface4SaidaDTO) priceResultObj).getPrecoPorPontos();
					
					System.out.println("List Points = "+listPoints);
					System.out.println("Sale Points = "+salePoints);
					
					
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

	public void setCalculateMPPoints(CalcMPPoints calculateMPPoints) {
		this.calculateMPPoints = calculateMPPoints;
	}
	
}
