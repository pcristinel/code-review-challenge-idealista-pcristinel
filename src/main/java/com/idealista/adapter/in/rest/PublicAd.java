package com.idealista.adapter.in.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicAd {

  private Integer id;
  private String typology;
  private String description;
  private List<String> pictureUrls;
  private Integer houseSize;
  private Integer gardenSize;
}
