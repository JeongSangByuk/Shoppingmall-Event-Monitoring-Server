package com.ssg.shoppingserver.domain.user.domain;

import ch.qos.logback.core.joran.action.AppenderRefAction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MembershipLevel {

    // SSG.com 회원 등급 체계

    VIP("VIP_MEMBERSHIP", "Vip 회원 등급"),
    GOLD("GOLD_MEMBERSHIP", "Gold 회원 등급"),
    FRIENDS("FRIENDS_MEMBERSHIP", "Friends 회원 등급");

    private final String level;
    private final String description;

}
