package org.example.ui.client.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Employee implements Serializable {

  @Serial private static final long serialVersionUID = -2_586_491_365_201_383_627L;

  private UUID id;
  private String name;
}
