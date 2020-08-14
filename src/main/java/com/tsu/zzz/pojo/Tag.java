package com.tsu.zzz.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Tag {
    private Long id;
    private String name;
    private List<Blog> blogList;
}
