package com.example.shoponlineservice.DOMAIN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class EmailRequest {

    private String userEmailAddress;
    private String message;
    private List<String> otherUserAddresses;
}
