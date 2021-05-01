package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.service.api.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shaoruilin
 * @create 2021-05-01-10:39
 */
@Transactional(readOnly = true)
@Service
public class ProjectServiceImpl implements ProjectService {
}
