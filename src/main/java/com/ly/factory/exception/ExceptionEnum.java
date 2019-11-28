package com.ly.factory.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    PRICE_CANNOT_BE_NULL(400,"价格不能为空！"),
    CATEGORY_NOT_FOND(404,"商品分类没查到"),
    PAY_STATUS_ERROR(500,"订单状态异常"),
    INVALID_SIGN_ERROR(400,"无效的的签名"),
    INVALID_ORDER_PARAM(400,"订单参数异常"),
    UPDATE_ORDER_STATUS_ERROR(500,"更新订单状态失败"),
    PHONE_CODE_ERROR(400,"短信验证码不正确"),
    PHONE_IS_EXIST(400,"该手机号已被注册！"),
    AUTH_USERNAME_NONE(400,"用户名或密码不正确"),
    AUTH_LOGIN_TOKEN_SAVEFAIL(400,"存储令牌失败"),
    AUTH_LOGIN_APPLYTOKEN_FAIL(400,"申请令牌失败")

    ;
    private Integer code;
    private String msg;

}
