package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author shaoruilin
 * @create 2021-05-08-16:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortalTypeVO {

    private Integer id;

    private String name;

    private String remark;

    private List<PortalProjectVO> portalProjectVOList;
}
