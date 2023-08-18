package com.karolmalysa.insurancecenter.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoles {
    ADMIN("Admin"),
    USER("User"),
    CLIENT("Client"),
    CLIENT_PREMIUM("Client Premium");

    private String displayRole;

  
}
