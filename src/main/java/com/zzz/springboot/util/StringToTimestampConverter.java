package com.zzz.springboot.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;


import org.springframework.core.convert.converter.Converter;
@Configuration
public class StringToTimestampConverter implements Converter<String,Timestamp> {

    private final SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Timestamp convert(String text) {
        Timestamp timestamp=null;
        if(StringUtils.hasText(text)){
            text = text.trim();
            boolean isLong = true;
            try {
                long millisecond = Long.parseLong(text);
                timestamp=new Timestamp(millisecond);
            }catch(Exception e){
                isLong = false;
            }
            if(!isLong) {
                    
                    
				try {
					String format;
					SimpleDateFormat sdf = new SimpleDateFormat(FormatUtils.getFormatter(text.length()));
					format = defaultDateFormat.format(sdf.parse(text));
					System.out.println(format);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        return timestamp;
    }

    
    
    
    
}