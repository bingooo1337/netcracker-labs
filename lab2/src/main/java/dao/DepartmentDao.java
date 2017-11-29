package dao;

import models.Department;
import models.Employee;

public interface DepartmentDao extends ModelDao<Department>{
    Employee[] getEmployees(Department dep);
}
