package com.idealista.application.port.out;

import com.idealista.application.domain.Ad;
import java.util.List;

public interface AdPersistenceOutPort {

  List<Ad> findAllAds();

  void save(Ad ad);

  List<Ad> findRelevantAds();

  List<Ad> findIrrelevantAds();
}
