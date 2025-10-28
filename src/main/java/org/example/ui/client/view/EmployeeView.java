package org.example.ui.client.view;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ui.client.bean.Employee;
import org.example.ui.client.components.SelectOneMenu;
import org.example.ui.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("employeeView")
@Scope("view")
@Data
@NoArgsConstructor
public class EmployeeView implements Serializable {

  @Serial private static final long serialVersionUID = -4_650_119_999_277_610_464L;

  private EmployeeService employeeService;

  private Integer numberOfEmployees;
  private SelectOneMenu<Employee> employeeMenu = SelectOneMenu.defaultMenu();

  @Autowired
  public EmployeeView(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public void buildEmployeeMenu() {
    this.employeeMenu = SelectOneMenu.by(employeeService.findAll(numberOfEmployees),
                                         e -> e.getId().toString(), Employee::getName);
  }

}
