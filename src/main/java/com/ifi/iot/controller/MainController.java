package com.ifi.iot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ifi.iot.entities.Employee;
import com.ifi.iot.repository.EmployeeRepository;

@Controller
@RequestMapping("/")
public final class MainController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping
	public String showEmployees(Model model) {
		List<Employee> listEmployee = employeeRepository.findAll();

		model.addAttribute("listEmployee", listEmployee);

		return "employeesPage";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateEmployee(Model model) {

		model.addAttribute("employee", new Employee());

		return "createPage";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createEmployee(Model model, Employee employee) {
		employeeRepository.save(employee);

		return "redirect:/";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String updateEmployee(@PathVariable("id") String empId, Model model) {
		Employee emp = employeeRepository.findById(Integer.parseInt(empId)).get();

		model.addAttribute("employee", emp);

		return "updatePage";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateEmployee(Model model, Employee employee) {
		System.out.println(employee);
		employeeRepository.save(employee);

		return "redirect:/";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteEmployee(@PathVariable("id") String empId, Model model) {
		employeeRepository.deleteById(Integer.parseInt(empId));

		return "redirect:/";
	}
}
