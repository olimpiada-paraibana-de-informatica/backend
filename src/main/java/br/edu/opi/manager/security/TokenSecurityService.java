package br.edu.opi.manager.security;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * GenericService for Token Authentication.
 */
@Service
public class TokenSecurityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenSecurityService.class.getSimpleName());
	private static String SECRET = "Pmc953rv3r53cr3t"; // TODO: put in .env
	private static String SECRET_KEY = Base64.getEncoder().encodeToString(SECRET.getBytes());
	public static final long EXPIRATION_TOKEN = 21_600_000L; // 6 hours // TODO: put in .env
	private static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER = "Authorization";

	private Gson gson;

	/**
	 * Gets the authentication.
	 *
	 * @param request Request.
	 * @return Authentication.
	 */
	Authentication getAuthentication(final HttpServletRequest request) {
		String token = request.getHeader(HEADER);
		if (token != null) {
			token = token.replace(TOKEN_PREFIX, "").trim();
			// @formatter:off
			Claims claims = Jwts
					.parser()
					.setSigningKey(SECRET_KEY)
					.parseClaimsJws(token)
					.getBody();
			String user = claims.getSubject();
			Payload userDetails = gson.fromJson(user, Payload.class);
			LOGGER.info("trying connect user " + userDetails.getUsername());
			LOGGER.info("user connected " + userDetails.getUsername());
			return new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(),
					null,
					userDetails.getAuthorities());
			// @formatter:on
		} else {
			return null;
		}
	}

	/**
	 * Generate Token.
	 *
	 * @param payload
	 * @return token
	 */
	public final String generateToken(final Payload payload) {
		// @formatter:off
		return Jwts
				.builder()
				.setSubject(gson.toJson(payload))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
		// @formatter:on
	}

}
