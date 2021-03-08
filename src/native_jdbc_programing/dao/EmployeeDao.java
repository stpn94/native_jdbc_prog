package native_jdbc_programing.dao;

import java.util.List;

import native_jdbc_programing.dto.Department;
import native_jdbc_programing.dto.Employee;
import native_jdbc_programing.dto.Title;

// Data Access Object
public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();
	
	Employee selectEmployeeByNo(Employee employee);

	int insertEmployee(Employee employee); // 객체

	int updateEmployee(Employee employee);

	int deleteEmployee(int EmployeeNo); // 기본타입

	List<Employee> selectEmployeeBydeptNo(Department department);
}
