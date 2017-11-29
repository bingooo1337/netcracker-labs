package dao;

import models.Department;
import models.Employee;

public interface EmployeeDao extends ModelDao<Employee>{
    Employee getManagerOf(Employee emp);
    Department[] getDepartments(Employee emp);
}
