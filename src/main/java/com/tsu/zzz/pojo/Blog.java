package com.tsu.zzz.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Blog {
    private Long id;
    private String title; //标题
    private String content; //内容
    private String firstPicture; //首图
    private String flag;
    private Integer views; //浏览次数
    private Integer appreciation; //是否开启赞赏
    private Integer shareStatement; //是否开始转载声明
    private Integer commentTabled; //是否开始评论
    private Integer publicShed; //是否发布
    private Integer recommend;//是否推荐
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    private Long userId;
    private User user;
    private List<Comment> commentList;
    private Long typeId;
    private Type type;
    private List<Tag> tagList;
}
