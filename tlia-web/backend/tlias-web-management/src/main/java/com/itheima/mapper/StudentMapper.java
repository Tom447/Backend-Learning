package com.itheima.mapper;

import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    /**
     * 添加学生
     * @param student
     */
    @Insert("insert into student(name, no, gender, phone, degree, clazz_id, create_time, update_time) VALUES " +
            "(#{name},#{no},#{gender},#{phone},#{degree},#{clazzId},#{createTime},#{updateTime})")
    void insert(Student student);

    /**
     * 动态条件查询
     * @param name
     * @param no
     * @param clazzId
     * @return
     */
    List<Student> list(String name, String no,Integer degree, Integer clazzId);

    /**
     * 根据ID查询学生信息
     * @param id
     * @return
     */
    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    /**
     * 修改学生信息
     * @param student
     */
    void update(Student student);

    /**
     * 批量删除学生信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 违纪处理
     * @param id
     * @param score
     */
    @Update("update student set violation_count = violation_count +1 , violation_score = violation_score + #{score} , update_time = now() where id = #{id}")
    void updateViolation(Integer id, Integer score);

}
