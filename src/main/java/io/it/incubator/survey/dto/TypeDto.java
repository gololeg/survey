package io.it.incubator.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeDto {

  public static String RADIO_TYPE = "RADIO";
  public static String CHECKBOX_TYPE = "CHECKBOX";
  private int id;
  private String name;
}
