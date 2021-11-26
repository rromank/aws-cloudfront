package com.example.aws;

import com.amazonaws.services.cloudfront.CloudFrontUrlSigner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;
import java.util.Date;

//@SpringBootTest
class AwsApplicationTests {


    @Test
    void contextLoads() throws Exception {
        PrivateKey privateKey = PrivateKeyReader.get("/Users/roman.riznyk/programming/aws/src/main/resources/private_key.der");

        Date expirationDate = new Date(System.currentTimeMillis() + 60 * 1000);

        String policy = CloudFrontUrlSigner.buildCustomPolicyForSignedUrl(
                "https://roman-riznyk-test-bucket-1.s3.eu-central-1.amazonaws.com/49fb8171d7cbcc14fd6c085c384c5c75.jpeg",
                expirationDate,
                null,
                null
        );

        String signedUrl = CloudFrontUrlSigner.getSignedURLWithCustomPolicy(
                "https://roman-riznyk-test-bucket-1.s3.eu-central-1.amazonaws.com/49fb8171d7cbcc14fd6c085c384c5c75.jpeg",
                "c4b00fa0-679c-4fca-8964-22779517de11",
                privateKey,
                policy
        );

        System.out.println(signedUrl);
    }

}
