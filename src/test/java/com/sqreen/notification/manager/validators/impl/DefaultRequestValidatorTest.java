package com.sqreen.notification.manager.validators.impl;

import com.sqreen.notification.manager.validators.RequestValidator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application–test.properties")
public class DefaultRequestValidatorTest {

    @Autowired
    private RequestValidator requestValidator;

    private static final String REQUEST_SIGNATURE = "f6787fd82534899d0644db6dd4de2bc75eb61ba6ea2638c64776e19f4070fdbd";

    @Test
    void checkSignatureTest() {
        Assert.assertTrue(requestValidator.checkSignature(REQUEST_SIGNATURE));
    }
}
