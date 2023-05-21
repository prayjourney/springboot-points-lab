### SpringSecurity之中免登录实现的方式
#### 1. 在Security的配置之中去配置
在configure接口之中定义直接放行的接口, 比如/login, `authorizeRequest.antMatchers("/login").permitAll().anyRequest().authenticated();`
就可以让login接口不去做token的校验。

```java

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // ... 省略
    
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
        // 放开/login, 其它接口都要认证
        //authorizeRequest.antMatchers("/login").permitAll().anyRequest().authenticated();

        // 将token验证添加在密码验证前面
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

```

#### 2. 自定义的方式去配置
我们自定义的方式去配置，思维其实和上面的一致，就是在启动的时候，把我们需要设置免登录的接口，都注册好，而不是访问的时候再去通过拦截器去验证，此处，
拦截器的方式是有问题的。

使用自定义的注解，然后在Interceptor之中注册，如下的方式，如果没有使用Spring Security，这是可以的，但是使用了Spring Security，就无法使用了，原因如下。

```java
@Slf4j
@Component
public class IgnoreAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("login");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查是否有NotLogin注解, 有则不用登录就可以访问
        if (method.isAnnotationPresent(IgnoreAuth.class)) {
            IgnoreAuth notLogin = method.getAnnotation(IgnoreAuth.class);
            if (notLogin.required()) {
                return true;
            }
            return false;
        }
        return true;
    }

}
```

##### 执行的顺序

filter —> interceptor，我们在security中，使用jwt，要定义我们自己的filter类，JwtAuthenticationTokenFilter，这个类，继承了OncePerRequestFilter，我们写的是interceptor，而interceptor的执行顺序，在JwtAuthenticationTokenFilter之后了，但是配置之中还有如下的myAuthenticationEntryPoint和myAccessDeniedHandler，前者是匿名访问没有权限的处理，后者是认证用户没有相关权限时候的处理。

当请求进入，先被jwtAuthenticationTokenFilter拦截，如果没有获取到token，就会直接走到这两个异常处理了，而不会走到我们的interceptor，所以这样是无法处理的。

`如果仅仅使用了jwt的方式，没有spring security，那么使用自定义注解的interceptor可以做到权限的处理`。

```java
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
        // 放开/login, 其它接口都要认证
        //authorizeRequest.antMatchers("/login").permitAll().anyRequest().authenticated();
        authorizeRequest.anyRequest().authenticated();

        // 将token验证添加在密码验证前面
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
```

##### Spring Security下面的注解配置免登录的方式



```
@PostConstruct
ApplicationContextAware
InitializingBean接口

https://blog.csdn.net/xurk0922/article/details/108036810
https://blog.csdn.net/liyantianmin/article/details/80379515
```





