package com.itheima.service.impl;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.LogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageBean;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LogServiceImpl implements LogService {

    
    @Autowired
    LogMapper logMapper;
    
    @Override
    public PageBean page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OperateLog> list = logMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) list;
        return new PageBean(p.getTotal(), p.getResult());
    }
}
