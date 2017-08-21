package com.xupt.ssm.mapper;

import com.xupt.ssm.po.Items;
import com.xupt.ssm.po.ItemsCustom;
import com.xupt.ssm.po.ItemsQueryVo;

import java.util.List;

/**
 * 商品查询mapper
 * Created by 梁峻磊 on 2017/8/15.
 */
public interface ItemsCustomMapper {
    //查村商品列表
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    //批量删除商品信息
    public void deleteItems(Integer[] id)throws Exception;

    //批量修改商品信息
    //public void updateManyItems(List<ItemsCustom> itemsList)throws Exception;
}