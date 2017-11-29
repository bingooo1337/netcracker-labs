package daoimpl;

import dao.EmployeeDao;
import models.Department;
import models.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class EmployeeDaoImpl implements EmployeeDao {
    private Connection con;

    public EmployeeDaoImpl(Connection con) {
        this.con = con;
    }

    @Override
    public Employee getManagerOf(Employee emp) {
        return getById(emp.getManager().getId());
    }

    @Override
    public Department[] getDepartments(Employee emp) {
        return new Department[0];
    }

    @Override
    public Employee getById(long id) {
        Statement stmt = null;
        try {
            String query = "SELECT * FROM EMP WHERE EMPNO = " + id;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return extractEmployeeFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
    }

    @Override
    public void insert(Employee model) {
    }

    @Override
    public void update(Employee model) {

    }

    @Override
    public void delete(Employee model) {

    }

    @Override
    public Set<Employee> getAll() {
        return null;
    }

    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee emp = new Employee();

        emp.setId(rs.getInt("EMPNO"));
        emp.setFirstName(rs.getString("ENAME"));
        emp.setLastName(rs.getString("ENAME"));
        emp.setPosition(rs.getString("JOB"));
        emp.setAge((int) emp.getId());
        emp.setDepartments(new Department[]{new Department(rs.getInt("DEPTNO"))});
        emp.setSalary(rs.getInt("SAL"));
        emp.setManager(new Employee(rs.getLong("MGR")));

        return emp;
    }
}
