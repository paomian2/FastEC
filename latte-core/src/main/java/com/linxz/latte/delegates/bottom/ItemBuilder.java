package com.linxz.latte.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月27日11:13  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS=new LinkedHashMap<>();

    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public final ItemBuilder addBottom(BottomTabBean bean,BottomItemDelegate delegate){
        ITEMS.put(bean,delegate);
        return this;
    }

    public final ItemBuilder addBottoms(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){
        return ITEMS;
    }
}
