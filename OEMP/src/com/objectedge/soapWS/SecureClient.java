/**
 * 
 */
package com.objectedge.soapWS;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

/**
 * @author Object Edge Inc.
 *
 */
public class SecureClient {
	
	private String username;
	private String password;
	private String wsdl;
	
	/**
	 * @param wsdl
	 */
	protected void addSecurityParamsToChain(BindingProvider portType) {
		
		// add SOAP logging handler and security header handler
		BindingProvider bindingProvider = (BindingProvider) portType;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdl);
		Binding binding = bindingProvider.getBinding();
		List<Handler> handlerChain = binding.getHandlerChain();
		// add custom handler for WS-Security
		handlerChain.add(new SecurityHeaderSOAPHandler());
		// define logging
		handlerChain.add(new SOAPLoggingHandler());
		binding.setHandlerChain(handlerChain);
		
		bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, getUsername());
		bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, getPassword());
		
		// security binding
		addSecurityBinding(bindingProvider);
	}
	
	private void addSecurityBinding(BindingProvider port) {
		BindingProvider bindingProvider = (BindingProvider) port;
		bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, getUsername());
		bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, getPassword());
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getWsdl() {
		return wsdl;
	}

	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
	}
}
