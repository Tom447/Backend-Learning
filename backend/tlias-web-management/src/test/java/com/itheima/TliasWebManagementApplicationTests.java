package com.itheima;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    public void testUUID(){
        for (int i = 0; i < 100; i++) {
            System.out.println(UUID.randomUUID().toString());
        }
    }

    @Autowired
    private DeptMapper deptMapper;

    @Test
    public void testGetById(){
        Dept dept = deptMapper.getById(1);
        System.out.println(dept);
    }

}
