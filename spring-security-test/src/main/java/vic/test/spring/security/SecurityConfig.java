package vic.test.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * equivalent:
 *
 * <http auto-config="true">
 *   <intercept-url pattern="/admin**" access="ROLE_ADMIN" />
 *   <intercept-url pattern="/dba**" access="ROLE_ADMIN,ROLE_DBA" />
 * </http>

 * <authentication-manager>
 *   <authentication-provider>
 *     <user-service>
 *       <user name="mkyong" password="123456" authorities="ROLE_USER" />
 *       <user name="admin" password="123456" authorities="ROLE_ADMIN" />
 *       <user name="dba" password="123456" authorities="ROLE_DBA" />
 *     </user-service>
 *   </authentication-provider>
 * </authentication-manager>
 * @author Vic Liu
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("vic").password("vicpass").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("adminpass").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Disable session == each time need credential => FORM_AUTH can't use
                //
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()

//                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .httpBasic()
        ;
    }
}
