package com.hewen.service.impl;

import com.hewen.entity.constants.Constants;
import com.hewen.entity.po.PhoneCode;
import com.hewen.entity.query.PhoneCodeQuery;
import com.hewen.mappers.PhoneCodeMapper;
import com.hewen.service.PhoneCodeService;
import com.hewen.utils.AliyunPhoneCode;
import com.hewen.utils.TenxunSms;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("phoneCodeService")
public class PhoneCodeServiceImpl implements PhoneCodeService {

    @Resource
    private PhoneCodeMapper<PhoneCode, PhoneCodeQuery> phoneCodeMapper;


    @Override
    public void savePhoneCode(String phone,String code) throws Exception {
        PhoneCode phoneCode = new PhoneCode();
        phoneCode.setPhone(phone);
        phoneCode.setCode(code);
        phoneCode.setStatus(Constants.PHONE_CODE_STATUS_0);
        phoneCode.setCreateTime(new Date());
        this.phoneCodeMapper.insert(phoneCode);
        //发送短信
        TenxunSms tenxunSms = new TenxunSms();
        tenxunSms.sendSmsCode(phone,code);
    }


}
