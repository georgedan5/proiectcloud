package com.example.shoponlineservice.SERVICES;

import com.example.shoponlineservice.DOMAIN.Orderr;
import com.example.shoponlineservice.DOMAIN.Product;
import com.example.shoponlineservice.DOMAIN.dto.EmailRequest;
import com.example.shoponlineservice.PROXY.NotificationServiceProxy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationServiceProxy notificationServiceProxy;

    public void sendNotificationToUser(final Orderr orderr) {
        String products = orderr.getProds().stream().map(Product::getName).collect(Collectors.joining(", "));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String message = String.format("At time %s, the order number %s was sent to customer %s, " +
                "employee %s, with the following observation %s and state %s. The following products were ordered: %s",
                formatter.format(orderr.getDate()), orderr.getId().toString(), orderr.getCustomer().getFirstName() + " " + orderr.getCustomer().getLastName(),
                orderr.getEmployee().getFirstName() + " " + orderr.getEmployee().getLastName(), orderr.getObservation(), orderr.getState(), products);
        EmailRequest emailRequest = EmailRequest.builder()
                .userEmailAddress(getLoggedInUser())
                .otherUserAddresses(List.of())
                .message(message)
                .build();

        notificationServiceProxy.sendNotifications(emailRequest);
    }

    private String getLoggedInUser() {
        String userDetails = ServiceUtil.getUserDetails();
        log.info(userDetails);
        return userDetails;
    }
}
