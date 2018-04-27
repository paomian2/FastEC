package com.linxz.fastec.generators;

import com.linxz.latte.wechat.tempates.WXPayEntryTemplate;
import com.linxz.latte_annotations.annotions.PayEntryGenerator;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月27日0:27  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
@PayEntryGenerator(
        packageName = "com.linxz.fastec",
        entryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
