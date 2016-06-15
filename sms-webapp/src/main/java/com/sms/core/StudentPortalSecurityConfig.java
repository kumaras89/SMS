package com.sms.core;

import com.sms.core.security.filter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextListener;

import java.util.Arrays;

@Configuration
@ComponentScan("com.sms.core")
@EnableWebSecurity
public class StudentPortalSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth)
        throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SmsSessionContext smsSessionContext(){
        return new SmsSessionContext();
    }

    @Bean
    public OperationAuthorizationVoter operationAuthorizationVoter(){
        return new OperationAuthorizationVoter();
    }
    @Bean
    public AccessDecisionManager accessDecisionManager(){
        return new UnanimousBased(Arrays.asList(operationAuthorizationVoter()));
    }

    @Bean
    public NoRedirectAuthenticationSuccessHandler successHandler() {
        return new NoRedirectAuthenticationSuccessHandler();
    }

    @Bean
    public NoRedirectLoginFailureHandler failureHandler() {
        return new NoRedirectLoginFailureHandler();
    }

    @Bean
    public NoRedirectLogoutSucessHandler logoutSucessHandler() {
        return new NoRedirectLogoutSucessHandler();
    }


    @Bean
    public AntPathMatcher matcher(){
        return new AntPathMatcher();
    }

    @Bean
    public SmsAuthenticationFilter smsAuthenticationFilter()
        throws Exception {
        final SmsAuthenticationFilter filter = new SmsAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());
        filter.setUsernameParameter("username");
        filter.setPasswordParameter("password");
        filter.setFilterProcessesUrl("/authenticate");
        return filter;

    }

    @Bean public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

    @Override
    protected void configure(final HttpSecurity http)
        throws Exception {
        http.csrf()
            .disable()
            .authorizeRequests()
            .accessDecisionManager(accessDecisionManager())
            .antMatchers("/login").permitAll()
            .antMatchers("/**").authenticated()
            .and()
            .addFilterBefore(smsAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .logout()
            .logoutSuccessHandler(logoutSucessHandler())
            .and()
            .httpBasic().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }
}
