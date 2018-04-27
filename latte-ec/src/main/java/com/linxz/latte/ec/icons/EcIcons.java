package com.linxz.latte.ec.icons;

import com.joanzapata.iconify.Icon;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月05日0:01  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public enum EcIcons implements Icon{
    icon_pay_ali('\ue64b'),
    icon_pay_wx('\ue619');

    private char character;
    EcIcons(char character){
        this.character=character;
    }
    @Override
    public String key() {
        return name().replace("-","-");
    }

    @Override
    public char character() {
        return character;
    }
}
