package com.objectedge.soapWS;

import java.io.ByteArrayOutputStream;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext> {

		
    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext messageContext) {
        logToSystemOut(messageContext);
        return true;
    }

    public boolean handleFault(SOAPMessageContext messageContext) {
        logToSystemOut(messageContext);
        return true;
    }

    public void close(MessageContext messageContext) {

    }

    private void logToSystemOut(SOAPMessageContext messageContext) {
        Boolean outboundProperty = (Boolean) messageContext.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);

	        if (outboundProperty.booleanValue()) {
	        	System.out.println("Outbound message:");
	        } else {
	        	System.out.println("Inbound message:");
	        }
        
        try {
        	ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        	messageContext.getMessage().writeTo(byteStream);
        	
        	System.out.println(byteStream);
        		
        } catch (Exception e) {
        	System.out.println("Exception in handler: " + e.getMessage()+ e);
        }
    }
}
