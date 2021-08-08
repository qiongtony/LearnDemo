package com.example.aopdemo.aop;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.aopdemo.Constants;
import com.example.aopdemo.LoginActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class AspectUtil {

    // PointCut：切入点，所有ClickAction
    @Pointcut("execution(@com.example.aopdemo.aop.ClickAction * *(..))")
    public void aspectPointCutClickAnnotation(){

    }

    // 切面,注意切入点的方法名，且要带括号
    @Around("aspectPointCutClickAnnotation()")
    public void aroundAspectPointCutClick(ProceedingJoinPoint joinPoint) throws Throwable{
        // 获取到切入点的信息

       MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
       // 获取类名
       String className = methodSignature.getDeclaringType().getSimpleName();//   methodSignature.getDeclaringTypeName()
       // 获取方法名
       String methodName = methodSignature.getName();

                ClickAction clickAction = methodSignature.getMethod().getAnnotation(ClickAction.class);
        Log.e(Constants.TAG, String.format("正在执行%s的切面，类名为：%s，方法名为%s", clickAction.name(), className, methodName));
        // 执行切面方法
        joinPoint.proceed();
    }

    // 切入点：登录注解
    @Pointcut("execution(@com.example.aopdemo.aop.LoginCheck * *(..))")
    public void loginCheckPointCut(){

    }

    @Around("loginCheckPointCut()")
    public void aroundLoginCheck(ProceedingJoinPoint joinPoint) throws Throwable{
        // 判断是否登录，如果登录执行方法内操作，如果未登录，不执行方法，执行未登录的处理逻辑
        boolean login = false;
        Log.e(Constants.TAG, String.format("执行%s前进行登录检查", joinPoint.getSignature().getName()));
        if (login){
            joinPoint.proceed();
        }else{
            Context context = (Context) joinPoint.getThis();
            Toast.makeText(context, "请进行登录！", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, LoginActivity.class));

        }
    }

    // 切入点是OnClickListener的OnClick方法
    @Pointcut("execution(void android.view.View.OnClickListener.onClick(..))")
    public void methodViewOnClick(){

    }

    @Around("methodViewOnClick()")
    public void aroundViewOnClick(ProceedingJoinPoint joinPoint) throws Throwable {
        View target = (View)joinPoint.getArgs()[0];
        Log.e("WWS", "target = " + target.getId());
        if (!FastClickCheckUtil.isFastClick(target, 2000)){
            joinPoint.proceed();
        }
    }
}
