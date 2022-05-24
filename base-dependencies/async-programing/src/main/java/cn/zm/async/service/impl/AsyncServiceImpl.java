package cn.zm.async.service.impl;

import cn.zm.async.service.AsyncService;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Future;

@Primary
@Service
@Transactional(rollbackFor = Exception.class)
public class AsyncServiceImpl implements AsyncService {
    /**
     * 应用搜索-异步
     */
    @Async
    @Override
    public Future<String> searchAppInfo() throws InterruptedException {
        //自己的业务逻辑
        //睡眠3秒测试
        Thread.sleep(3000);
        String result = null;
        return new AsyncResult<String>(result);
    }

    /**
     * 待办搜索-异步
     */
    @Async
    @Override
    public Future<String> searchPending() throws InterruptedException {
        //自己的业务逻辑

        //睡眠1秒测试
        Thread.sleep(1000);

        String result = null;
        return new AsyncResult<String>(result);
    }

    /**
     * 用户搜索-异步
     */
    @Async
    @Override
    public Future<String> searchUser() throws InterruptedException {
        //自己的业务逻辑

        //睡眠2秒测试
        Thread.sleep(2000);

        String result = null;
        return new AsyncResult<String>(result);
    }
}
