package com.karolmalysa.insurancecenter.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClaimStatus {
    SUBMITTED("Submitted"),
    INVESTIGATION("Investigation"),
    APPROVED("Approved"),
    DENIED("Denied");

    private final String displayName;

  
}
