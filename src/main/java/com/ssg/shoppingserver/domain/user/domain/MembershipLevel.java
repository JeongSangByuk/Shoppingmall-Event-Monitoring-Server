package com.ssg.shoppingserver.domain.user.domain;

import ch.qos.logback.core.joran.action.AppenderRefAction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MembershipLevel {

    // SSG.com 회원 등급 체계 => [ VIP, GOLD, FRIENDS ]

    VIP("VIP_MEMBERSHIP",  10),
    GOLD("GOLD_MEMBERSHIP",  7),
    FRIENDS("FRIENDS_MEMBERSHIP",  5);

    private final String level;
    private final int discountRate;

}
