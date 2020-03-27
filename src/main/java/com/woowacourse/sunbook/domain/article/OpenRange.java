package com.woowacourse.sunbook.domain.article;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum OpenRange {
    ALL(0),
    ONLY_FRIEND(1),
    NONE(2);

    private Integer openRange;

    public static OpenRange of(Integer openRange) {
        return Arrays.stream(values())
                .filter(or -> openRange.equals(or.openRange))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                ;
    }
}
