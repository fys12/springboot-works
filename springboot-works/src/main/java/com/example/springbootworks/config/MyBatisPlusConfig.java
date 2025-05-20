package com.example.springbootworks.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.example.springbootworks.domain.TbSignal;
import com.example.springbootworks.utils.DynamicTableNameUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Configuration
@MapperScan("com.example.springbootworks.mapper")
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 定义SQL拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        DynamicTableNameInnerInterceptor dynamicTableNameInterceptor = new DynamicTableNameInnerInterceptor();

        Map<String, TableNameHandler> handlerMap = new HashMap<>();
        // 设置要进行动态表名替换的表
        handlerMap.put("tb_signal", new TableNameHandler() {
            @Override
            public String dynamicTableName(String sql, String originalTableName) {
                // 从上下文中获取动态表名,没有则返回默认表名
                String dynamicName = DynamicTableNameUtil.getTableName();
                return (dynamicName != null) ? dynamicName :
                        "tb_signal_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
            }
        });
        // 设置动态表名
        dynamicTableNameInterceptor.setTableNameHandlerMap(handlerMap);
        // 添加拦截器
        interceptor.addInnerInterceptor(dynamicTableNameInterceptor);
        return interceptor;
    }

}