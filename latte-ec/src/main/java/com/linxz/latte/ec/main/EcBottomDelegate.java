package com.linxz.latte.ec.main;

import android.graphics.Color;

import com.linxz.latte.delegates.bottom.BaseBottomDelegate;
import com.linxz.latte.delegates.bottom.BottomItemDelegate;
import com.linxz.latte.delegates.bottom.BottomTabBean;
import com.linxz.latte.delegates.bottom.ItemBuilder;
import com.linxz.latte.ec.main.car.ShopCarDelegate;
import com.linxz.latte.ec.main.discover.DiscoverDelegate;
import com.linxz.latte.ec.main.index.IndexDelegate;
import com.linxz.latte.ec.main.personal.PersonalDelegate;
import com.linxz.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月27日17:15  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class EcBottomDelegate extends BaseBottomDelegate{

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items=new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCarDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "我的"), new PersonalDelegate());
        return builder.addBottoms(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
