package com.tsu.zzz.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.tsu.zzz.dao")
public class MybatisConfig {
}
