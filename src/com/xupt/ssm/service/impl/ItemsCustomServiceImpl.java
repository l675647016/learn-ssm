package com.xupt.ssm.service.impl;

import java.util.List;

import com.xupt.ssm.mapper.ItemsCustomMapper;
import com.xupt.ssm.mapper.ItemsMapper;
import com.xupt.ssm.po.Items;
import com.xupt.ssm.po.ItemsCustom;
import com.xupt.ssm.po.ItemsQueryVo;
import com.xupt.ssm.service.ItemsCustomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


public class ItemsCustomServiceImpl implements ItemsCustomService {

    @Autowired
    private ItemsCustomMapper itemsCustomMapper;

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
            throws Exception {
        //通过ItemsMapperCustom查询数据库
        return itemsCustomMapper.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {
        Items items = itemsMapper.selectByPrimaryKey(id);
        //中间进行业务处理
        //...
        //返回itemsCustom
        ItemsCustom itemsCustom = new ItemsCustom();
        //将items的属性拷贝到itemsCustom
        BeanUtils.copyProperties(items,itemsCustom);
        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        //更新商品信息,使用updateByPrimaryKeyWithBLOBs可以更新Items表中的所有字段，包括大文本类型
        //updateByPrimaryKeyWithBLOBs要求必须传入id
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }

    @Override
    public void deleteItems(Integer[] id) throws Exception {
        itemsCustomMapper.deleteItems(id);
    }

    @Override
    public void updateManyItems(ItemsQueryVo itemsQueryVo) throws Exception {
        List<ItemsCustom> itemsList = itemsQueryVo.getItemsList();
        itemsCustomMapper.updateManyItems(itemsList);
    }

}
