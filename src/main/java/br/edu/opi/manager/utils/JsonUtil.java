package br.edu.opi.manager.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.Reader;

/**
 * Utility class for JSon operations.
 */
@Component
public final class JsonUtil {

	/**
	 * Instance of the converter.
	 */
	private Gson gson;

	/**
	 * Private constructor.
	 */
	private JsonUtil() {
		gson = new GsonBuilder().create();
	}

	/**
	 * Converts the object into JSon as string.
	 * 
	 * @param object Object.
	 * @return JSon as string.
	 */
	public String toJSon(Object object) {
		return gson.toJson(object);
	}

	/**
	 * Converts the JSon as string into object.
	 * 
	 * @param json  JSon as string.
	 * @param clazz Class of the target object.
	 * @return Object.
	 */
	public <T> T fromJSon(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	/**
	 * Converts the JSon as string into object.
	 * 
	 * @param reader Reader.
	 * @param clazz  Class of the target object.
	 * @return Object.
	 */
	public <T> T fromJSon(Reader reader, Class<T> clazz) {
		return gson.fromJson(reader, clazz);
	}

}
