package com.hewen.mappers;

public interface PhoneCodeMapper <T,P> extends BaseMapper<T,P>{

    String selectCodeByPhone(String phone);

    void updateStatusByCode(String code);
}
