package br.edu.opi.manager.security;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ExceptionsUtils for Security.
 */
@Component
public class SecurityUtils {

	// @formatter:off
	public static final String TOKEN_HEADER = "authorization";

	public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "*";
	public static final List<String> ACCESS_CONTROL_ALLOW_ORIGIN_LIST = Arrays.asList("*");

	public static final String ACCESS_CONTROL_ALLOW_METHODS = "OPTIONS, GET, POST, DELETE, PUT, PATCH";
	public static final List<String> ACCESS_CONTROL_ALLOW_METHODS_LIST = Arrays.asList("OPTIONS", "GET", "POST", "DELETE", "PUT", "PATCH");

	public static final String ACCESS_CONTROL_ALLOW_HEADERS = "authorization, content-type, refresh-token";
	public static final List<String> ACCESS_CONTROL_ALLOW_HEADERS_LIST = Arrays.asList("authorization", "content-type", "refresh-token");

	public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "authorization, content-type, refresh-token";
	public static final List<String> ACCESS_CONTROL_EXPOSE_HEADERS_LIST = Arrays.asList("authorization", "content-type", "refresh-token");
	// @formatter:on

	/**
	 * Fills the Header with the Access Control.
	 *
	 * @param response Response.
	 * @return Response.
	 */
	public HttpServletResponse fillAccessControlHeader(HttpServletResponse response) {

		if (!response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)) {
			response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN);
		}

		if (!response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS)) {
			response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS);
		}

		if (!response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS)) {
			response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS);
		}

		if (!response.containsHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS)) {
			response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ACCESS_CONTROL_EXPOSE_HEADERS);
		}

		return response;
	}

	/**
	 * Get Headers with the Access Control.
	 *
	 * @return HttpHeaders
	 */
	public HttpHeaders fillAccessControlHeader() {

		HttpHeaders headers = new HttpHeaders();

		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN);
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS);
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS);
		headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ACCESS_CONTROL_EXPOSE_HEADERS);

		return headers;
	}

	/**
	 * Get Headers with add customs.
	 *
	 * @return HttpHeaders
	 */
	public HttpHeaders fillAccessControlHeader(Map<String, String> customHeaders) {

		HttpHeaders headers = new HttpHeaders();

		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN);
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS);
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS);
		headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ACCESS_CONTROL_EXPOSE_HEADERS);

		for (Entry<String, String> custom : customHeaders.entrySet()) {
			headers.add(custom.getKey(), custom.getValue());
		}

		return headers;
	}

	public static String generateFriendlyPassword() {
		int DEFAULT_LENGTH_PASSWORD = 8;
		char[] ch = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] c = new char[DEFAULT_LENGTH_PASSWORD];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < DEFAULT_LENGTH_PASSWORD; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}
		return new String(c);
	}

}
