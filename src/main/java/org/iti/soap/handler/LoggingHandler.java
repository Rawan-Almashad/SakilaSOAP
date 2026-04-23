package org.iti.soap.handler;

import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;
import java.util.Set;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        try {
            if (outbound) {
                System.out.println("Outgoing Response:");
            } else {
                System.out.println("Incoming Request:");
            }

            context.getMessage().writeTo(System.out);
            System.out.println("\n-----------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true; // continue chain
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        System.out.println("SOAP Fault occurred");
        return true;
    }

    @Override
    public void close(MessageContext context) {}

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}