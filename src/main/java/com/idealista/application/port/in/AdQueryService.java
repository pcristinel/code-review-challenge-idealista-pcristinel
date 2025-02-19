package com.idealista.application.port.in;

import com.idealista.adapter.in.rest.PublicAd;
import com.idealista.adapter.in.rest.QualityAd;
import java.util.List;

public interface AdQueryService {

  List<PublicAd> findPublicAds();

  List<QualityAd> findQualityAds();
}
