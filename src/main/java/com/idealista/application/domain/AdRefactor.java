package com.idealista.application.domain;

import com.idealista.application.domain.Picture.Quality;
import java.util.Date;
import java.util.List;

public abstract class AdRefactor {

  public enum Typology {
    FLAT,
    CHALET,
    GARAGE,
  }

  Integer id;
  Typology typology;
  String description;
  List<Picture> pictures;

  // fixme: estas propiedades deben estar en las clases hijas.
  //  Un garaje no necesita estas propiedades.
  //  Un piso no tiene jardín.
//  private Integer houseSize;
//  private Integer gardenSize;

  Integer score;
  Date irrelevantSince;

  abstract boolean isComplete();

  abstract int calculateScore();

  int calculatePicturesScore() {
    int score = Constants.ZERO;

    if (pictures.isEmpty()) {
      return -10; //Si no hay fotos restamos 10 puntos
    }

    for (Picture picture : pictures) {
      if (Quality.HD == picture.getQuality()) {
        score += Constants.TWENTY; //Cada foto en alta definición aporta 20 puntos
      } else {
        score += Constants.TEN; //Cada foto normal aporta 10 puntos
      }
    }

    return score;
  }

  public boolean hasDescription() {
    return description != null && !description.isEmpty();
  }

  public boolean isScoreHigherThanMaximumAllowed() {
    return score > Constants.ONE_HUNDRED;
  }

  public boolean isScoreNegative() {
    return score < Constants.ZERO;
  }

  public boolean isScoreConsideredIrrelevant() {
    return score < Constants.FORTY;
  }

  public boolean isIrrelevant() {
    return irrelevantSince != null || isScoreConsideredIrrelevant();
  }
}
