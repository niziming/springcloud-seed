// package cn.zm.tk.base.mapper;// package cn.zm.tk.base.mapper;
//
// import java.util.List;
// import java.util.Map;
// import java.util.function.Consumer;
//
// import com.google.common.collect.Lists;
// import org.apache.ibatis.annotations.DeleteProvider;
// import org.apache.ibatis.annotations.Options;
// import org.apache.ibatis.annotations.Select;
// import org.apache.ibatis.annotations.SelectProvider;
// import org.apache.ibatis.annotations.UpdateProvider;
//
//
// import cn.hutool.core.bean.BeanUtil;
// import tk.mybatis.mapper.common.IdsMapper;
// import tk.mybatis.mapper.common.Mapper;
// import tk.mybatis.mapper.common.MySqlMapper;
// import tk.mybatis.mapper.entity.Example;
// import tk.mybatis.mapper.entity.Example.Criteria;
//
// public interface Imapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
//
// 	@SelectProvider(method = "sql", type = ImapperSql.class)
// 	T seeOneBysql(String sql);
//
// 	@SelectProvider(method = "sql", type = ImapperSql.class)
// 	String seeFieldBysql4String(String sql);
//
// 	@SelectProvider(method = "sql", type = ImapperSql.class)
// 	int seeFieldBysql4Int(String sql);
//
// 	@SelectProvider(method = "sql", type = ImapperSql.class)
// 	List<T> seeBysql(String sql);
//
// 	@SelectProvider(method = "sql", type = ImapperSql.class)
// 	List<String> seeBysql4listString(String sql);
//
// 	@SelectProvider(method = "sql", type = ImapperSql.class)
// 	List<Map<String, Object>> seeBysql4map(String sql);
//
// 	@SelectProvider(method = "sql", type = ImapperSql.class)
// 	List<Map.Entry<String, String>> getBySql4KV();
//
// 	@UpdateProvider(method = "sql", type = ImapperSql.class)
// 	int upstaBysql(String sql);
//
// 	@DeleteProvider(method = "sql", type = ImapperSql.class)
// 	int deleteBYSql(String sql);
//
// 	@Options(useCache = false)
// 	@Select("SELECT LAST_INSERT_ID()")
// 	int lastInsertId();
//
// 	default <M> List<M> seeBysql4clazz(String sql, Class<M> clazz) {
// 		List<Map<String, Object>> list = seeBysql4map(sql);
// 		List<M> result = Lists.newArrayListWithCapacity(list.size());
// 		list.forEach(t -> result.add(BeanUtil.toBean(t, clazz)));
// 		return result;
// 	}
//
// 	default List<T> selectByLambda(MapperConsumer... taos) {
// 		return selectByExample(war(taos));
// 	}
//
// 	default T selectOneByLambda(MapperConsumer... taos) {
// 		return selectOneByExample(war(taos));
// 	}
//
// 	default int selectCountByLambda(MapperConsumer... taos) {
// 		return selectCountByExample(war(taos));
// 	}
//
// 	default int deleteByLambda(MapperConsumer... taos) {
// 		return deleteByExample(war(taos));
// 	}
//
// 	default int updateByLambdaSelective(T record, MapperConsumer... taos) {
// 		return updateByExampleSelective(record, war(taos));
// 	}
//
// 	default int updateByLambda(T record, MapperConsumer... taos) {
// 		return updateByExample(record, war(taos));
// 	}
//
// 	default Example war(MapperConsumer... taos) {
// 		Example example = new Example(entityClass());
// 		for (Consumer<Criteria> tao : taos)
// 			tao.accept(example.or());
// 		return example;
// 	}
//
// 	/**
// 	 * 使用接口内的lambda方法必须实现该方法
// 	 *
// 	 * @return
// 	 */
// 	Class<T> entityClass();
//
// }
