package com.example.education.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Regex {
    EMAIL_REGEX("^(?!\\.)[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{1,64}(?<!\\.)@[a-zA-Z0-9-]{1,255}(?:\\.[a-zA-Z0-9-]{2,63})+$"),
    PASSWORD_REGEX("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[_\\W]).{8,64}$"),
    PHONE_NUMBER_REGEX("^\\+[1-9]\\d{1,14}$"),
    USERNAME_PATTERN("^(?=.{1,32}$)(?!.*[.]{2})(?![.])(?!\\\\d+$)[a-zA-Z0-9_.]+(?<![.])$");
    private final String validation;
}
