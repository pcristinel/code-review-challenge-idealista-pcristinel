package com.idealista.application.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdChalet extends AdRefactor {

  private static List<String> VALUABLE_WORDS = List.of("luminoso", "nuevo", "céntrico", "reformado");

  private Integer houseSize;
  private Integer gardenSize;

  @Override
  public boolean isComplete() {
    return !pictures.isEmpty()
           && hasDescription()
           && houseSize != null
           && gardenSize != null;
  }

  @Override
  public int calculateScore() {
    int score = Constants.ZERO;

    //Calcular puntuación por fotos
    score += super.calculatePicturesScore();

    //Calcular puntuación por completitud
    if (isComplete()) {
      score += Constants.FORTY;
    }

    // Calcular si tiene descripción
    if (hasDescription()) {
      score += Constants.FIVE;
      score += calculateValuableWordsScore();
    }

    if(isScoreHigherThanMaximumAllowed()) {
      score = Constants.ONE_HUNDRED;
    }

    if (isScoreNegative()) {
      score = Constants.ZERO;
    }

    if (isScoreConsideredIrrelevant()) {
      irrelevantSince = new Date();
    }

    return score;
  }


  private int calculateValuableWordsScore() {
    int score = 0;

    List<String> wds = Arrays.asList(description.split(" ")); //número de palabras

    if (wds.size() >= Constants.FIFTY) {
      score += Constants.TWENTY;
    }

    for (String word : VALUABLE_WORDS) {
      if (description.contains(word)) {
        score += 5;
      }
    }

    return score;
  }
}
