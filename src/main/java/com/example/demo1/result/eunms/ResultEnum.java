package com.example.demo1.result.eunms;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultEnum {

    // 统一结果返回码
    SUCCESS(0, "成功"),
    RETRY(-1, "请求失败,请稍后重试"),
    NO_VALID_PARAM(1, "请求参数有误"),
    FAIL(400, "请求失败"),
    NOT_ORDINARY_DEVICE(401, "非常用设备登录"),
    AUTH_ERROR(403, "认证失败"),
    NO_HANDLER(404, "接口不存在"),
    SERVER_ERROR(500, "服务器内部错误"),
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION(501, "缺少servlet请求参数"),

    HTTP_MESSAGE_NOT_READABLE_EXCEPTION(502, "请求参数不能正确读取解析"),

    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(504, "请求参数无效"),

    METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION(505, "方法请求参数类型不匹配"),

    CONSTRAIN_VIOLATION_EXCEPTION(506, "javax.validation:validation-api 校验参数抛出的异常"),

    VALIDATION_EXCEPTION(507, "javax.validation 下校验参数时抛出的异常"),

    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(508, "不支持该请求方法"),

    HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION(509, "不支持当前媒体类型"),


    // 前端业务异常返回码
    ERROR_LOGIN(10001, "登录账号或密码错误"),
    NO_EXIST_USER(10002, "用户不存在"),
    ERROR_ID(10003, "ID不存在"),
    ERROR_NO(10004, "NO不存在"),
    ERROR_SIGN(10005, "签名认证失败"),
    ERROR_INTERFACE(10006, "第三方接口调用失败"),

    INTEGRAL_ERROR_AUTO(50001, "不能进行积分操作"),
    INTEGRAL_ERROR_LIMIT(50002, "操作超过限定数量"),
    INTEGRAL_ERROR_BALANCE(50003, "余额不足"),
    SIGNED_IN_TODAY(50004, "今日已签到"),
    RULE_IS_DISABLED(50005, "该规则已禁用"),
    INTEGRAL_SUCCESS(20001, "成功"),
    INTEGRAL_SUCCESS_REFRESH(2000101, "刷新后重试"),
    ;

    private Integer code;

    private String message;

}
