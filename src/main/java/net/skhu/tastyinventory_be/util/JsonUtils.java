//package net.skhu.tastyinventory_be.util;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//
//import java.lang.reflect.Type;
//
//public class JsonUtils {
//    private static JsonUtils instance;
//
//    private final Gson gson;
//    private final Gson prettyGson;
//
//    private JsonUtils() {
//        gson = new GsonBuilder().disableHtmlEscaping().create();
//        prettyGson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
//    }
//
//    public static JsonUtils getInstance() {
//        if (instance == null) {
//            instance = new JsonUtils();
//        }
//        return instance;
//    }
//
//    private static Gson getGson() {
//        return getInstance().gson;
//    }
//
//    private static Gson getPrettyGson() {
//        return getInstance().prettyGson;
//    }
//
//    public static JsonElement parse(String jsonStr) {
//        return JsonParser.parseString(jsonStr);
//    }
//
//    public static String toJson(Object obj) {
//        return getGson().toJson(obj);
//    }
//
//    public static <T> T fromJson(String jsonStr, Class<T> cls) {
//        return getGson().fromJson(jsonStr, cls);
//    }
//
//    public static <T> T fromJson(String jsonStr, Type type) {
//        return getGson().fromJson(jsonStr, type);
//    }
//
//    public static String toPrettyJson(Object obj) {
//        return getPrettyGson().toJson(obj);
//    }
//}
