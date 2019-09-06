package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import entities.EmployeeEntity;
import model.Employee;
import service.EmployeeEJB;

@ManagedBean(name = "employeecontroller")
@SessionScoped
public class EmployeeController {

	@EJB
	private EmployeeEJB employeeEJB;

	// from form
	@ManagedProperty(value = "#{employee}")
	private Employee employee;

	private List<Employee> employeeList;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public String viewEmployee() {
		return "employeeList.xhtml";
	}

	public String viewAllEmployees() {

		employeeList = new ArrayList<Employee>();
		List<EmployeeEntity> employeeEntities = employeeEJB.getEmployeeEntites();

		for (EmployeeEntity employeeEntity : employeeEntities) {
			
			Employee m = new Employee();
			m.setName(employeeEntity.getName());
			m.setSurName(employeeEntity.getSurName());
			m.setDateOfBirth(employeeEntity.getDateOfBirth());
			employeeList.add(m);
		}

		return "ViewAllEmployees.xhtml";
	}

	public String addNewEmployee() {
		employeeEJB.addNew(employee.getEntity());
		return "employeeList.xhtml";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}