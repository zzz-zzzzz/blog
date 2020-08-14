package com.tsu.zzz.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String nickName;
    private String username;
    private String avatar;
    private String password;
}
