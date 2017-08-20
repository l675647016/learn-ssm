package com.xupt.ssm.mapper;

import com.xupt.ssm.po.ItemsCustom;
import com.xupt.ssm.po.ItemsQueryVo;

import java.util.List;

/**
 * 商品查询mapper
 * Created by 梁峻磊 on 2017/8/15.
 */
public interface ItemsCustomMapper {
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}