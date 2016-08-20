package com.objectedge.soapWS;

public interface AdapterConstants {

	String ERROR = "error";
	String RETURN_CODE = "RETURN_CODE";
	int SUCCESS = 0;
	int TECH_ERROR = 1;
	int BUSINESS_ERROR = 2;
	int INTERFACEID = 1;
	
	String BARRAMENTO_ERROR = "BARRAMENTO_ERROR";
	String GENERIC_EXCEPTION = "Exception while calling ConsultarProdutoResgate for loyaltyCode:[{0}], [{1}]";
	String TECH_EXCEPTION = "Tech error while calling ConsultarProdutoResgate for loyaltyCode:[{0}], service errorCode:[{1}], errorMsg:[{2}]";
	String FAULT_EXCEPTION = "Fault Exception while calling ConsultarProdutoResgate for loyaltyCode:[{0}], service errorCode:[{1}], errorMsg:[{2}]";
}
