package com.search.location.domain.search.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class NaverLocationResponseVo implements CommonLocationResponseVo {

    private List<NaverLocation> items = new ArrayList<>();

    @Override
    public List<? extends CommonLocation> getResult() {
        return this.items;
    }

    @Getter @Setter
    public static class NaverLocation implements CommonLocation{
        private String title;

        private String roadAddress;

        @Override
        public String getName() {
            return this.title;
        }

        public static NaverLocation from(String name) {
            NaverLocation naverLocation = new NaverLocation();
            naverLocation.title = name;
            return naverLocation;
        }
    }

}
