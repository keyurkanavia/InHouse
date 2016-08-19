package com.objectedge.soapWS;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFactory;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SecurityHeaderSOAPHandler implements SOAPHandler<SOAPMessageContext> {

	
	// prefixes
	private static final String AUTH_PREFIX_WSSE = "wsse";
	private static final String AUTH_PREFIX_WSU = "wsu";
	
	// namespaces
	private static final String AUTH_SECURITY_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private static final String AUTH_UTILITY_NS =  "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
	private static final String AUTH_PASSWORD_NS =  "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText";	

	public boolean handleMessage(SOAPMessageContext context) {
		try {
			SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
			SOAPFactory factory = SOAPFactory.newInstance();
			SOAPElement securityHeaderElement = factory.createElement("Security", AUTH_PREFIX_WSSE, AUTH_SECURITY_NS);
			
			Name securityMustUnderstandAttribute = factory.createName("mustUnderstand", AUTH_PREFIX_WSU, AUTH_SECURITY_NS);
			securityHeaderElement.addAttribute(securityMustUnderstandAttribute, "1");
			
			SOAPElement usernameTokenElement = factory.createElement("UsernameToken", AUTH_PREFIX_WSSE, AUTH_SECURITY_NS);
			Name userNameTokenIdName = factory.createName("id", AUTH_PREFIX_WSU, AUTH_UTILITY_NS);
			usernameTokenElement.addAttribute(userNameTokenIdName, "unt_uHZ95Zwnftr5YPCL");
			
			SOAPElement usernameElement = factory.createElement("Username", AUTH_PREFIX_WSSE, AUTH_SECURITY_NS);
			
			usernameElement.addTextNode((String)context.get(BindingProvider.USERNAME_PROPERTY));
			
			SOAPElement passwordElement = factory.createElement("Password", AUTH_PREFIX_WSSE, AUTH_SECURITY_NS);
			Name passwordTypeAttribute = factory.createName("Type");
			passwordElement.addAttribute(passwordTypeAttribute, AUTH_PASSWORD_NS);
			passwordElement.addTextNode((String)context.get(BindingProvider.PASSWORD_PROPERTY));
			
			usernameTokenElement.addChildElement(usernameElement);
			usernameTokenElement.addChildElement(passwordElement);
			securityHeaderElement.addChildElement(usernameTokenElement);
			
			if (envelope.getHeader() == null) {
				envelope.addHeader().addChildElement(securityHeaderElement);
			} else {
				envelope.getHeader().addChildElement(securityHeaderElement);
			}
			
		} catch (Throwable e) {
			System.out.println(e.getMessage()+ e);
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(MessageContext context) {

	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}
}