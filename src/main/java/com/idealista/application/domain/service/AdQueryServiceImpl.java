package com.idealista.application.domain.service;

import com.idealista.adapter.in.rest.PublicAd;
import com.idealista.adapter.in.rest.QualityAd;
import com.idealista.application.domain.Ad;
import com.idealista.application.domain.Picture;
import com.idealista.application.port.in.AdQueryService;
import com.idealista.application.port.out.AdPersistenceOutPort;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdQueryServiceImpl implements AdQueryService {

  private final AdPersistenceOutPort adPersistenceOutPort;

  @Override
  public List<PublicAd> findPublicAds() {
    List<Ad> ads = adPersistenceOutPort.findRelevantAds();
    ads.sort(Comparator.comparing(Ad::getScore));

    List<PublicAd> result = new ArrayList<>();
    for (Ad ad : ads) {
      PublicAd publicAd = new PublicAd();
      publicAd.setDescription(ad.getDescription());
      publicAd.setGardenSize(ad.getGardenSize());
      publicAd.setHouseSize(ad.getHouseSize());
      publicAd.setId(ad.getId());
      publicAd.setPictureUrls(ad.getPictures().stream().map(Picture::getUrl).collect(Collectors.toList()));
      publicAd.setTypology(ad.getTypology().name());

      result.add(publicAd);
    }
    return result;
  }

  @Override
  public List<QualityAd> findQualityAds() {
    List<Ad> ads = adPersistenceOutPort.findIrrelevantAds();

    List<QualityAd> result = new ArrayList<>();
    for (Ad ad : ads) {
      QualityAd qualityAd = new QualityAd();
      qualityAd.setDescription(ad.getDescription());
      qualityAd.setGardenSize(ad.getGardenSize());
      qualityAd.setHouseSize(ad.getHouseSize());
      qualityAd.setId(ad.getId());
      qualityAd.setPictureUrls(ad.getPictures().stream().map(Picture::getUrl).collect(Collectors.toList()));
      qualityAd.setTypology(ad.getTypology().name());
      qualityAd.setScore(ad.getScore());
      qualityAd.setIrrelevantSince(ad.getIrrelevantSince());

      result.add(qualityAd);
    }

    return result;
  }
}
