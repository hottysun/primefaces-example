package org.example.ui.client.view;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.ui.client.bean.Employee;
import org.example.ui.service.EmployeeService;

@Data
@RequiredArgsConstructor
@Named
@ViewScoped
public class EmployeeView implements Serializable {

  @Serial private static final long serialVersionUID = -4_650_119_999_277_610_464L;

  private final EmployeeService employeeService;

  private Integer numberOfEmployees;

  public List<Employee> findEmployees() {
    return employeeService.findAll(numberOfEmployees);
  }
}
