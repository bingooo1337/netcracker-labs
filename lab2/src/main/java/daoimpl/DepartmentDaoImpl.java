package daoimpl;

import dao.DepartmentDao;
import models.Department;
import models.Employee;

import java.util.Set;

public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public Employee[] getEmployees(Department dep) {
        return new Employee[0];
    }

    @Override
    public Department getById(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void insert(Department model) {

    }

    @Override
    public void update(Department model) {

    }

    @Override
    public void delete(Department model) {

    }

    @Override
    public Set<Department> getAll() {
        return null;
    }
}
