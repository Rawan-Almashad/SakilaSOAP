package org.iti.soap.handler;

import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;
import java.util.Iterator;
import java.util.Set;

public class SecurityHandler implements SOAPHandler<SOAPMessageContext> {

    private static final String VALID_KEY = "12345";

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        Boolean outbound =
                (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (!outbound) {

            try {
                var header = context.getMessage().getSOAPHeader();

                if (header == null) {
                    throw new RuntimeException("Missing SOAP header");
                }

                String apiKey = null;

                var it = header.examineAllHeaderElements();

                while (it.hasNext()) {
                    var element = it.next();

                    if (element.getLocalName().equals("apiKey")) {
                        apiKey = element.getTextContent();
                        break;
                    }
                }

                if (apiKey == null) {
                    throw new RuntimeException("Missing API Key");
                }

                if (!VALID_KEY.equals(apiKey)) {
                    throw new RuntimeException("Invalid API Key");
                }

            } catch (Exception e) {
                throw new RuntimeException("Unauthorized SOAP request", e);
            }
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {}

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}