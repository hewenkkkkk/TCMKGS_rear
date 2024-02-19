package com.hewen.controller;


import com.hewen.entity.constants.Constants;
import com.hewen.entity.vo.ResponseVO;
import com.hewen.service.PhoneCodeService;
import com.hewen.utils.VerificationCodeGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("phoneCodeController")
@RequestMapping("phoneCode")
public class PhoneCodeController extends ABaseController{


    @Resource
    private PhoneCodeService phoneCodeService;

    /**
     * 生成验证码
     {
        "phone": “手机号”,
     }
     */
    @GetMapping("sendCode")
    public ResponseVO sendCode(@RequestParam("phone") String phone) throws Exception {
        String code = VerificationCodeGenerator.generateVerificationCode(Constants.LENGTH_6);
        phoneCodeService.savePhoneCode(phone, code);
        return getSuccessResponseVO(null);
    }
}
