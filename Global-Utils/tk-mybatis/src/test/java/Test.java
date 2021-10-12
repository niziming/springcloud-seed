import cn.zm.tk.anno.Like;
import cn.zm.tk.web.entity.Account;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test<T> {
    public List<T> likeByProperty(T record) {
        Class<?> aClass = record.getClass();
        Field[] fields = aClass.getDeclaredFields();
        Example example = new Example(aClass);
        Example.Criteria criteria = example.createCriteria();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Like like = field.getDeclaredAnnotation(Like.class);
                // if (ObjectUtils.isNotEmpty(field.get(record)) && field.get(record) instanceof String) {
                if (ObjectUtils.isNotEmpty(field.get(record))) {
                    if (ObjectUtils.isNotEmpty(like)) {
                        criteria.andLike(field.getName(), "%" + field.get(record) + "%");
                    } else {
                        criteria.andEqualTo(field.getName(), field.get(record));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // return mapper.selectByExample(example);
        return null;
    }

    public static void main(String[] args) throws IllegalAccessException {
        // Test<Object> test = new Test<>();
        // Account account = Account.builder().money(10.10).name("").build();
        // test.likeByProperty(account);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("爸：小红你去买瓶酒！");
            try {
                System.out.println("小红出去买酒了，女孩子跑的比较慢，估计5s后才会回来...");
                Thread.sleep(5000);
                return "我买回来了！";
            } catch (InterruptedException e) {
                System.err.println("小红路上遭遇了不测");
                return "来世再见！";
            }
        }, executor);
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("爸：小明你去买包烟！");
            try {
                System.out.println("小明出去买烟了，可能要3s后回来...");
                Thread.sleep(3000);
                return "我买回来了!";
            } catch (InterruptedException e) {
                System.out.println("小明路上遭遇了不测！");
                return "这是我托人带来的口信，我已经不在了。";
            }
        }, executor);
        future2.thenAccept(e -> {
            System.out.println("小红说 = " + e);
        });
        future1.thenAccept(e -> {
            System.out.println("小明说 = " + e);
        });
        executor.shutdown();
    }
}
