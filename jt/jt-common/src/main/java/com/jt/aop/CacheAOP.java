package com.jt.aop;

import com.jt.anno.CacheFind;
import com.jt.util.ObjectMapperUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;

@Aspect //AOP切面类
@Component  //将类交给spring容器管理
public class CacheAOP{
    @Autowired
    private JedisCluster jedis;
    //private Jedis jedis;      //单台redis
    //private ShardedJedis jedis; //分片机制
    //private JedisSentinelPool jedisSentinelPool;

    @Around("@annotation(cacheFind)")
    public Object around(ProceedingJoinPoint joinPoint, CacheFind cacheFind){
        //从池中获取jedis对象
        //Jedis jedis=jedisSentinelPool.getResource();
        Object result=null;

        try {
            //1.拼接redis存储数据库的key
            Object[] args=joinPoint.getArgs();
            String key = cacheFind.preKey() + "::" + Arrays.toString(args);
            //2.查询redis之后判断是否有数据
            if(jedis.exists(key)){
                //redis中有记录，无需执行目标方法
                String json = jedis.get(key);
                //动态获取方法的返回值类型  向上造型    向下造型
                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                Class returnType = methodSignature.getReturnType();
                result = ObjectMapperUtil.toObj(json,returnType);
                System.out.println("AOP查询redis缓存");
            }else {
                //表示数据不存在，需要查询数据库
                result = joinPoint.proceed();//执行目标方法及通知
                //将查询的结果保存到redis中
                String json = ObjectMapperUtil.toJSON(result);
                //判断数据是否需要超时时间
                if (cacheFind.seconds() > 0) {
                    jedis.setex(key, cacheFind.seconds(), json);
                } else {
                    jedis.set(key, json);
                }
                System.out.println("aop执行方法查询数据库");
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //jedis.close();  //用完后将
        return result;
    }
}