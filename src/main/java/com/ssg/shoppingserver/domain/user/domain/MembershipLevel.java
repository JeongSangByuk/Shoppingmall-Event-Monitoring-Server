package com.ssg.shoppingserver.domain.user.domain;

import ch.qos.logback.core.joran.action.AppenderRefAction;
import com.ssg.shoppingserver.domain.product.domain.ProductCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum MembershipLevel {

    // SSG.com 회원 등급 체계 => [ VIP, GOLD, FRIENDS ]

    VIP(1L,"VIP_MEMBERSHIP",  10L),
    GOLD(2L,"GOLD_MEMBERSHIP",  7L),
    FRIENDS(3L,"FRIENDS_MEMBERSHIP",  5L),
    ERROR(4L,"ERROR",  0L);


    private final Long code;
    private final String title;
    private final Long discountRate;

    // find by code
    public static MembershipLevel findByCode(Long code) {
        return Arrays.stream(MembershipLevel.values())
                .filter( membershipLevel -> membershipLevel.getCode() == code)
                .findFirst()
                .orElse(ERROR);
    }

}
