package com.zj.common.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;


/**
 * Created by zhangjun16 on 2018/1/15.
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils() {
    }

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ObjectMapper mapper2 = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper2.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mapper2.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
        mapper2.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static String toJson(Object o) {
        String result = "";
        try {
            result = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LOGGER.error("serialization by jackson error", e);
        }
        return result;
    }

    public static <T> T parse(String json, Class<T> clazz) {
        T t = null;
        try {
            t = mapper.readValue(json, clazz);
        } catch (Exception e) {
            LOGGER.error("parse json error", e);
        }
        return t;
    }

    public static String toJsonWithNull(Object o) {
        String result = "";
        try {
            result = mapper2.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LOGGER.error("serialization by jackson error", e);
        }
        return result;
    }
}
