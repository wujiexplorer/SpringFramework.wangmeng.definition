package com.wangmeng02.service;

import com.wangmeng02.dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogService {

    @Autowired
    private LogDao logDao;

    @Transactional(propagation = Propagation.MANDATORY)//required(update,add,delete） +supports(select)+required_new(log)
    public void addLog(){
        logDao.addLog();
    }
}
