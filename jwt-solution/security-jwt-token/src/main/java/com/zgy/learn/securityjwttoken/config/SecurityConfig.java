package com.zgy.learn.securityjwttoken.config;

import com.zgy.learn.securityjwttoken.handler.JwtAuthenticationTokenFilter;
import com.zgy.learn.securityjwttoken.handler.MyAccessDeniedHandler;
import com.zgy.learn.securityjwttoken.handler.MyAuthenticationEntryPoint;
import com.zgy.learn.securityjwttoken.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 放行的接口设置, 包括配置的和使用注解的
    @Resource
    private IgnoreAuthProperties ignoreAuthProperties;
    @Resource
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Resource
    private AuthenticationService authenticationService;
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 自定义登录逻辑与密码加密和解析器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
    }

    /**
     * 密码加密和解析器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 设置需要授权认证的资源, 不需要权认能访问的资源
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequest = httpSecurity.authorizeRequests();
        /**
         * 不在此处使用formLogin的方式, 自己定义登录接口
         */
        httpSecurity
                // 关闭csrf
                .csrf().disable()
                // 跨域
                .cors()
                .and()
                .exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint)
                .and()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                .and()
                // 不创建session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().cacheControl().disable()
                .and()
                .authorizeRequests();
        // 设置放行接口, 其它接口都要认证
        ignoreAuth(authorizeRequest);
        authorizeRequest.anyRequest().authenticated();

        // 将token验证添加在密码验证前面
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry ignoreAuth(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequest) {
        String[] allUrl = new String[ignoreAuthProperties.getAllUrl().size()];
        String[] getUrl = new String[ignoreAuthProperties.getGetUrl().size()];
        String[] postUrl = new String[ignoreAuthProperties.getPostUrl().size()];
        ignoreAuthProperties.getGetUrl().toArray(getUrl);
        ignoreAuthProperties.getPostUrl().toArray(postUrl);
        ignoreAuthProperties.getAllUrl().toArray(allUrl);
        if (allUrl.length > 0) {
            authorizeRequest.antMatchers(allUrl).permitAll();
        }
        if (getUrl.length > 0) {
            authorizeRequest.antMatchers(HttpMethod.GET, getUrl).permitAll();
        }
        if (postUrl.length > 0) {
            authorizeRequest.antMatchers(HttpMethod.POST, postUrl).permitAll();
        }
        return authorizeRequest;
    }

}
