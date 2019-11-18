package com.ly.factory.mapper;

import com.ly.factory.domain.Employee;
import com.ly.factory.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper extends BaseMapper<Employee,Integer> {
    @Select("select * from tb_employee")
    public List<Employee> getEmployees();

    @Select("select * from tb_employee where name=#{name} and password=#{password}")
    public int login(Employee employee);

    @Select("insert into tb_employee (name,factory,company,phone,password,shenfen,zhineng)values(#{name},#{factory},#{company},#{phone},#{password},#{shenfen},#{zhineng})")
    public int reg(Employee employee);

    //用户登录Mapper
    @Select("select * from tb_employee where name=#{username} and password=#{password}")
    public int loginMapper(String username,String password);

    //查询员工根据姓名service
    @Select("select * from tb_employee where name like CONCAT('%',#{name},'%')")
    public List<Employee> searchEmps(String name);

    @Select("select id,name from tb_employee where identity=#{identity}")
    public List<Employee> queryByIdentity(@Param("identity") int identity);
}
