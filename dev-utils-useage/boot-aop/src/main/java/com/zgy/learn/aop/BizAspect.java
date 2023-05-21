package com.zgy.learn.aop;

import cn.hutool.core.date.DateUtil;
import com.zgy.learn.handle.BizException;
import com.zgy.learn.handle.MessageCode;
import com.zgy.learn.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: pray-journey.io
 * @date: created in 2022/11/6, 2023/5/19
 * @description: AOP的执行顺序的问题
 * <p>
 * 1. AOP的通知，是同心圆的模型，先执行的，后结束
 * 2. 同一个切面之中, 执行顺序按照around - > before -> after -> after return / after throwing
 * 3. 不同切面, 可以设置order决定执行顺序，order越小，越先执行，但是先执行的，一定对于后面的after，这些操作，是后结束的，是一个同心圆包起来的
 * 4. 不同的切面，同一个pointCut，执行的顺序，一定要在相同的通知类型下面比较顺序，才有意义，否则没有意义，比如就算是不同的切面，A前面order=1，B
 * 切面的order=2，对于同一个pointCut，A之中是After通知，B之中是Before通知，B的执行一定要比A的执行早，只有相同的通知类型，才有比较意义，比如都是
 * Before，那么A的就比B的要早。
 * </p>
 */
@Order(1000) // 定义切面执行的顺序，
@Slf4j
@Aspect // 定义成切面
@Component // 让Spring管理
public class BizAspect {
    /**
     * 定义切点
     * execution中, *表示全部, ()匹配没有参数, (..)代表任意多个参数, (*)代表一个参数但可以是任意型, (*,String)代表第一个参数为任何类型 ,第二个为String类型
     * <p>
     * 以execution(* com.zgy.learn.controller.*.*(..))为例, 分为三个部分
     * 第一部分: 第一个*号的位置: 表示返回值类型, *表示所有类型
     * 第二部分: 包名, 可以写全也可以后面加一个*, 表示需要拦截的包名，controller.*表示controller下面所有的类,
     * 第三部分: *(..): 这个星号表示方法名, 表示所有的方法, 后面括弧里面表示方法的参数, 两个句点表示任何参数
     */
    @Pointcut("execution(* com.zgy.learn.controller.*.*(..))")
    public void exceptionPointCut() {
    }

    // 只对于这个方法起作用
    @Pointcut("execution(Integer com.zgy.learn.controller.ShopController.buy(String, String, Integer))")
    public void buyPointCut() {
    }

    @Pointcut("execution(* com.zgy.learn.controller.TravelController.*(..))")
    public void travelPointCut() {
    }

    // @AfterReturning可以处理返回值, @After不能处理返回值, 功能更加强大
    @AfterReturning(pointcut = "buyPointCut()", returning = "result")
    public void totalCountAdvice(JoinPoint point, Integer result) {
        Object[] args = point.getArgs();
        String user = (String) args[0];
        String name = (String) args[1];
        Integer num = (Integer) args[2];
        if (result > 200) {
            log.warn("用户:{}购买了{}有{}, 时间是:{}", user, name, num, DateUtil.now());
        } else {
            log.info("用户:{}购买了{}, 数量是{}, 时间是:{}", user, name, num, DateUtil.now());
        }
    }

    @Before("travelPointCut()")
    public void travelForeshowAdvice(JoinPoint point) {
        Object[] args = point.getArgs();
        String destination = (String) args[0];
        if (args.length == 2) {
            String tool = (String) args[1];
            if (tool.equals(ToolUtil.AIR_PLANE)) {
                log.warn("即将出发==> 有人乘坐飞机, 目的地是:{}, 出发时间:{}", destination, DateUtil.now());
            }
        } else {
            log.info("即将出发==> 目的地: {}的旅行于{}出发", destination, DateUtil.now());
        }
    }

    @After("travelPointCut()")
    public void travelAdvice(JoinPoint point) {
        Object[] args = point.getArgs();
        String destination = (String) args[0];
        if (args.length == 2) {
            String tool = (String) args[1];
            if (tool.equals(ToolUtil.AIR_PLANE)) {
                log.warn("有人乘坐飞机, 目的地是:{}, 出发时间:{}", destination, DateUtil.now());
            }
        } else {
            log.info("目的地: {}的旅行于{}出发", destination, DateUtil.now());
        }
    }

    @AfterThrowing(pointcut = "exceptionPointCut()", throwing = "ex")
    public void exceptionAdvice(JoinPoint point, Throwable ex) {
        Signature signature = point.getSignature();
        String method = signature.getName();
        if (ex instanceof BizException) {
            MessageCode messageCode = ((BizException) ex).getMessageCode();
            log.warn("执行方法{}出错, 时间是:{}, 错误码：{}", method, DateUtil.now(), messageCode.getCode());
        } else {
            log.warn("执行方法{}出错, 时间是:{}", method, DateUtil.now());
        }
    }

}

