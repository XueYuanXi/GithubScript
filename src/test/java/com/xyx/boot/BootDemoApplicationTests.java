package com.xyx.boot;

import com.xyx.boot.dto.Order;
import com.xyx.boot.dto.Product;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BootDemoApplicationTests {

    /**
     * 测试Drools规则引擎
     */
    @Test
    void testDrools() {
        // 1. 获取 Kie 容器
        KieServices kieServices = KieServices.Factory.get();
        assertNotNull(kieServices, "KieServices 初始化失败！");
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        // 2. 创建 KieSession
        KieSession kieSession = kieContainer.newKieSession("rulesKSession");

        // 3. 插入事实数据
        Product product = new Product();
        product.setType(Product.GOLD);
        product.setDiscount(10);
        kieSession.insert(product);

        // 4. 触发规则执行
        kieSession.fireAllRules();

        // 5. 关闭会话
        kieSession.dispose();
    }
}
