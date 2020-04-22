package com.bgfnc.web.evaluation;

import com.bgfnc.web.evaluation.common.util.EncoderUtil;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EvaluationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EvaluationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        StandardPBEStringEncryptor pbeStringEncryptor = new StandardPBEStringEncryptor();
        pbeStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        pbeStringEncryptor.setPassword("bugunfnc2020!");

        String enc = pbeStringEncryptor.encrypt("jdbc:mysql://13.124.211.149:3306/evaluation?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC");
        System.out.println("url = " + enc);

        enc = pbeStringEncryptor.encrypt("root");
        System.out.println("username = " + enc);

        enc = pbeStringEncryptor.encrypt("Oneshot0713!");
        System.out.println("password = " + enc);

        enc = EncoderUtil.encodeSha256("Oneshot0713!");
        System.out.println("encode = " + enc);
    }
}
