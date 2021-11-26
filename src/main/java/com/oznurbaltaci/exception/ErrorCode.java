package com.oznurbaltaci.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    GENERAL_EXCEPTION(1000, "Sistemde bir hata oluştu."),
    ARGUMENT_NOT_VALID(1001, "Zorunlu parametreler boş olamaz"),
    USER_NOT_FOUND(1002,"Kullanıcı bulunamadı."),
    TOKEN_NOT_FOUND(1003,"Kullanıcı bulunamadı."),
    UNAUTHORIZED(401,"İşlem yapabilmeniz için giriş yapmanız gerekmektedir."),
    USERNAME_EXIST(1003,"Bu kullanıcı adı daha önce alınmıştır."),
    USER_ACCOUNT_NOT_FOUND(1008,"Kullanıcı hesabı bulunamadı."),
    WROND_HTTP_METHOD(1013,"Yanlış HTTP metodu kullandınız. Lütfen kontrol edin!"),
    MISSING_HEADER(1018,"Eksik Request Header. Lütfen kontrol ediniz."),
    MISSING_PARAM(1019,"Eksik Request Parameter. Lütfen kontrol ediniz."),
    FACEBOOK_ERROR(1020,"Facebook servisinde bir hata oluştu."),
    USER_ACCOUNT_ERROR(1021,"Kullanici hesabi bulunamadi."),
    CURRENCY_ERROR(1022,"Para birimleri eslesmedi."),
    AMOUNT_ERROR(1022,"Bakiye gonderilen tutardan kucuk olamaz."),
    ACCOUNT_ERROR(1023,"Hesap bulunamadi."),
    USER_NOT_ACCOUNT_ERROR(1024,"Hesap kullaniciya ait degil."),
    IDENTITY_ERROR(1025,"Bu tc kimlik nolu kullanici bulunamadi.");

    private int code;
    private String message;
}
