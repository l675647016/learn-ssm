package com.xupt.ssm.service;

import com.xupt.ssm.po.ItemsCustom;
import com.xupt.ssm.po.ItemsQueryVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 梁峻磊 on 2017/8/16.
 */
@Service
public interface ItemsCustomService {
    //商品列表查询
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws  Exception;

    //根据id查询商品信息
    public ItemsCustom findItemsById(Integer id)throws Exception;

    //修改商品信息
    public void updateItems(Integer id,ItemsCustom itemsCustom)throws Exception;
}
