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
    private Boolean appreciation; //是否开启赞赏
    private Boolean shareStatement; //是否开始转载声明
    private Boolean commentable; //是否开始评论
    private Boolean published; //是否发布
    private Boolean recommend;//是否推荐
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    private Long userId;
    private User user;
    private List<Comment> commentList;
    private Long typeId;
    private Type type;
    private List<Tag> tagList;
    private String description;

    private String tagIds;

    public void initTagIds(List<Tag> tagList) {
        String tagIds = new String();
        for (Tag tag : tagList) {
            tagIds += "," + tag.getId();
        }
        this.tagIds = tagIds;
    }
}
