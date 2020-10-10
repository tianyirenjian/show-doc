package com.tianyisoft.showdoc.config;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Component
@Intercepts(@Signature(
        type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class UpdateInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement stmt = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sct = stmt.getSqlCommandType();
        Object param = invocation.getArgs()[1];
        Field[] fields = param.getClass().getDeclaredFields();
        if (sct.equals(SqlCommandType.INSERT)) {
            for (Field field: fields) {
                if (field.getName().equals("createdAt") || field.getName().equals("updatedAt")) {
                    field.setAccessible(true);
                    field.set(param, LocalDateTime.now());
                    field.setAccessible(false);
                }
            }
        } else if (sct.equals(SqlCommandType.UPDATE)) {
            for (Field field: fields) {
                if (field.getName().equals("updatedAt")) {
                    field.setAccessible(true);
                    field.set(param, LocalDateTime.now());
                    field.setAccessible(false);
                }
            }
        }
        return invocation.proceed();
    }
}
