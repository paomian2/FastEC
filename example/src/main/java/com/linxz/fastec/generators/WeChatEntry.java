package com.linxz.fastec.generators;

import com.linxz.latte.wechat.tempates.WXEntryTemplate;
import com.linxz.latte_annotations.annotions.EntryGenerator;

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
@EntryGenerator(
        packageName = "com.linxz.fastec",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
