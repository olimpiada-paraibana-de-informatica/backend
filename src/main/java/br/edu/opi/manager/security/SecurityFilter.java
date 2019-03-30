package br.edu.opi.manager.security;

import br.edu.opi.manager.exceptions.ErrorCodes;
import br.edu.opi.manager.exceptions.security.AuthenticationRuntimeException;
import br.edu.opi.manager.utils.ErrorMessagesConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter for Authentication with JWT.
 */
@Component
public class SecurityFilter extends GenericFilterBean {

	private TokenSecurityService tokenSecurityService;

	private SecurityUtils securityUtils;

	private Gson gson;

	@Autowired
	public SecurityFilter(
			TokenSecurityService tokenSecurityService,
			SecurityUtils securityUtils) {
		this.tokenSecurityService = tokenSecurityService;
		this.securityUtils = securityUtils;
		this.gson = new GsonBuilder().create();
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse res = securityUtils.fillAccessControlHeader((HttpServletResponse) response);

		try {

			HttpServletRequest req = (HttpServletRequest) request;

			/*
			 * If it is a OPTIONS request (usually used for check, return OK because there
			 * is no header
			 */
			if (RequestMethod.OPTIONS.name().equalsIgnoreCase(req.getMethod())) {
				res.setStatus(HttpServletResponse.SC_OK);
				return;
			}

			/* Checks the Authorization */
			Authentication auth = tokenSecurityService.getAuthentication(req);

			SecurityContextHolder.getContext().setAuthentication(auth);

			chain.doFilter(request, response);

		} catch (AuthenticationRuntimeException re) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			res.setContentType("application/json; charset=utf-8");
			res.getWriter().write(gson.toJson(re.getExceptionResponse()));
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			res.setContentType("application/json; charset=utf-8");
			final JsonObject error = new JsonObject();
			error.addProperty("message", ErrorMessagesConstants.AUTHENTICATION_GENERIC_ERROR);
			error.addProperty("errorCode", ErrorCodes.AUTHENTICATION_ERROR);
			res.getWriter().write(error.toString());
		}

	}

}
