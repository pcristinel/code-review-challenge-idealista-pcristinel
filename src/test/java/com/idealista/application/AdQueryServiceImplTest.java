package com.idealista.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.idealista.application.domain.Ad;
import com.idealista.application.domain.Ad.Typology;
import com.idealista.application.domain.Picture;
import com.idealista.application.domain.Picture.Quality;
import com.idealista.application.domain.service.AdQueryServiceImpl;
import com.idealista.application.port.out.AdPersistenceOutPort;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AdQueryServiceImplTest {

    @Mock
    private AdPersistenceOutPort adPersistenceOutPort;

    @InjectMocks
    private AdQueryServiceImpl scoreService;

    @Test
    public void calculateScoresTest() {
        when(adPersistenceOutPort.findAllAds()).thenReturn(Arrays.asList(irrelevantAd(), relevantAd()));
        scoreService.calculateScores();
        verify(adPersistenceOutPort).findAllAds();
        verify(adPersistenceOutPort, times(2)).save(any());
    }

    private Ad relevantAd() {
        return Ad.builder()
            .id(1)
            .typology(Typology.FLAT)
            .description("""
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras dictum felis elit, vitae cursus erat blandit vitae. Maecenas
                 eget efficitur massa. Maecenas ut dolor eget enim consequat iaculis vitae nec elit. Maecenas eu urna nec massa feugiat
                  pharetra. Sed eu quam imperdiet orci lobortis fermentum. Sed odio justo, congue eget iaculis.""")
            .pictures(Arrays.asList(new Picture(1, "http://urldeprueba.com/1", Quality.HD), new Picture(2, "http://urldeprueba.com/2", Quality.HD)))
            .houseSize(50)
            .build();
    }

    private Ad irrelevantAd() {
        return Ad.builder()
            .id(1)
            .typology(Typology.FLAT)
            .houseSize(100)
            .build();
    }

}