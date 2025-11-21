package com.itheima.mapper;


import com.itheima.pojo.Customer;
import com.itheima.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {


    @Select("select * from crm_operate_log")
    List<OperateLog> list();
}
