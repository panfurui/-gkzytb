package com.demo.config.rest;

public enum RestErrorEnum {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /*-----------------------------未知类型错误--------------------------*/
    //未知错误
    UNKNOWN(1001, "未知错误"),

    /*-----------------------------认证类型错误--------------------------*/
    /**
     * 无效AccessToken
     */
    INVALID_ACCESS_TOKEN(1002, "无效的认证信息"),
    INVALID_USERNAME(1003, "无效的用户名"),
    INVALID_PASSWORD(1004, "无效的密码"),

    /*-----------------------------参数错误--------------------------*/
    //参数为空错误
    PARAMETERS_MISSING(1100, "缺少参数"),
    //参数值错误
    PARAMETERS_ERROR(1101, "参数错误"),
    //参数值错误


    /*-----------------------------资源类型错误--------------------------*/
    //无效资源
    RESOURCE_INVALID(1201, "无效的资源"),
    //无资源访问权限
    RESOURCE_NO_PERMISSION(1202, "无权限访问资源"),
    //待删除的资源正在使用中
    RESOURCE_IN_USE(1202, "资源正在使用"),
    //资源不存在
    RESOURCE_DOES_NOT_EXIST(1203, "资源不存在"),

    /*-----------------------------物料错误--------------------------*/
    MATERIAL_MISSING(1300, "缺少必要物料"),
    MATERIAL_DOES_NOT_EXIST(1301, "物料不存在"),
    MATERIAL_DOES_NOT_BIND(1302, "未绑定物料"),
    MATERIAL_DUPLICATE_CODE(1302, "重复的物料编码"),
    MATERIAL_DUPLICATE_NAME(1303, "重复的物料名称"),
    MATERIAL_IN_USE(1304, "物料正被使用中，请先将车道取消激活"),
    MATERIAL_DUPLICATE_IP(1305, "重复的物料IP"),
    MATERIAL_ERROR_IP(1306, "物料IP格式错误"),
    MATERIAL_ERROR_PORT(1307, "物料端口格式错误"),
    MATERIAL_ERROR_MODEL(1308, "物料类型格式错误"),

    /*-----------------------------流程错误--------------------------*/
    FLOW_MISSING(1400, "缺少必要流程"),
    FLOW_DOES_NOT_EXIST(1401, "流程不存在"),
    FLOW_DOES_NOT_BIND(1402, "未绑定流程"),
    FLOW_BOOT_FAILED(1403, "流程启动失败"),

    FLOW_CHECK_CAR_ERROR(1404, "车辆检测异常"),
    FLOW_ORDER_INFO_ERROR(1405, "订单信息异常"),
    FLOW_CAR_POSITION_ERROR(1406, "车辆停靠位置偏差"),
    FLOW_BING_NOT_SUPPORT(1407, "车辆无匹配垛型"),

    /*-----------------------------车道错误--------------------------*/
    LINE_DUPLICATE_CODE(1501, "重复的车道编码"),
    LINE_DUPLICATE_NAME(1502, "重复的车道名称"),
    LINE_IN_USE(1503, "车道正在使用中，请先将车道取消激活"),


    SCHOOL_DUPLICATE_NAME(1601, "学校名已存在"),
    DUPLICATE_FAVOURITE_MAJOR(1601, "重复添加感兴趣专业"),
    DUPLICATE_SCHOOL_MAJOR(1601, "重复添加同类型专业"),
    ERROR_LINE(1601, "该区域无对应年份分数线"),
    ERROR_LINE1(1601, "该区域2019年份分数线"),
    DUPLICATE_APPLY_MAJOR(1601, "重复添加志愿"),
    PROJECT_DUPLICATE_USERNAME(1701, "用户名已存在")
            ;


    private Integer code;
    private String description;

    RestErrorEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static RestErrorEnum codeOf(int code) {
        for (RestErrorEnum errorEnum : RestErrorEnum.values()) {
            if (code == errorEnum.getCode()) {
                return errorEnum;
            }
        }
        return UNKNOWN;
    }
}
