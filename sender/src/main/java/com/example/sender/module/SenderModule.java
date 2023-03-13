package com.example.sender.module;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class SenderModule {

    final RabbitTemplate rabbitTemplate;
    private static final String topicExchange = "spring-boot-exchange";

    // @Scheduled(fixedRate = 1000)
    public void sender1() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        String time = nowDateTime.toString();
        System.out.println("==> + " + time);
        rabbitTemplate.convertAndSend("time", "time-first", time);
        // rabbitTemplate.convertAndSend(topicExchange, "foo.bar.baz", "Hello Message!");
    }

    @Scheduled(fixedRate = 1000)
    public void sender2() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        String time = nowDateTime.toString();
        System.out.println("==> + " + time);

        Dept dept = new Dept(10, "test", "test", time);
        System.out.println("dept = " + dept.toString());
        rabbitTemplate.convertAndSend("dept", "dept-first", dept);
    }

}
