package cn.zm.mq.async.service.impl;

import cn.zm.mq.async.service.AsyncService;
import cn.zm.mq.async.service.CompositeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class CompositeServiceImpl implements CompositeService {
    @Resource
    private AsyncService asyncService;

    @Override
    public Map<String, String> searchList() throws InterruptedException, TimeoutException, ExecutionException {
        long beforeTime = System.currentTimeMillis();// 开始时间戳

        //应用搜索-异步
        Future<String> appResult = asyncService.searchAppInfo();
        //待办搜索-异步
        Future<String> pendingResult = asyncService.searchPending();
        //用户搜索-异步
        Future<String> userResult = asyncService.searchUser();


        //添加结果集，30秒超时
        Map<String, String> jsonObject = new HashMap<String, String>();
        if(appResult != null){
            jsonObject.put("AppInfo", appResult.get(30, TimeUnit.SECONDS));
        }
        if(pendingResult != null){
            jsonObject.put("AppPending", pendingResult.get(30, TimeUnit.SECONDS));
        }
        if(userResult != null){
            jsonObject.put("UnifiedUser", userResult.get(30, TimeUnit.SECONDS));
        }

        long afterTime = System.currentTimeMillis();// 结束时间戳
        //耗时3秒多正确，耗时6秒多异步未成功
        System.out.println("耗时"+(afterTime - beforeTime)+"毫秒");
        return jsonObject;
    }
}
