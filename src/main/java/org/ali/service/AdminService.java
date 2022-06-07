package org.ali.service;

import org.ali.entity.Admin;

public interface AdminService {
    public boolean findByUsernameAndPassword(Admin admin);
}
