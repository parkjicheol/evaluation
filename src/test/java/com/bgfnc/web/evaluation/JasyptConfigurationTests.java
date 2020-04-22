package com.bgfnc.web.evaluation;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptConfigurationTests {

    final static String KEY = "bugunfnc2020!";
    final static String ALGORITHM = "PBEWithMD5AndDES";

    @Test
    public void test() {
        StandardPBEStringEncryptor  standardPBEStringEncryptor = new StandardPBEStringEncryptor();

        standardPBEStringEncryptor.setAlgorithm(ALGORITHM);
        standardPBEStringEncryptor.setPassword(KEY);

        String enc = standardPBEStringEncryptor.encrypt("bugunfnc!");
        System.out.println("end = " + enc);

        String des = standardPBEStringEncryptor.decrypt(enc);
        System.out.println("des = " + des);
    }

}
