package com.idealista.application.domain;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

  public enum Typology {
    FLAT,
    CHALET,
    GARAGE,
  }

  private Integer id;
  private Typology typology;
  private String description;
  private List<Picture> pictures;
  private Integer houseSize;
  private Integer gardenSize;
  private Integer score;
  private Date irrelevantSince;

  // fixme: este método debería estar en las clases hijas.
  public boolean isComplete() {
    return (Typology.GARAGE.equals(typology) && !pictures.isEmpty())
           || (Typology.FLAT.equals(typology) && !pictures.isEmpty() && description != null && !description.isEmpty() && houseSize != null)
           || (Typology.CHALET.equals(typology) && !pictures.isEmpty() && description != null && !description.isEmpty() && houseSize != null
               && gardenSize != null);
  }
}
