package org.yesworkflow.save.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.time.LocalDateTime;

public class JsonSerializer implements IYwSerializer
{

    private Gson gson;
    public JsonSerializer()
    {
        GsonIso8601LocalDateTimeAdapter localDateTimeAdapter = new GsonIso8601LocalDateTimeAdapter();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, localDateTimeAdapter)
                .create();
    }

    public String Serialize(Object object)
    {
        return gson.toJson(object);
    }

    public <T> T Deserialize(String json, Class<T> Dto)
    {
        T object = null;
        try
        {
            object = gson.fromJson(json, Dto);
        }
        catch (JsonParseException e)
        {
        }
        return object;
    }
}
