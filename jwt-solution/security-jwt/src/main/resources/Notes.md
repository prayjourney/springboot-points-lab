#### 流程
1.在任意一个页面上，比如在首页，点击了跳转到登录页面的按钮，然后跳转到登录的页面
2.在登录的页面上面，输入用户名，密码，点击登录，进行比较，如果比较成功，就生成token, 返回给前端
3.前端把后端返回的token存储起来，每次发送请求的时候带上token，放在header之中，后端验证，如果没有权限，就要报错
4.当token快要过期的时候，就要发送一个新的token


#### 关键的点
在前后端分离，使用spring security的情况下，我们不能返回页面，而是要返回相应的状态，所以在配置这块上面，就要关注下面的
```java
http.formLogin()
        .usernameParameter("username")
        .passwordParameter("password")
        .loginPage("/index")
        // 原先使用的登录成功之后跳转的页面
        // .successForwardUrl("/hello")
        // 现在登录成功使用的是successHandler处理, 返回了一个token字符串, 所以就不会跳转页面了
        .loginProcessingUrl("/login").successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)
        .and()
        // 授权的拦截器和异常处理器, token的解析, 获取权限
        // jwtAuthorizationFilter在UsernamePasswordAuthenticationFilter之前执行，查看有无token和解析token
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

```
1.不应该再去指定登录的页面

2.登录成功不再去指定流程，我们只返回登录是否成功

3.登录成功之后，再去后续的再去做各种权限的检查和校验



这个工程，还是前后没有分离的思维，看下面一个
### 前后分离，不能用http.formLogin()了
