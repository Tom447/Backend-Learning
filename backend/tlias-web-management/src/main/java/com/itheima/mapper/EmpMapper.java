package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PieChartData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {

    /**
     * 分页插叙
     * @param start
     * @param pageSize
     * @return
     */
    //@Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp limit #{start} , #{pageSize}")
    //public List<Emp> page(Integer start , Integer pageSize);

    /**
     * 分页查询-获取总记录数
     * @return
     */
    //@Select("select count(*) from emp")
    //public Long count();


    /**
     * 列表查询
     */
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除
     */
    void delete(List<Integer> ids);

    /**
     * 插入员工
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) values " +
            "(#{username}, #{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    @Select("select id ,username, name, password, gender, image, job, entrydate, dept_id, create_time, update_time from emp where id = #{id}")
    Emp getById(Integer id);

    /**
     * 修改员工
     * @param emp
     */
    void update(Emp emp);


    /**
     * 统计员工的性别数据
     */
    @Select("select if(gender = 1, '男性员工', '女性员工') name, count(*) value from emp group by gender")
    List<PieChartData> countEmpGenderData();


    /**
     * 统计员工的职位信息
     */
    @Select("select (case job" +
            "            when 1 then '班主任'" +
            "            when 2 then '讲师'" +
            "            when 3 then '学工主管'" +
            "            when 4 then '教研主管'" +
            "            when 5 then '咨询师'" +
            "            else '其他' end)  job," +
            "       count(*) jobcount" +
            " from emp" +
            " group by job")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 根据用户名和密码查询员工信息
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getEmpByUsernameAndPassword(Emp emp);

    @Select("select * from emp")
    List<Emp> getAllEmpNoParam();
}
