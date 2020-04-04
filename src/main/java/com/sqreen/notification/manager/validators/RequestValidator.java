package com.sqreen.notification.manager.validators;

public interface RequestValidator {

    boolean checkSignature(String requestSignature);

}
