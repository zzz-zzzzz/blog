package com.tsu.zzz.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Comment {
    private Long id;
    private String nickName;
    private String email;
    private String avatar;
    private Date createTime;
    private Long parentId;
    private Long blogId;
}
