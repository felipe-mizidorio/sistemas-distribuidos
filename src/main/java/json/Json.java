package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class Json {
    private static final Gson gson = new GsonBuilder().create();

    public static <T> String toJson(T object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(final String json, Class<T> className) throws JsonIOException {
        return gson.fromJson(json, className);
    }

}
