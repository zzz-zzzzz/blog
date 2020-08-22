package com.tsu.zzz.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String avatar;
    private Date createTime;
    private Long parentId;
    private Long blogId;
    private String content;
    private List<Comment> replyComments;
    private Comment parentComment;
    private Boolean adminComment;
}
