package models;

import java.util.Arrays;

public class Employee extends Model {
    private String firstName;
    private String lastName;
    private String position;
    private Department[] departments;
    private Employee manager;
    private Integer salary;
    private Integer age;

    public Employee() {
    }

    public Employee(long id) {
        super(id);
    }

    public Employee(String firstName, String lastName, String position, Department[] departments, Employee managerId, int salary, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.departments = departments;
        this.manager = managerId;
        this.salary = salary;
        this.age = age;
    }

    public Employee(long id, String firstName, String lastName, String position, Department[] departments, Employee managerId, int salary, int age) {
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

    public Department[] getDepartments() {
        return departments;
    }

    public void setDepartments(Department[] departments) {
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
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (position != null ? !position.equals(employee.position) : employee.position != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(departments, employee.departments)) return false;
        if (manager != null ? !manager.equals(employee.manager) : employee.manager != null) return false;
        if (salary != null ? !salary.equals(employee.salary) : employee.salary != null) return false;
        return age != null ? age.equals(employee.age) : employee.age == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(departments);
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID='" + getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", departmentsIDs=" + Arrays.toString(departments) +
                ", managerID=" + manager.getId() +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
