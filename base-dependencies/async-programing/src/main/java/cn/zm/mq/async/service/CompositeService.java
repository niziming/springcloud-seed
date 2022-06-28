package cn.zm.mq.async.service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public interface CompositeService {
    /**
     * 综合搜索集合
     */
    Map<String, String> searchList() throws InterruptedException, TimeoutException, ExecutionException;
}
