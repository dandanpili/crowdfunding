package com.atguigu.crowd.handler;

import com.atguigu.crowd.service.api.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaoruilin
 * @create 2021-05-01-10:38
 */
@RestController
public class ProjectProviderHandler {

    @Autowired
    private ProjectService projectService;

}
