package com.xupt.ssm.controller;

import com.xupt.ssm.po.ItemsCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 梁峻磊 on 2017/8/24.
 */
@Controller
public class JsonTest {
    //请求json，输出是json
    //@RequestBody将请求商品信息的json转成itemsCustom对象
    //@ResponseBody将itemsCustom对象转成json输出
    @RequestMapping("/requestJson")
    public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom){
        return itemsCustom;
    }

    //请求key/value，输出json
    @RequestMapping("/responseJson")
    public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom){
        return itemsCustom;
    }
}
