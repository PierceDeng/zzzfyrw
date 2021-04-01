package com.zzzfyrw.zzzfyrw.common.codec.gson;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public final class GsonUtil {

    private static final GsonBuilder builder;

    static {
        builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        builder.serializeNulls();
        builder.disableHtmlEscaping();
        builder.setLenient();
        builder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        builder.registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                (o, type, jsonSerializationContext) -> new JsonPrimitive(o.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        builder.registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>)
                (date, type, jsonSerializationContext) -> new JsonPrimitive(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        builder.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) -> {
            String datetime = jsonElement.getAsJsonPrimitive().getAsString();
            return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        });
        builder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) -> {
            String datetime = jsonElement.getAsJsonPrimitive().getAsString();
            return LocalDate.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        });
    }

    public static <T> T fromJsonToObject (String json, Class<T> type){
        Gson gson = builder.create();
        return gson.fromJson(json,type);
    }

    public static <T> T fromJsonToObject (String json, Type type){
        Gson gson = builder.create();
        return gson.fromJson(json,type);
    }

    public static <T> String fromObjectToJson(T Object){
        Gson gson = builder.create();
        return gson.toJson(Object);
    }

    public static Map<String,Object> fromJsonToMap(String json){
        Gson gson = builder.create();
        return gson.fromJson(json,new TypeToken<Map<String, Object>>() { }.getType());
    }

    public static JsonObject fromJsonToObject(String json){
        return new JsonObject().getAsJsonObject(json);
    }



}
