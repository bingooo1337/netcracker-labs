package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Employee extends Model {
    //TODO IMPLEMENT PARENT ID
    private String firstName;
    private String lastName;
    private String position;
    private ArrayList<Department> departments;
    private Employee manager;
    private Integer salary;
    private Integer age;

    public Employee() {
    }

    public Employee(long id) {
        super(id);
    }

    public Employee(String firstName, String lastName, String position, ArrayList<Department> departments, Employee managerId, int salary, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.departments = departments;
        this.manager = managerId;
        this.salary = salary;
        this.age = age;
    }

    public Employee(long id, String firstName, String lastName, String position, ArrayList<Department> departments, Employee managerId, int salary, int age) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.departments = departments;
        this.manager = managerId;
        this.salary = salary;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (position != null ? !position.equals(employee.position) : employee.position != null) return false;
        if (departments != null ? !departments.equals(employee.departments) : employee.departments != null)
            return false;
        if (manager != null ? !manager.equals(employee.manager) : employee.manager != null) return false;
        if (salary != null ? !salary.equals(employee.salary) : employee.salary != null) return false;
        return age != null ? age.equals(employee.age) : employee.age == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (departments != null ? departments.hashCode() : 0);
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String departmentsIds;
        if (departments.size() > 1) {
            departmentsIds = "[";
            departmentsIds += departments.stream()
                    .map(dep -> String.valueOf(dep.getId()))
                    .collect(Collectors.joining(", "));
            departmentsIds += "]";
        } else departmentsIds = String.valueOf(departments.get(0).getId());
        String managerID = manager == null ? "null" : String.valueOf(manager.getId());
        return "Employee {" +
                "\n\tID = '" + getId() + '\'' +
                ", \n\tfirstName = '" + firstName + '\'' +
                ", \n\tlastName = '" + lastName + '\'' +
                ", \n\tposition = '" + position + '\'' +
                ", \n\tdepartments = " + departmentsIds +
                ", \n\tmanagerID = " + managerID +
                ", \n\tsalary = " + salary +
                ", \n\tage = " + age +
                "\n}";
    }
}
