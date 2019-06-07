package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.Employees;

@Mapper
public interface EmployeeMapper {
	@Select("select employee_id as employeeId,first_name as firstName,last_name as lastName,version from employees")
	List<Employees> findAll();

	@Select("select employee_id as employeeId,first_name as firstName,last_name as lastName,version from employees where employee_id=#{id}")
	Employees findById(Long id);

	@Insert("insert into employees(first_name,last_name,version) values(#{firstName},#{lastName},0)")
	// @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before
	// = false, resultType = Integer.class)
	void insert(Employees employees);

	@Update("update employees set first_name = #{firstName}, last_name =#{lastName} ,version = #{version}+1 where version = #{version} and employee_id = #{employeeId}  ")
	boolean update(Employees employees);
}
