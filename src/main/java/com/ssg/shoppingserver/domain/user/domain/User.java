package com.ssg.shoppingserver.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    // User 고유 id
    private UUID id;

    // User email
    private String email;

    // User 이름
    private String name;

    // User 멤버십 레벨
    private MembershipLevel membershipLevel;

    // User SSG Smile Club 회원 여부
    private boolean isSmileClubMember;

    @Builder
    public User(UUID id, String email, String name, MembershipLevel membershipLevel, boolean isSmileClubMember) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.membershipLevel = membershipLevel;
        this.isSmileClubMember = isSmileClubMember;
    }
}
