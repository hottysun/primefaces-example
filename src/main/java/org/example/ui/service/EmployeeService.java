package org.example.ui.service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.example.ui.client.bean.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements Serializable {

  @Serial
  private static final long serialVersionUID = 6_865_335_010_065_235_821L;

  public List<Employee> findAll(Integer numberOfEmployees) {
    return Optional.ofNullable(numberOfEmployees)
                   .map(n -> IntStream.range(0, n)
                                      .mapToObj(i ->
                                                    Employee.builder()
                                                            .id(UUID.randomUUID())
                                                            .name("Name" + i)
                                                            .build()))
                   .map(Stream::toList)
                   .orElseGet(List::of);

  }
}
