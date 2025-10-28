package org.example.ui.client.components;

import jakarta.faces.event.ValueChangeEvent;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class SelectOneMenu<T> implements Serializable {

  @Serial private static final long serialVersionUID = 3_994_753_455_342_550_302L;

  private final List<T> values;

  private String selected;
  private Map<String, String> options = new HashMap<>();

  private T selectedItem;
  private Map<String, T> optionsMap = new HashMap<>();

  public static <T> SelectOneMenu<T> by(final List<T> values,
                                        final Function<T, String> keyFunction,
                                        final Function<T, String> valueFunction) {
    var menu = new SelectOneMenu<T>(values);
    Optional.ofNullable(values)
            .stream()
            .flatMap(List::stream)
            .filter(Objects::nonNull)
            .forEach(t -> menu.getOptions().put(valueFunction.apply(t), keyFunction.apply(t)));

    menu.setOptionsMap(Optional.ofNullable(values)
                               .stream()
                               .flatMap(List::stream)
                               .filter(Objects::nonNull)
                               .collect(Collectors.toMap(keyFunction, Function.identity())));

    return menu;
  }

  public static <T> SelectOneMenu<T> defaultMenu() {
    return new SelectOneMenu<>(List.of());
  }

  public void onChange(ValueChangeEvent event) {
    Optional.ofNullable(event)
            .map(ValueChangeEvent::getNewValue)
            .map(String::valueOf)
            .map(this.getOptionsMap()::get)
            .ifPresent(this::setSelectedItem);
  }

}
