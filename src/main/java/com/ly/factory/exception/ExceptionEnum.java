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
    BRAND_NOT_FOUND(404, "品牌不存在"),
    BRAND_SAVE_ERROR(500,"新增品牌失败" ),
    UPLOAD_FILE_ERROR(500,"文件上传失败" ),
    INVALID_FILE_TYPE(400,"文件类型不匹配" ),
    SPEC_GROUP_NOT_FOND(404, "商品规格组不存在"),
    SPEC_PARAM_NOT_FOND(404,"商品规格参数不存在" ),
    GOODS_NOT_FOUND(404,"商品不存在" ),
    SAVE_GOODS_FAIL(500,"新增商品失败" ),
    DETAIL_NOT_FOUND(404,"查询商品详情失败" ),
    SKU_NOT_FOUND(404,"查询商品sku失败" ),
    STOCK_NOT_FOUND(404,"商品库存查询失败" ),
    GOODS_ID_CANNOT_BE_NULL(404,"商品id不能为空"),
    GOODS_UPDATE_ERROR(500,"商品修改失败" ),
    INVALID_USER_DATA_TYPE(400,"用户数据类型无效"),
    INVALID_USERNAME_PASSWORD(400,"用户名或密码错误" ),
    UNAUTHORIZED(403,"未授权"),
    CAR_NOT_FOUND(404,"购物车为空"),
    CREATE_ORDER_ERROR(500,"创建订单失败" ),
    STOCK_NOT_ENOUGH(500 ,"库存不足"),
    ORDER_NOT_FOUND(404,"订单不存在！"),
    ORDER_DETAIL_NOT_FOUND(404,"订单详情不存在！"),
    ORDER_STATUS_NOT_FOUND(404,"订单状态不存在！"),
    WX_PAY_ORDER_FAIL(500,"微信下单失败"),
    PAY_STATUS_ERROR(500,"订单状态异常"),
    INVALID_SIGN_ERROR(400,"无效的的签名"),
    INVALID_ORDER_PARAM(400,"订单参数异常"),
    UPDATE_ORDER_STATUS_ERROR(500,"更新订单状态失败"),
    PHONE_CODE_ERROR(400,"短信验证码不正确")
    ;
    private Integer code;
    private String msg;

}
