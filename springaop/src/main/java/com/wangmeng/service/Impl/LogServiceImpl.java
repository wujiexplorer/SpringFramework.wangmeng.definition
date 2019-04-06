package com.wangmeng.service.Impl;

import com.wangmeng.dao.LogDao;
import com.wangmeng.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addLog() {
        logDao.add("addLog:"+System.currentTimeMillis());
        int i = 1/0;
    }
}
