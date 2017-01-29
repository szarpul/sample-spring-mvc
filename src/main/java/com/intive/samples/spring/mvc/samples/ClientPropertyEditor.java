package com.intive.samples.spring.mvc.samples;


import java.beans.PropertyEditorSupport;


public class ClientPropertyEditor extends PropertyEditorSupport {

    private Client client;

    @Override
    public String getAsText() {
        return Client.convertToString((Client) getValue());
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
     setValue(Client.convertToObject(text));
    }

}

