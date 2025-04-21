package com.xyx.boot;

import com.xyx.boot.dto.Order;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DroolsTests {
    @Autowired
    private KieBase kieBase;

    @Test
    public void droolsTest(){
        Order order = new Order();
        order.setAmount(200);
        //创建会话对象，用于和规则交互的引擎
        KieSession kieSession = kieBase.newKieSession();
        //讲数据交给规则引擎，规则迎请会根据提供的数据进行规则匹配
        kieSession.insert(order);
        //激活规则引擎，如果匹配成功则执行
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
        System.out.println("订单金额："+order.getAmount()+",订单积分："+order.getScore());
        System.out.println("规则执行完毕");
    }
}
