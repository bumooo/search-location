package com.search.location.domain.search.service;

import com.search.location.domain.search.enums.PlatForm;
import com.search.location.domain.search.service.impl.KakaoSearchApiServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchServiceProviderFactoryTest {

    @Autowired
    private SearchServiceProviderFactory searchServiceProviderFactory;

    @Test
    @DisplayName("kakaoSearchServiceImpl 가져오기")
    void getService () throws Exception {
        // given
        PlatForm platForm = PlatForm.KAKAO;

        // when
        SearchApiService service = searchServiceProviderFactory.getService(platForm);

        // then
        Assertions.assertThat(service.getClass()).isEqualTo(KakaoSearchApiServiceImpl.class);
    }
}