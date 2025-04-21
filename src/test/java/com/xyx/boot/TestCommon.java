package com.xyx.boot;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class TestCommon {

    /**
     *测试把反斜杠换成正斜杠
     */
    @Test
    void testReplaceSlash() {
        try{
            String path = "C:\\Users\\Administrator\\Desktop\\test.txt";
            log.info("path:{}",path.replace("\\","/"));
            log.info("path1:{}",path.replaceAll("\\\\","/"));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }

    @Test
    void testRsa() {
        // 生成公钥和私钥
        RSA rsa = new RSA();
        // 公钥
        String publicKey = rsa.getPublicKeyBase64();
        // 私钥
        String privateKey = rsa.getPrivateKeyBase64();
        System.out.println("Public Key: " + publicKey);
        System.out.println("Private Key: " + privateKey);
        // 要加密的数据
        String data = "Hello, Hutool! This is a test message.";
        // 使用公钥加密
        String encryptedData = rsa.encryptBase64(data, KeyType.PublicKey);  // false 表示使用公钥加密
        System.out.println("Encrypted Data: " + encryptedData);

        // 使用私钥解密
        String decryptedData = rsa.decryptStr(encryptedData, KeyType.PrivateKey);  // true 表示使用私钥解密
        System.out.println("Decrypted Data: " + decryptedData);
    }

    /**
     * 测试Easy Rule规则引擎
     */
    @Test
    void testEasyRule(){
        // define facts
        Facts facts = new Facts();
        facts.put("rain", true);

        // define rules
        Rule weatherRule = new RuleBuilder()
                .name("weather rule")
                .description("if it rains then take an umbrella")
                .when(facts1 -> facts1.get("rain").equals(true))
                .then(facts1 -> System.out.println("It rains, take an umbrella!"))
                .build();
        Rules rules = new Rules();
        rules.register(weatherRule);

        // fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }
}
