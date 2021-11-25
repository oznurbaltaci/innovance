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
    EMAIL_EXIST(1004,"Bu email adresi daha önce alınmıştır."),
    LOGIN_REQUEST_INCORRECT(1005,"Kullanıcı adı veya şifre yanlış."),
    USER_DELETED(1006,"Kullanıcı pasifte."),
    USER_SESSION_NOT_FOUN(1007,"Kullanıcı oturumu bulunamadı."),
    USER_ACCOUNT_NOT_FOUND(1008,"Kullanıcı hesabı bulunamadı."),
    USER_ALREADY_LOGIN(1009,"Kullanıcı oturumu zaten açık."),
    USER_PROFILE_NOT_FOUND(1010, "Kullanıcı profil bilgileri bulunamadı."),
    POST_STORE_FAILED(1011,"Gönderi kaydedilirken bir sorun oluştu. "),
    POST_NOT_FOUND(1012,"Gönderi bulunamadı."),
    WROND_HTTP_METHOD(1013,"Yanlış HTTP metodu kullandınız. Lütfen kontrol edin!"),
    ACCOUNT_NOT_VERIFIED(1014,"Kullanıcı hesabı henüz doğrulanmadı."),
    MAIL_ERROR(1015,"Mail gönderiminde hata oluştu!"),
    VERIFICATION_INCORRECT(1016,"Doğrulama kodu veya mail yanlış."),
    ALL_TIMELINE_GET(1017,"Bütün gönderiler timeline'da gösterildi."),
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
