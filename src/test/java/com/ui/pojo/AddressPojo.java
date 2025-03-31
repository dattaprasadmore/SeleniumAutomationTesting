package com.ui.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressPojo {
    private String company;
    private String addressLine1;
    private String addressLine2;
    private String City;
    private String postCode;
    private String homePhoneNumber;
    private String mobileNumber;
    private String otherInformation;
    private String addressAlias;
    private String state;
}