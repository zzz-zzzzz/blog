package com.tsu.zzz.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String nickname;
    private String username;
    private String avatar;
    private String password;
    private String email;
}
