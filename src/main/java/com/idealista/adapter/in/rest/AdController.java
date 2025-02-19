package com.idealista.adapter.in.rest;

import com.idealista.application.port.in.AdQueryService;
import com.idealista.application.port.in.CalculateScoreInPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ads")
public class AdController {

  private final AdQueryService adQueryService;
  private final CalculateScoreInPort calculateScoreInPort;

  @GetMapping("/quality")
  public ResponseEntity<List<QualityAd>> qualityListing() {
    return ResponseEntity.ok(adQueryService.findQualityAds());
  }

  @GetMapping("/public")
  public ResponseEntity<List<PublicAd>> publicListing() {
    return ResponseEntity.ok(adQueryService.findPublicAds());
  }

  @GetMapping("/score")
  public ResponseEntity<Void> calculateScore() {
    calculateScoreInPort.invoke();
    return ResponseEntity.accepted().build();
  }
}
