package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shaoruilin
 * @create 2021-05-08-16:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortalProjectVO {
    private Integer projectId;

    private String projectName;

    private String headerPicturePath;

    private Integer money;

    private String deployDate;

    private Integer percentage;

    private Integer supporter;
}
