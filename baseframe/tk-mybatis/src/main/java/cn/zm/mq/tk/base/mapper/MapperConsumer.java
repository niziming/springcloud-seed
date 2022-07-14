package cn.zm.mq.tk.base.mapper;

import java.util.function.Consumer;

import tk.mybatis.mapper.entity.Example.Criteria;

@FunctionalInterface
public interface MapperConsumer extends Consumer<Criteria> {

}
