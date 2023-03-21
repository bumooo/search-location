package com.search.location.domain.location.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationSearchResultVo implements Comparable<LocationSearchResultVo> {

    private String title;

    @JsonIgnore
    private int score;

    @JsonIgnore
    private int index;

    public static LocationSearchResultVo of(String title, LocationScoreVo scoreVo) {
        LocationSearchResultVo locationSearchResultVo = new LocationSearchResultVo();
        locationSearchResultVo.title = title;
        locationSearchResultVo.score = scoreVo.getScore();
        locationSearchResultVo.index = scoreVo.getIndex();
        return locationSearchResultVo;
    }

    // priority 비교 후 같다면 index 비교하여 정렬
    @Override
    public int compareTo(LocationSearchResultVo o) {
        if (this.score == o.score) {
            return Integer.compare(this.index, o.index);
        }
        return Integer.compare(o.score, this.score);
    }
}
