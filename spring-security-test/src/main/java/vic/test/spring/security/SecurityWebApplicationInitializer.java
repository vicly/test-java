package vic.test.spring.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * The equivalent:
 *
 * <filter>
 *     <filter-name>springSecurityFilterChain</filter-name>
 *     <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
 * </filter>
 * <filter-mapping>
 *     <filter-name>springSecurityFilterChain</filter-name>
 *     <url-pattern>/*</url-pattern>
 * </filter-mapping>
 *
 * @author Vic Liu
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
