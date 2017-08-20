package com.xupt.ssm.po;


/**
 * 商品包装对象
 * Created by 梁峻磊 on 2017/8/15.
 */
public class ItemsQueryVo {
    //商品信息
    private Items items;
    //为了系统可扩展性。对原始生成的po进行扩展
    private ItemsCustom itemsCustom;

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
}
