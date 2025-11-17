package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 添加班级信息
     * @param clazz
     */
    @Insert("insert into clazz VALUES (null,#{name},#{room},#{beginDate},#{endDate},#{masterId},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    /**
     * 动态条件查询
     * @param name
     * @param begin
     * @param end
     * @return
     */
    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    /**
     * 根据ID查询班级详情
     * @param id
     * @return
     */
    @Select("select * from clazz where  id = #{id}")
    Clazz getInfo(Integer id);

    /**
     * 动态更新班级信息
     * @param clazz
     */
    void update(Clazz clazz);

    /**
     * 根据ID删除班级
     * @param id
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 查询全部班级
     * @return
     */
    @Select("select * from clazz")
    List<Clazz> findAll();
}
