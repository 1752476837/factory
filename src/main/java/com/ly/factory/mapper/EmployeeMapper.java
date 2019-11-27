package com.ly.factory.mapper;

import com.ly.factory.domain.Employee;
import com.ly.factory.utils.BaseMapper;
import org.apache.ibatis.annotations.*;
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

    @Select("select id,name from tb_employee where identity=#{identity} and state = 1 and skill like CONCAT('%',#{skill},'%')")
    public List<Employee> queryByIdentity(@Param("identity") int identity,@Param("skill") String skill);

    //查询手机号是否存在
    @Select("select COUNT(*) from tb_employee where phone = #{phone}")
    public Integer selectByPhone(String phone);

    //无条件查询emp列表
    @Select("select id,name,company,factory,phone,identity,skill,state from tb_employee")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "company",column = "company",one = @One(select = "com.ly.factory.mapper.EmployeeMapper.queryTitleByCompany")),
            @Result(property = "factory",column = "factory",one = @One(select = "com.ly.factory.mapper.EmployeeMapper.queryTitleByFactory")),
            @Result(property = "phone",column = "phone"),
            @Result(property = "identity",column = "identity"),
            @Result(property = "skill",column = "skill"),
            @Result(property = "state",column = "state")
    })
    public List<Employee> queryList();

    //按名称查询emp列表
    @Select("select id,name,company,factory,phone,identity,skill,state from tb_employee where name like CONCAT('%',#{name},'%')")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "company",column = "company",one = @One(select = "com.ly.factory.mapper.EmployeeMapper.queryTitleByCompany")),
            @Result(property = "factory",column = "factory",one = @One(select = "com.ly.factory.mapper.EmployeeMapper.queryTitleByFactory")),
            @Result(property = "phone",column = "phone"),
            @Result(property = "identity",column = "identity"),
            @Result(property = "skill",column = "skill"),
            @Result(property = "state",column = "state")
    })
    public List<Employee> queryListByName(String name);


    //按状态查询emp列表
    @Select("select id,name,company,factory,phone,identity,skill,state from tb_employee where state = #{state}")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "company",column = "company",one = @One(select = "com.ly.factory.mapper.EmployeeMapper.queryTitleByCompany")),
            @Result(property = "factory",column = "factory",one = @One(select = "com.ly.factory.mapper.EmployeeMapper.queryTitleByFactory")),
            @Result(property = "phone",column = "phone"),
            @Result(property = "identity",column = "identity"),
            @Result(property = "skill",column = "skill"),
            @Result(property = "state",column = "state")
    })
    public List<Employee> queryListByState(Integer state);


    //按名称+状态查询emp列表
    @Select("select id,name,company,factory,phone,identity,skill,state from tb_employee where name like CONCAT('%',#{name},'%') and state = #{state}")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "company",column = "company",one = @One(select = "com.ly.factory.mapper.EmployeeMapper.queryTitleByCompany")),
            @Result(property = "factory",column = "factory",one = @One(select = "com.ly.factory.mapper.EmployeeMapper.queryTitleByFactory")),
            @Result(property = "phone",column = "phone"),
            @Result(property = "identity",column = "identity"),
            @Result(property = "skill",column = "skill"),
            @Result(property = "state",column = "state")
    })
    public List<Employee> queryListByNameAndState(@Param("name") String name,@Param("state") Integer state);


    @Select("select title from tb_company where id = #{id}")
    public String queryTitleByCompany(Integer id);

    @Select("select title from tb_factory where id = #{id}")
    public String queryTitleByFactory(Integer id);

    //修改员工状态根据id
    @Update("update tb_employee set state=#{state} where id =#{id}")
    void updateStateById(@Param("id") Integer id, @Param("state") Integer state);
}
