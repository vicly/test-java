package vic.test.springboot.app.bookmarks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @author Vic Liu
 */
@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {
    String applicationName = "bookmark";

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authentication -> authenticationManagerBuilder.getOrBuild().authenticate(authentication));
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("android-" + applicationName)
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .authorities("ROLE_USER")
                .scopes("write")
                .resourceIds(applicationName)
                .secret("123456");
    }
}
