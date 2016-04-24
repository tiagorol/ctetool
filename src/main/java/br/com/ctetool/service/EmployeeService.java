
package br.com.ctetool.service;
import java.util.List;

import br.com.ctetool.entity.Employee;

/**
 * @author Ranga Reddy
 * @version 1.0
 */
public interface EmployeeService {
	public long createEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployee(long id);
    public List<Employee> getAllEmployees();
    public Employee getEmployee(long id);	
	public List<Employee> getAllEmployees(String employeeName);
}
