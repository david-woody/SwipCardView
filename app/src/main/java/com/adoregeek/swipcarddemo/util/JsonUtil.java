package com.adoregeek.swipcarddemo.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
	private static Gson gson = new GsonBuilder().setExclusionStrategies(new MenuTreeExclusionStrategy()).create();

	private static class MenuTreeExclusionStrategy implements ExclusionStrategy {
		public boolean shouldSkipField(FieldAttributes f) {
			if("baseObjId".equals(f.getName())) 
				return true;
			return false;
		}

		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}
	}

	public static <T> T jsonToEntity(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}

	public static <T> T jsonToEntity(JsonElement json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}

	public static <T> List<T> jsonToEntityList(String json, Class<T> classOfT) {
		JsonParser parser = new JsonParser();
		try {
			JsonElement ele = parser.parse(json);
			return jsonToEntityList(ele.getAsJsonArray(), classOfT);
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> List<T> jsonToEntityList(JsonArray arr, Class<T> classOfT) {
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < arr.size(); i++) {
			list.add(jsonToEntity(arr.get(i).toString(), classOfT));
		}
		return list;
	}

	public static String entityToJson(Object src) {
		return gson.toJson(src);
	}
}
