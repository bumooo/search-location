package com.search.location.domain.search.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class KakaoLocationResponseVo implements CommonLocationResponseVo {

    private List<KakaoLocation> documents = new ArrayList<>();

    @Override
    public List<? extends CommonLocation> getResult() {
        return this.documents;
    }

    @Getter @Setter
    public static class KakaoLocation implements CommonLocation {
        private String placeName;

        @Override
        public String getName() {
            return this.placeName;
        }

        public static KakaoLocation from(String name) {
            KakaoLocation kakaoLocation = new KakaoLocation();
            kakaoLocation.placeName = name;
            return kakaoLocation;
        }
    }
}
