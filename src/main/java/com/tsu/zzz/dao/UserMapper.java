package com.tsu.zzz.dao;

import com.tsu.zzz.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where username=#{username} and password=#{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("select id,nickname,avatar from t_user where id=#{id}")
    User findById(Long id);
}
