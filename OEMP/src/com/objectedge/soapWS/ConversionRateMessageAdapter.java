package  com.objectedge.soapWS;

import java.util.HashMap;
import java.util.Map;


import br.com.multiplusfidelidade.ebo.v1.Canal;
import br.com.multiplusfidelidade.ebo.v1.Parceiro;
import br.com.multiplusfidelidade.ebo.v1.Produto;
import br.com.multiplusfidelidade.ebo.v1.PropriedadesExecucao;
import br.com.multiplusfidelidade.ebo.v1.TipoReferencia;
import br.com.multiplusfidelidade.ebs.v1.ConsultarProdutoResgateInput;
import br.com.multiplusfidelidade.ebs.v1.ConsultarProdutoResgateOutput;

/**
 * 
 * @author Object Edge Inc.
 *
 */
public class ConversionRateMessageAdapter implements AdapterConstants{

	/**
	 * Creates the ConsultarProdutoResgate Input object
	 * @param loyaltyCode
	 * @param orgPartnerCode
	 * @param interfaceId
	 * @return inputReq, ConsultarProdutoResgateInput
	 */
	public ConsultarProdutoResgateInput adaptRequestConsultarProdutoResgateInput(String loyaltyCode, String orgPartnerCode, 
			Integer interfaceId) { 
		
		System.out.println("adaptRequestConsultarProdutoResgateInput Number:" + loyaltyCode);
		
		if (loyaltyCode == null || loyaltyCode.isEmpty() || orgPartnerCode == null || orgPartnerCode.isEmpty()) {
			System.out.println("adaptRequestConsultarProdutoResgateInput Loyalty Code or org partner code passed is empty. loyalty code - "+ 
						loyaltyCode+ " organization partner code -"+ orgPartnerCode);
			return null;
		}
		
		ConsultarProdutoResgateInput inputReq = new ConsultarProdutoResgateInput();
		
		Produto produto = new Produto();
		produto.setIdProduto(loyaltyCode);
		
		Parceiro parceiro = new Parceiro();
		parceiro.setCodigoParceiro(orgPartnerCode);
		
		produto.setParceiro(parceiro);
		inputReq.setProdutoFidelidade(produto);
		
		Canal canal = new Canal();
		
		TipoReferencia canalTipoReferencia = new TipoReferencia();
		canalTipoReferencia.setValorReferencia("Web");
		canal.setCanal(canalTipoReferencia);

		TipoReferencia subCanalTipoReferencia = new TipoReferencia();
		subCanalTipoReferencia.setValorReferencia("eCommerce");	
		canal.setSubcanal(subCanalTipoReferencia);
		
		inputReq.setCanalCatalogo(canal);
				
		PropriedadesExecucao propriedadesExecucao = new PropriedadesExecucao();
		propriedadesExecucao.setIdInterface(interfaceId);
		inputReq.setPropriedadesExecucao(propriedadesExecucao);
		
		return inputReq;
	}
	
	/**
	 * Returns a map with sku loyalty code as key and web service response as value
	 * 
	 * @param outputRes
	 * @param loyaltyCode
	 * @return returnMap
	 */
	public Map<String, Object> adaptResponseConsultarProdutoResgateOutput(ConsultarProdutoResgateOutput outputRes, String loyaltyCode) {
		
		System.out.println("adaptResponseConsultarProdutoResgateOutput :" + outputRes);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (outputRes == null) {
			System.out.println("adaptResponseConsultarProdutoResgateOutput is empty");
			returnMap.put(RETURN_CODE, Integer.valueOf(TECH_ERROR));
			return returnMap;
		}
		try {
			returnMap.put(loyaltyCode, outputRes);
		} catch (Exception ex) {
			System.out.println("adaptResponseConsultarProdutoResgateOutput exception while adapting Response:" +ex);
		}
		return returnMap;
	}
}
