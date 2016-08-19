package  com.objectedge.soapWS;

import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.AddressingFeature;

import br.com.multiplusfidelidade.ebs.v1.ConsultarProdutoResgateFaultMsg;
import br.com.multiplusfidelidade.ebs.v1.ConsultarProdutoResgateInput;
import br.com.multiplusfidelidade.ebs.v1.ConsultarProdutoResgateOutput;
import br.com.multiplusfidelidade.ebs.v1.ConsultarProdutoResgatev1;
import br.com.multiplusfidelidade.ebs.v1.ConsultarProdutoResgatev1PortType;
import br.com.multiplusfidelidade.ebs.v1.ErroTecnicoFaultMsg;

/**
 * 
 * This is the main class that implements method for calling service interface to get conversion rate and shipping value 
 * for a loyalty code namely ConsultarProdutoResgatev1
 * 
 * @author Object Edge Inc.
 *
 */
public class ConsultarProdutoResgateClient extends SecureClient {
	
	
	private ConsultarProdutoResgatev1 client;
	private ConsultarProdutoResgatev1PortType port;
	private ConversionRateMessageAdapter messageAdapter;
	private Integer interfaceId = 1; 
	private boolean initialised;
	String ERROR = "error";
	String RETURN_CODE = "RETURN_CODE";
	int SUCCESS = 0;
	int TECH_ERROR = 1;
	int BUSINESS_ERROR = 2;
	int INTERFACEID = 1;
	/**
	 * @param wsdl
	 * @param userName
	 * @param password
	 */
	public ConsultarProdutoResgateClient(String wsdl, String userName, String password){
		init(wsdl, userName, password);
	}
	
	/**
	 * Initializes the client
	 * 
	 * @param wsdl
	 * @param userName
	 * @param password
	 */
	public void init(String wsdl, String userName, String password) {
		System.out.println("Creating ConversionRate client. Time -> " + Calendar.getInstance().getTime() + " wsdl -> " + wsdl);
		try {
			setUsername(userName);
			setPassword(password);
			setWsdl(wsdl);
			// create web service client and port
			URL wsdlLocation = new URL(wsdl);
	        
			client = new ConsultarProdutoResgatev1(wsdlLocation);
			port = getClient().getConsultarProdutoResgatev1(new AddressingFeature(false));
	
			// create object factory
	
			addSecurityParamsToChain((BindingProvider)port);
			
			messageAdapter = new ConversionRateMessageAdapter();
			
			setInitialised(true);
			System.out.println("ConsultarProdutoResgate service is initialised: clientPort: " + port);
		} catch (Exception ex) {
			setInitialised(false);
			System.out.println("Exception while initializing ConsultarProdutoResgate :" + ex+ ex);
			return;
		}
	}
	
	/**
	 * This is the main method to be called from outside, which gives the response object
	 * 
	 * @param loyaltyCode
	 * @param orgPartnerCode
	 * @return returnMap
	 * @throws ConsultarProdutoResgateFaultMsg
	 * @throws ErroTecnicoFaultMsg
	 */
	public Map<String, Object> fetchConversionRate(String loyaltyCode, String orgPartnerCode) 
			throws ConsultarProdutoResgateFaultMsg, ErroTecnicoFaultMsg {
		
		System.out.println("Entering fetchConversionRate");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (loyaltyCode == null || loyaltyCode.isEmpty() || orgPartnerCode == null || orgPartnerCode.isEmpty()) {
			System.out.println("fetchConversionRate Loyalty code or sku code passed is empty. Loyalty code : " + loyaltyCode + " "
					+ "organization parner code - "+ orgPartnerCode);
			returnMap.put(RETURN_CODE, Integer.valueOf(BUSINESS_ERROR));
			return returnMap;   //returning from here to avoid further exceptions
		}
		
		if (!isInitialised()) {
			System.out.println("ConsultarProdutoResgate service is NOT initialised, cannot make call.");
			returnMap.put(RETURN_CODE, Integer.valueOf(TECH_ERROR));
			return returnMap;   //returning from here to avoid further exceptions
		}
		
		ConsultarProdutoResgateInput inputReq = null;
		ConsultarProdutoResgateOutput outputRes = null; 
		try {
			inputReq = messageAdapter.adaptRequestConsultarProdutoResgateInput(loyaltyCode, orgPartnerCode, interfaceId);	
			outputRes = port.consultarProdutoResgate(inputReq);
		} catch (ConsultarProdutoResgateFaultMsg fex) {
			System.out.println(fex.getFaultInfo().getCodigo() + 
					": FaultMsg while calling ConsultarProdutoResgate for loyaltycode:" + loyaltyCode);
			throw fex;
		} catch (ErroTecnicoFaultMsg tex) {
			System.out.println(tex.getFaultInfo().getCodigo() + 
					": TechFaultMsg while calling ConsultarProdutoResgate for loyaltycode:" + loyaltyCode);
			throw tex;
		} catch (Exception ex) {			
			System.out.println(ex + ": while calling ConsultarParticipante for loyaltycode:" + loyaltyCode+ ex);
			returnMap = new HashMap<String, Object>();
			returnMap.put(RETURN_CODE, Integer.valueOf(TECH_ERROR));
			return returnMap;			
		}

		returnMap = messageAdapter.adaptResponseConsultarProdutoResgateOutput(outputRes, loyaltyCode);	
		System.out.println("Exiting fetchConversionRate");				
		return returnMap;
	}
	
	/**
	 * 
	 * @return
	 */
	public ConsultarProdutoResgatev1 getClient() {
		return client;
	}
	/**
	 * 
	 * @param client
	 */
	public void setClient(ConsultarProdutoResgatev1 client) {
		this.client = client;
	}
	/**
	 * 
	 * @return
	 */
	public ConsultarProdutoResgatev1PortType getPort() {
		return port;
	}
	/**
	 * 
	 * @param port
	 */
	public void setPort(ConsultarProdutoResgatev1PortType port) {
		this.port = port;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isInitialised() {
		return initialised;
	}
	/**
	 * 
	 * @param initialised
	 */
	public void setInitialised(boolean initialised) {
		this.initialised = initialised;
	}
	/**
	 * 
	 * @return
	 */
	public ConversionRateMessageAdapter getProfileMessageAdapter() {
		return messageAdapter;
	}
	/**
	 * 
	 * @param messageAdapter
	 */
	public void setProfileMessageAdapter(ConversionRateMessageAdapter messageAdapter) {
		this.messageAdapter = messageAdapter;
	}
	/**
	 * 
	 * @return
	 */
	public Integer getInterfaceId() {
		return interfaceId;
	}
	/**
	 * 
	 * @param interfaceId
	 */
	public void setInterfaceId(Integer interfaceId) {
		this.interfaceId = interfaceId;
	}
	
}
