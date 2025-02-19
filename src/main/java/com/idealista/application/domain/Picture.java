package com.idealista.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Picture {

  public enum Quality {
    HD,
    SD,
  }

  private Integer id;
  private String url;
  private Quality quality;
}
