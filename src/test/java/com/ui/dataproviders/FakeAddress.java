package com.ui.dataproviders;

import com.ui.pojo.AddressPojo;
import net.datafaker.Faker;

import java.util.Locale;

public class FakeAddress {

    public static void main(String[] args) {
        getFakeAddress();
    }

    public static AddressPojo getFakeAddress(){
        Faker faker = new Faker(Locale.US);

        AddressPojo addressPojo = new AddressPojo(
                faker.company().name(),
                faker.address().buildingNumber(),
                faker.address().streetAddress(),
                faker.address().cityName(),
                faker.address().zipCode(),
                faker.phoneNumber().phoneNumber(),
                faker.phoneNumber().cellPhone(),
                "Other Information",
                "Office Address",
                faker.address().state());

        return addressPojo;
    }
}
