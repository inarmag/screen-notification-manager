package com.sqreen.notification.manager.validators.impl;

import com.google.common.hash.Hashing;
import com.sqreen.notification.manager.validators.RequestValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class DefaultRequestValidator implements RequestValidator {

    @Value("${sqreen.webhook.secret.key}")
    private String webhookSecretKey;

    @Override
    public boolean checkSignature(String requestSignature) {

        String sha256hexRequestSignature = Hashing.sha256()
                .hashString(requestSignature, StandardCharsets.UTF_8)
                .toString();

        return DigestUtils.sha256Hex(webhookSecretKey).equals(sha256hexRequestSignature);
    }


}
