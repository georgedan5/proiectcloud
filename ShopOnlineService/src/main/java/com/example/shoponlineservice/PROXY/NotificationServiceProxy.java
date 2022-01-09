package com.example.shoponlineservice.PROXY;

import com.example.shoponlineservice.DOMAIN.dto.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificationService", url = "${app.notification.service.url}")
public interface NotificationServiceProxy {

    @GetMapping("/api/test")
    String getTestMessage();

    @PostMapping("api/email")
    void sendNotifications(@RequestBody EmailRequest request);
}
