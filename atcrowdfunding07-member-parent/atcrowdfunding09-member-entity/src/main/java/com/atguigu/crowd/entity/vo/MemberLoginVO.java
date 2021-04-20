package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shaoruilin
 * @create 2021-04-20-12:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginVO {
    private Integer id;

    private String username;

    private String email;
}
