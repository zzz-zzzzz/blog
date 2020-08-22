package com.tsu.zzz.dao;


import com.tsu.zzz.pojo.Blog;
import com.tsu.zzz.pojo.Comment;
import org.apache.ibatis.annotations.*;

import javax.xml.ws.ResponseWrapper;
import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from t_comment where blog_id=#{blogId} and parent_id=#{parentId} order by create_time")
    @Results(id = "commentResult01", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "nickname", property = "nickname"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "blog_id", property = "blogId"),
            @Result(column = "email", property = "email"),
            @Result(column = "avatar", property = "avatar"),
            @Result(column = "adminComment", property = "adminComment"),
            @Result(property = "replyComments", column = "id", many = @Many(select = "com.tsu.zzz.dao.CommentMapper.findByParentId")),
            @Result(property = "parentComment", column = "parent_id", one = @One(select = "com.tsu.zzz.dao.CommentMapper.findById"))
            })
    List<Comment> findByBlogIdAndParentId(@Param("blogId") Long blogId, @Param("parentId") Long parentId);

    @Insert("insert into t_comment(nickname,email,content,avatar,create_time,blog_id,parent_id,adminComment) values(#{nickname},#{email},#{content},#{avatar},#{createTime},#{blogId},#{parentId},#{adminComment})")
    void save(Comment comment);

    @Select("select * from t_comment where parent_id=#{parentId} order by create_time")
    @ResultMap("commentResult01")
    List<Comment> findByParentId(Long parentId);

    @Results(id = "commentResult02",value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "nickname", property = "nickname"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "blog_id", property = "blogId"),
            @Result(column = "email", property = "email"),
            @Result(column = "avatar", property = "avatar"),
            @Result(column = "adminComment", property = "adminComment"),
    })
    @Select("select * from t_comment where id=#{id}")
    Comment findById(Long id);

}
