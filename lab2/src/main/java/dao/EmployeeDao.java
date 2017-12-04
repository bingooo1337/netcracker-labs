package dao;

import models.Department;
import models.Employee;

import java.util.ArrayList;

public interface EmployeeDao extends ModelDao<Employee>{
    Employee getManagerOf(Employee emp);
    ArrayList<Department> getDepartments(Employee emp);
}
