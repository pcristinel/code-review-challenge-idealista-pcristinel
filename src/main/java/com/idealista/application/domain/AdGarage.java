package com.idealista.application.domain;

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
public class AdGarage extends AdRefactor {

  private static List<String> VALUABLE_WORDS = List.of("luminoso", "nuevo", "céntrico");

  @Override
  public boolean isComplete() {
    return !pictures.isEmpty();
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

    // Calcular score descripción
    if (hasDescription()) {
      score += Constants.FIVE;
      score += calculateValuableWordsScore();
    }

    return score;
  }

  private int calculateValuableWordsScore() {
    int score = 0;

    for (String word : VALUABLE_WORDS) {
      if (description.contains(word)) {
        score += 5;
      }
    }

    return score;
  }
}
