package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.vo.ProjectVO;

/**
 * @author shaoruilin
 * @create 2021-05-01-10:38
 */
public interface ProjectService {

    void saveProject(ProjectVO projectVO, Integer memberId);
}
