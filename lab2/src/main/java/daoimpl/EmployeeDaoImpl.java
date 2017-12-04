package daoimpl;

import dao.EmployeeDao;
import models.Department;
import models.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final int EMPLOYEE_TYPE_ID = 1;
    private static final int EMPNO_ATTR_ID = 11;
    private static final int FNAME_ATTR_ID = 12;
    private static final int LNAME_ATTR_ID = 13;
    private static final int POS_ATTR_ID = 14;
    private static final int DEPT_ATTR_ID = 15;
    private static final int MGR_ATTR_ID = 16;
    private static final int SAL_ATTR_ID = 17;
    private static final int AGE_ATTR_ID = 18;
    private Connection con;

    public EmployeeDaoImpl(Connection con) {
        this.con = con;
    }

    @Override
    public Employee getManagerOf(Employee emp) {
        return getById(emp.getManager().getId());
    }

    @Override
    public ArrayList<Department> getDepartments(Employee emp) {
        return null;
    }

    @Override
    public Employee getById(long id) {
        Statement stmt = null;
        try {
            String query = "SELECT\n" +
                    "  EMPNO.NUMBER_VALUE EMPNO, FI_NAME.TEXT_VALUE FIRST_NAME, L_NAME.TEXT_VALUE LAST_NAME, POS.TEXT_VALUE POSITION,\n" +
                    "  MGR_ID.TO_OBJECT_ID MANAGER, DEPT_ID.TO_OBJECT_ID DEPARTMENT, AGE.NUMBER_VALUE AGE, SAL.NUMBER_VALUE SALARY\n" +
                    "FROM OBJECTS\n" +
                    "  INNER JOIN OBJECT_TYPES ON OBJECT_TYPES.OBJECT_TYPE_ID = OBJECTS.OBJECT_TYPE_ID\n" +
                    "  INNER JOIN ATTRIBUTES EMPNO_ATTR ON EMPNO_ATTR.OBJECT_TYPE_ID = OBJECT_TYPES.OBJECT_TYPE_ID\n" +
                    "                                      AND EMPNO_ATTR.NAME = 'empno'\n" +
                    "  INNER JOIN PARAMS EMPNO ON EMPNO.ATTR_ID = EMPNO_ATTR.ATTR_ID AND\n" +
                    "                             EMPNO.OBJECT_ID = OBJECTS.OBJECT_ID\n" +
                    "  INNER JOIN ATTRIBUTES FI_NAME_ATTR ON FI_NAME_ATTR.OBJECT_TYPE_ID = OBJECT_TYPES.OBJECT_TYPE_ID\n" +
                    "                                        AND FI_NAME_ATTR.NAME = 'first_name'\n" +
                    "  INNER JOIN PARAMS FI_NAME ON FI_NAME.ATTR_ID = FI_NAME_ATTR.ATTR_ID AND\n" +
                    "                               FI_NAME.OBJECT_ID = OBJECTS.OBJECT_ID\n" +
                    "  INNER JOIN ATTRIBUTES L_NAME_ATTR ON L_NAME_ATTR.OBJECT_TYPE_ID = OBJECT_TYPES.OBJECT_TYPE_ID\n" +
                    "                                       AND L_NAME_ATTR.NAME = 'last_name'\n" +
                    "  INNER JOIN PARAMS L_NAME ON L_NAME.ATTR_ID = L_NAME_ATTR.ATTR_ID AND\n" +
                    "                              L_NAME.OBJECT_ID = OBJECTS.OBJECT_ID\n" +
                    "  INNER JOIN ATTRIBUTES POS_ATTR ON POS_ATTR.OBJECT_TYPE_ID = OBJECT_TYPES.OBJECT_TYPE_ID\n" +
                    "                                    AND POS_ATTR.NAME = 'position'\n" +
                    "  INNER JOIN PARAMS POS ON POS.ATTR_ID = POS_ATTR.ATTR_ID AND\n" +
                    "                           POS.OBJECT_ID = OBJECTS.OBJECT_ID\n" +
                    "  INNER JOIN ATTRIBUTES MGR_ID_ATTR ON MGR_ID_ATTR.OBJECT_TYPE_ID = OBJECT_TYPES.OBJECT_TYPE_ID\n" +
                    "                                       AND MGR_ID_ATTR.NAME = 'manager_reference'\n" +
                    "  INNER JOIN REFERENCES MGR_ID ON MGR_ID.ATTR_ID = MGR_ID_ATTR.ATTR_ID AND\n" +
                    "                                  MGR_ID.FROM_OBJECT_ID = OBJECTS.OBJECT_ID\n" +
                    "  INNER JOIN ATTRIBUTES DEPT_ID_ATTR ON DEPT_ID_ATTR.OBJECT_TYPE_ID = OBJECT_TYPES.OBJECT_TYPE_ID\n" +
                    "                                        AND DEPT_ID_ATTR.NAME = 'department_reference'\n" +
                    "  INNER JOIN REFERENCES DEPT_ID ON DEPT_ID.ATTR_ID = DEPT_ID_ATTR.ATTR_ID AND\n" +
                    "                                   DEPT_ID.FROM_OBJECT_ID = OBJECTS.OBJECT_ID\n" +
                    "  INNER JOIN ATTRIBUTES AGE_ATTR ON AGE_ATTR.OBJECT_TYPE_ID = OBJECT_TYPES.OBJECT_TYPE_ID\n" +
                    "                                    AND AGE_ATTR.NAME = 'age'\n" +
                    "  INNER JOIN PARAMS AGE ON AGE.ATTR_ID = AGE_ATTR.ATTR_ID AND\n" +
                    "                           AGE.OBJECT_ID = OBJECTS.OBJECT_ID\n" +
                    "  INNER JOIN ATTRIBUTES SAL_ATTR ON SAL_ATTR.OBJECT_TYPE_ID = OBJECT_TYPES.OBJECT_TYPE_ID\n" +
                    "                                    AND SAL_ATTR.NAME = 'salary'\n" +
                    "  INNER JOIN PARAMS SAL ON SAL.ATTR_ID = SAL_ATTR.ATTR_ID AND\n" +
                    "                           SAL.OBJECT_ID = OBJECTS.OBJECT_ID\n" +
                    "WHERE EMPNO.NUMBER_VALUE = " + id + " AND OBJECTS.OBJECT_ID = " + id;

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                Employee emp = extractEmployeeFromResultSet(rs);
                while (rs.next()) {
                    emp.getDepartments().add(new Department(rs.getLong("DEPARTMENT")));
                }
                return emp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null)
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
    }

    @Override
    public void insert(Employee model) {
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            con.setAutoCommit(false);

            long empId = model.getId();
            long mgrId = model.getManager() == null ? model.getId() : model.getManager().getId();

            stmt.addBatch("INSERT INTO OBJECTS (OBJECT_ID, OBJECT_TYPE_ID) VALUES (" +
                    empId + "," + EMPLOYEE_TYPE_ID + ")");
            stmt.addBatch("INSERT INTO PARAMS (NUMBER_VALUE, ATTR_ID, OBJECT_ID) VALUES (" +
                    empId + "," + EMPNO_ATTR_ID + "," + empId + ")");
            stmt.addBatch("INSERT INTO PARAMS (TEXT_VALUE, ATTR_ID, OBJECT_ID) VALUES (\'" +
                    model.getFirstName() + "\'," + FNAME_ATTR_ID + "," + empId + ")");
            stmt.addBatch("INSERT INTO PARAMS (TEXT_VALUE, ATTR_ID, OBJECT_ID) VALUES (\'" +
                    model.getLastName() + "\'," + LNAME_ATTR_ID + "," + empId + ")");
            stmt.addBatch("INSERT INTO PARAMS (TEXT_VALUE, ATTR_ID, OBJECT_ID) VALUES (\'" +
                    model.getPosition() + "\'," + POS_ATTR_ID + "," + empId + ")");
            stmt.addBatch("INSERT INTO PARAMS (NUMBER_VALUE, ATTR_ID, OBJECT_ID) VALUES (" +
                    model.getSalary() + "," + SAL_ATTR_ID + "," + empId + ")");
            stmt.addBatch("INSERT INTO PARAMS (NUMBER_VALUE, ATTR_ID, OBJECT_ID) VALUES (" +
                    model.getAge() + "," + AGE_ATTR_ID + "," + empId + ")");
            stmt.addBatch("INSERT INTO REFERENCES (ATTR_ID, FROM_OBJECT_ID, TO_OBJECT_ID) VALUES (" +
                    MGR_ATTR_ID + "," + empId + "," + mgrId + ")");

            for (Department dep : model.getDepartments()) {
                stmt.addBatch("INSERT INTO REFERENCES (ATTR_ID, FROM_OBJECT_ID, TO_OBJECT_ID) VALUES (" +
                        DEPT_ATTR_ID + "," + model.getId() + "," + dep.getId() + ")");
            }

            stmt.executeBatch();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null)
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
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
        emp.setFirstName(rs.getString("FIRST_NAME"));
        emp.setLastName(rs.getString("LAST_NAME"));
        emp.setPosition(rs.getString("POSITION"));
        emp.setAge(rs.getInt("AGE"));
        ArrayList<Department> departments = new ArrayList<>();
        departments.add(new Department(rs.getLong("DEPARTMENT")));
        emp.setDepartments(departments);
        emp.setSalary(rs.getInt("SALARY"));
        if (rs.getLong("MANAGER") != emp.getId())
            emp.setManager(new Employee(rs.getLong("MANAGER")));

        return emp;
    }
}
