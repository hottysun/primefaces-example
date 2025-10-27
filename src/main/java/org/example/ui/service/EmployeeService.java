package org.example.ui.service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.IntStream;
import org.example.ui.client.bean.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements Serializable {

  @Serial private static final long serialVersionUID = 6_865_335_010_065_235_821L;

  public List<Employee> findAll(Integer numberOfEmployees) {
    return IntStream.range(0, numberOfEmployees)
                    .mapToObj(i -> Employee.builder().id(i).name("Name" + i).build())
                    .toList();
  }
}
