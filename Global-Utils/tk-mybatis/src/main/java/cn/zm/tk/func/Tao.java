package cn.zm.tk.func;

import java.util.function.Consumer;
import tk.mybatis.mapper.entity.Example.Criteria;

@FunctionalInterface
public interface Tao extends Consumer<Criteria> {
}
