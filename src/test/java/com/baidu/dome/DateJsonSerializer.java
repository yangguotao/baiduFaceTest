package com.baidu.dome;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义一个json的日期类型格式化类
 * 与 fastjson 中 @JSONField(format = "yyyy-MM-dd HH:mm:ss")效果相似，
 * 对象转成json字符串时 日期类型不会再转成时间戳
 * @Author: yangguotao
 * @Date: 2020/10/30
 * @Version 1.0
 */
public class DateJsonSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
       String valueStr = simpleDateFormat.format(value);
       gen.writeString(valueStr);
    }
}
