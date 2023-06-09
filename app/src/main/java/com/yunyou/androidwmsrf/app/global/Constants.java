package com.yunyou.androidwmsrf.app.global;

import com.yunyou.androidwmsrf.mvp.model.api.Api;

/**
 * Author:      yunyou
 * Date:        2018-05-29 09:11
 * Description: 常量
 */
public interface Constants {

    // 是否播放警告音
    boolean IS_PLAY_SOUND = true;
    // userId
    String USER_ID_KEY = "USER_ID_KEY";
    String USER_SESSION_ID = "USER_SESSION_ID";
    String USER_NAME = "USER_NAME";
    String MAC_ADDRESS = "MAC_ADDRESS";
    // userId（默认空值）
    String USER_ID_DEFAULT_VALUE = "";

    String INTENT_PARAMETER_ASN = "INTENT_PARAMETER_ASN";
    String INTENT_PARAMETER_ASN_DETAIL = "INTENT_PARAMETER_ASN_DETAIL";
    String INTENT_PARAMETER_TRAY_OR_SKU = "INTENT_PARAMETER_TRAY_OR_SKU";
    String INTENT_PARAMETER_PA_TASK_INFO = "INTENT_PARAMETER_PA_TASK_INFO";
    String INTENT_PARAMETER_PICK_DETAIL_INFO = "INTENT_PARAMETER_PICK_DETAIL_INFO";
    String INTENT_PARAMETER_PICK_DETAIL = "INTENT_PARAMETER_PICK_DETAIL";
    String INTENT_PARAMETER_RP_DETAIL = "INTENT_PARAMETER_RP_DETAIL";
    String INTENT_PARAMETER_COUNT_NO = "INTENT_PARAMETER_COUNT_NO";
    String INTENT_PARAMETER_TASK_COUNT_DETAIN_INFO = "INTENT_PARAMETER_TASK_COUNT_DETAIN_INFO";
    String INTENT_PARAMETER_TASK_COUNT_DETAIN = "INTENT_PARAMETER_TASK_COUNT_DETAIN";
    String INTENT_PARAMETER_TASK_COUNT_OWNER = "INTENT_PARAMETER_TASK_COUNT_OWNER";
    String INTENT_PARAMETER_TASK_COUNT_OWNER_INFO = "INTENT_PARAMETER_TASK_COUNT_OWNER_INFO";
    String INTENT_PARAMETER_INV_SKU_INFO = "INTENT_PARAMETER_INV_SKU_INFO";
    String INTENT_PARAMETER_INV_SKU = "INTENT_PARAMETER_INV_SKU";
    String INTENT_PARAMETER_INV_PICK_BOX_HEADER_INFO = "INTENT_PARAMETER_INV_PICK_BOX_HEADER_INFO";
    String INTENT_PARAMETER_INV_PICK_BOX_DETAIL_INFO = "INTENT_PARAMETER_INV_PICK_BOX_DETAIL_INFO";
    String INTENT_PARAMETER_INV_PICK_BOX_QUERY_ID = "INTENT_PARAMETER_INV_PICK_BOX_QUERY_ID";
    // 服务器地址
    String APP_DOMAIN_KEY = "APP_DOMAIN_KEY";
    // 服务器地址默认值
    String APP_DOMAIN_DEFAULT_VALUE = Api.APP_DOMAIN;
    // 必填*
    String required_start = "<font color=\"#ff0000\">*</font>";
}