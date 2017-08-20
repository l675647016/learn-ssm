package com.xupt.ssm.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 梁峻磊 on 2017/8/19.
 */
public class CustomDateConverter implements Converter<String,Date>{
    @Override
    public Date convert(String source) {
        //实现日期穿转换成日期类型
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //转换成功，直接返回
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果参数绑定失败，返回null
        return null;
    }
}
