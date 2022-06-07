package org.ali.service.impl;

import org.ali.dao.AdminDao;
import org.ali.entity.Admin;
import org.ali.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;


    @Override
    public boolean findByUsernameAndPassword(Admin admin) {
        return adminDao.findByUsernameAndPassword(admin) > 0;
    }

}
