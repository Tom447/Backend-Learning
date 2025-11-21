package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer id; //ID, 主键
    private String name; //客户姓名
    private String phone; //手机号
    private Short gender; //性别, 1:男, 2:女
    private Short age; //年龄
    private String qq; //QQ
    private String description; //描述
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
