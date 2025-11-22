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

    @Insert("INSERT INTO crm_operate_log\n" +
            "        (id, url, target_class, target_method, method_params, method_return,\n" +
            "         client_browser, client_platform, operate_time, cost_time)\n" +
            "        VALUES\n" +
            "        (#{id}, #{url}, #{targetClass}, #{targetMethod}, #{methodParams},\n" +
            "         #{methodReturn}, #{clientBrowser}, #{clientPlatform}, #{operateTime}, #{costTime})")
    void insert(OperateLog operateLog);
}
