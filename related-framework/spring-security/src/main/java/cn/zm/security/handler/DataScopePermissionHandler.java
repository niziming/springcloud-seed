package cn.zm.security.handler;

import cn.zm.security.anno.DataScope;
import cn.zm.security.aspect.DataScopeAspect;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.HexValue;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <数据权限处理器>
 * @author 十渊Jermaine jermainenee@yeah.net
 * @version 1.0
 * @date 2022/7/29
*/
@Slf4j
@Component
public class DataScopePermissionHandler implements DataPermissionHandler {

    /**
     * @param where             原SQL Where 条件表达式
     * @param mappedStatementId Mapper接口方法ID
     * @return
     */
    @SneakyThrows
    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        log.debug("DataScopePermissionHandler.getSqlSegment");
        DataScope dataScope = DataScopeAspect.threadLocal.get();
        if (dataScope == null) {
            return where;
        }

        if (where == null) {
            where = new HexValue(" 1 = 1 ");
        }
        String[] scopes = dataScope.scopes();
        List<Expression> collect = Arrays.stream(scopes).map(StringValue::new).collect(Collectors.toList());

        ExpressionList itemsList = new ExpressionList(collect);

        InExpression inExpression = new InExpression(new Column(dataScope.column()), itemsList);
        return new AndExpression(where, inExpression);
    }
}
