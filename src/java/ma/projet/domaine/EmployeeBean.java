/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employee;
import ma.projet.beans.Service;
import ma.projet.service.EmployeeService;
import ma.projet.service.ServiceService;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author YahyaMrd
 */
@ManagedBean(name = "employeeBean")
public class EmployeeBean {

    private Employee employee;
    private List<Employee> employees;
    private Service service;
    private EmployeeService employeeService;
    private boolean chefService = false;
    private ServiceService sc;

    public EmployeeBean() {
        employee = new Employee();
        employee.setService(new Service());
        employeeService = new EmployeeService();
        sc = new ServiceService();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployees() {
        if (employees == null) {
            employees = employeeService.getAll();
        }
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public boolean isChefService() {
        return chefService;
    }

    public void setChefService(boolean chefService) {
        this.chefService = chefService;
    }

    public String onCreateAction() {
        System.out.println("onCreateAction called called");
        if (chefService == false) {
            for (Employee e : employeeService.getAll()) {
                if (e.getService().getId() == employee.getService().getId() && e.getChef() == null) {
                        employee.setChef(e);
                        employeeService.create(employee);
                }
            }
        } else {
            Employee newEmployee = employeeService.createe(employee);
            for (Employee e : employeeService.getAll()) {
                if (e.getService().getId() == newEmployee.getService().getId() && e.getId() != newEmployee.getId()) {
                        e.setChef(newEmployee);
                        employeeService.update(e);
                }
            }
        }
        employee = new Employee();
        return null;
    }

    public void onDeleteAction() {
        System.out.println("onDeleteAction called");
        if (employee != null) {
            System.out.println("Deleting employee with ID: " + employee.getId());
            employee.setService(null);
            employeeService.delete(employee);

        } else {
            System.out.println("Employee is null. Delete operation skipped.");
        }
    }

    public void onEdit(RowEditEvent event) {
        Employee em = (Employee) event.getObject();
        
         for (Employee e : employeeService.getAll()) {
                if (e.getService().getId() == em.getService().getId() && e.getChef() == null) {
                        em.setChef(e);
                }
            }
        employeeService.update(em);
        employees = employeeService.getAll();
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

}
