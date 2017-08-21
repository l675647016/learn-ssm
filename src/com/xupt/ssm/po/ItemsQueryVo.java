package com.xupt.ssm.po;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 商品包装对象
 * Created by 梁峻磊 on 2017/8/15.
 */
public class ItemsQueryVo {
    //商品信息
    private Items items;
    //为了系统可扩展性。对原始生成的po进行扩展
    private ItemsCustom itemsCustom;
    //批量商品信息
    private List<ItemsCustom> itemsList;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public ItemsCustom getItemsCustom() {
        return itemsCustom;
    }

    public void setItemsCustom(ItemsCustom itemsCustom) {
        this.itemsCustom = itemsCustom;
    }

    public List<ItemsCustom> getItemsList() {
        return itemsList;
    }
    @Autowired
    public void setItemsList(List<ItemsCustom> itemsList) {
        this.itemsList = itemsList;
    }
}
