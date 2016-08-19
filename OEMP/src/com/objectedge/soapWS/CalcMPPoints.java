package com.objectedge.soapWS;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import br.com.multiplusfidelidade.ebo.v1.ListaDePrecos;
import br.com.multiplusfidelidade.ebo.v1.Preco;
import br.com.multiplusfidelidade.ebs.v1.ConsultarProdutoResgateOutput;
import br.com.multiplusfidelidade.framework.business.CalcularPrecoPontos;
import br.com.multiplusfidelidade.framework.business.datatype.CalcularPrecoPontosInterface3DTO;
import br.com.multiplusfidelidade.framework.business.datatype.CalcularPrecoPontosInterface3SaidaDTO;
import br.com.multiplusfidelidade.framework.business.datatype.CalcularPrecoPontosInterface4DTO;
import br.com.multiplusfidelidade.framework.business.datatype.CalcularPrecoPontosInterface4SaidaDTO;
import br.com.multiplusfidelidade.framework.business.datatype.FaixaCustoPontos;
import br.com.multiplusfidelidade.framework.business.datatype.TipoFreteEnum;
import br.com.multiplusfidelidade.framework.business.datatype.impl.CalcularPrecoPontosInterface3DTOImpl;
import br.com.multiplusfidelidade.framework.business.datatype.impl.CalcularPrecoPontosInterface4DTOImpl;
import br.com.multiplusfidelidade.framework.business.exception.CalcularPrecoPontosException;
import br.com.multiplusfidelidade.framework.business.impl.CalcularPrecoPontosImpl;

/**
 * This util class is used to get the points, conversion value, shipping type from the webservice response
 * 
 * @author oedev
 *
 */
public class CalcMPPoints{
	
	/**
	 * Calculates the points from the jar Multiplus have given. This method would be be called if both list price and sale price 
	 * are available. It uses the Interface4 in the jar
	 * 
	 * @param listPrice
	 * @param salePrice
	 * @param output
	 * @returns the price object, CalcularPrecoPontosInterface4SaidaDTO
	 */
	public CalcularPrecoPontosInterface4SaidaDTO getPriceResultObj(BigDecimal listPrice, BigDecimal salePrice, 
																			ConsultarProdutoResgateOutput output) {
		
		CalcularPrecoPontosInterface4SaidaDTO outputObj = null;
		if(output != null && (listPrice != null || salePrice != null)) {	
			
			List<ListaDePrecos> listaDePrecosList = output.getProdutoFidelidade().getListasDePrecos().getListaDePrecos();
			Calendar prevDate = null;
			
			for(ListaDePrecos listaDePrecos : listaDePrecosList) {
				
				Calendar currentDate = Calendar.getInstance();
				int result = 1;
				if(listaDePrecos.getDataEfetivacao() != null){
					result = listaDePrecos.getDataEfetivacao().toGregorianCalendar().compareTo(currentDate);
				}
				
				if((result <= 0) && 
				        (prevDate == null || listaDePrecos.getDataEfetivacao().toGregorianCalendar().compareTo(prevDate) > 0)) {
	
						prevDate = listaDePrecos.getDataEfetivacao().toGregorianCalendar();
						CalcularPrecoPontosInterface4DTO inputObj = new CalcularPrecoPontosInterface4DTOImpl();
						
						inputObj.setPrecoDe(listPrice);
						inputObj.setPrecoPor(salePrice);
						
						
						if(listaDePrecos.getTipoCalculoFrete() != null) {
							String shippingValueType = listaDePrecos.getTipoCalculoFrete().getValorReferencia();
							
							if(("PORCENTAGEM").equalsIgnoreCase(shippingValueType)) {
								inputObj.setTipoFrete(TipoFreteEnum.PORCENTAGEM);
								//MP-3105 - Divide the shipping price by 100 if it is percentage
								if(listaDePrecos.getFrete() != null && listaDePrecos.getFrete().getQuantiaMonetaria() != null) {
									inputObj.setValorFrete((listaDePrecos.getFrete().getQuantiaMonetaria()).divide(new BigDecimal(100),7, BigDecimal.ROUND_HALF_UP));
								}
							}
							else {
								inputObj.setTipoFrete(TipoFreteEnum.VALOR);
								if(listaDePrecos.getFrete() != null && listaDePrecos.getFrete().getQuantiaMonetaria() != null) {
									inputObj.setValorFrete(listaDePrecos.getFrete().getQuantiaMonetaria());
								}
							}
						}
						
						if(listaDePrecos.getPrecos() != null && listaDePrecos.getPrecos().getPreco() != null) {
							List<Preco> precosList = listaDePrecos.getPrecos().getPreco();	
							for(Preco preco : precosList) {
								
								FaixaCustoPontos boundary = new FaixaCustoPontos();
								
								if(preco.getCustoPontos() != null) {
									boundary.setCustoPontos(preco.getCustoPontos().getQuantiaMonetaria());
								}
								if(preco.getFaixaInicial() != null) {
									boundary.setPrecoInicial(preco.getFaixaInicial().getQuantiaMonetaria());
								}
								if(preco.getFaixaFinal() != null) {
									boundary.setPrecoFinal(preco.getFaixaFinal().getQuantiaMonetaria());
								}
								
								inputObj.addFaixaCustoPontos(boundary);
							}
						}
						try {
							CalcularPrecoPontos points = new CalcularPrecoPontosImpl();	
							outputObj = points.calcular(inputObj);
						} catch(CalcularPrecoPontosException ex) {
							
						}
				}
				
			}	
		}	
		return outputObj;
	}
	
	/**
	 * Calculates the points from the jar Multiplus have given. This method would be be called if only list price is available for
	 * a sku. It uses the Interface3 in the jar
	 * 
	 * @param listPrice
	 * @param output
	 * @returns the price object, CalcularPrecoPontosInterface3SaidaDTO
	 */
	public CalcularPrecoPontosInterface3SaidaDTO getPriceResultObj(BigDecimal listPrice, ConsultarProdutoResgateOutput output) {
		
		CalcularPrecoPontosInterface3SaidaDTO outputObj = null;
		if(output != null && listPrice != null) {	
			
			List<ListaDePrecos> listaDePrecosList = output.getProdutoFidelidade().getListasDePrecos().getListaDePrecos();
			Calendar prevDate = null;
			
			for(ListaDePrecos listaDePrecos : listaDePrecosList) {
				
				Calendar currentDate = Calendar.getInstance();
				int result = 1;
				if(listaDePrecos.getDataEfetivacao() != null){
					result = listaDePrecos.getDataEfetivacao().toGregorianCalendar().compareTo(currentDate);
				}
				
				if((result <= 0) && 
				        (prevDate == null || listaDePrecos.getDataEfetivacao().toGregorianCalendar().compareTo(prevDate) > 0)) {
						
						prevDate = listaDePrecos.getDataEfetivacao().toGregorianCalendar();
						CalcularPrecoPontosInterface3DTO inputObj = new CalcularPrecoPontosInterface3DTOImpl();
						
						inputObj.setPreco(listPrice);
						
						if(listaDePrecos.getTipoCalculoFrete() != null) {
							String shippingValueType = listaDePrecos.getTipoCalculoFrete().getValorReferencia();
							
							if(("PORCENTAGEM").equalsIgnoreCase(shippingValueType)) {
								inputObj.setTipoFrete(TipoFreteEnum.PORCENTAGEM);
								//MP-3105 - Divide the shipping price by 100 if it is percentage
								if(listaDePrecos.getFrete() != null && listaDePrecos.getFrete().getQuantiaMonetaria() != null) {
									inputObj.setValorFrete((listaDePrecos.getFrete().getQuantiaMonetaria()).divide(new BigDecimal(100),7, BigDecimal.ROUND_HALF_UP));
								}
							}
							else {
								inputObj.setTipoFrete(TipoFreteEnum.VALOR);
								if(listaDePrecos.getFrete() != null && listaDePrecos.getFrete().getQuantiaMonetaria() != null) {
									inputObj.setValorFrete(listaDePrecos.getFrete().getQuantiaMonetaria());
								}
							}
						}
						
						if(listaDePrecos.getPrecos() != null && listaDePrecos.getPrecos().getPreco() != null) {
							List<Preco> precosList = listaDePrecos.getPrecos().getPreco();	
							for(Preco preco : precosList) {
								
								FaixaCustoPontos boundary = new FaixaCustoPontos();
								
								if(preco.getCustoPontos() != null) {
									boundary.setCustoPontos(preco.getCustoPontos().getQuantiaMonetaria());
								}
								if(preco.getFaixaInicial() != null) {
									boundary.setPrecoInicial(preco.getFaixaInicial().getQuantiaMonetaria());
								}
								if(preco.getFaixaFinal() != null) {
									boundary.setPrecoFinal(preco.getFaixaFinal().getQuantiaMonetaria());
								}
								
								inputObj.addFaixaCustoPontos(boundary);
							}
						}
						try {
							CalcularPrecoPontos points = new CalcularPrecoPontosImpl();	
							outputObj = points.calcular(inputObj);
						} catch(CalcularPrecoPontosException ex) {
							
						}
				}
				
			}	
		}
		return outputObj;
	}    
}
