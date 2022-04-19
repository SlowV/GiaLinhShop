package com.slowv.fruit.service.dto.response;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserPreferLocaleResponse implements Serializable {
    private String language;
    private String country;
    private String currency;

    public UserPreferLocaleResponse setLanguage(String language) {
        this.language = language;
        return this;
    }

    public UserPreferLocaleResponse setCountry(String country) {
        this.country = country;
        return this;
    }

    public UserPreferLocaleResponse setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
}
