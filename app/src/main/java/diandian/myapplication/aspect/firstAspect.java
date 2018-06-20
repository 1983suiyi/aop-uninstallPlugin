package diandian.myapplication.aspect;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import diandian.myapplication.AspectAnnotation;

/**
 * Created by funplus on 2018/6/15.
 */


@Aspect
public class firstAspect {

    @Around(value = "call(* *..*.*(..)) && @annotation(aspect)")
    //@Around("execution(* diandian.myapplication.aspect.firstAspect**(..))")
    public void executionCommon(ProceedingJoinPoint joinPoint, AspectAnnotation aspect) throws Throwable{
        Object[] args =joinPoint.getArgs();
        if (args!=null&&args.length>0)
            Log.e("suiyi", "executionCommon:"+args[0].getClass().getSimpleName());

        Log.e("suiyi", "executionCommon:"+aspect.MethodName()+"---"+aspect.clazz());
        Object retn = joinPoint.proceed();
        return;
    }


    @Pointcut("execution(@diandian.myapplication.PointCutAnnotation * *(..))")
    public void myPointCut(){
        Log.d("suiyi", "myPointCut");
    }

    @Before("myPointCut()")
    public void myPointCutBefore(){
        Log.e("suiyi", "myPointCutBefore");
    }

    @After("execution(* android.app.Activity.onCreate(..))")
    public void After(JoinPoint joinPoint){
        Log.e("suiyi", "after");
    }

    @Before("execution(* android.app.Activity.onCreate(..))")
    public void Before(JoinPoint joinPoint)throws Throwable{
        Log.e("suiyi", "before");
    }


}
