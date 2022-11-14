package com.ssg.shoppingserver.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import com.ssg.shoppingserver.domain.user.domain.User;
import com.ssg.shoppingserver.global.common.BaseLocalDateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserInfoGetResponse {

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

    // User 생성 시간
    private String createdAt;

    @Builder
    public UserInfoGetResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.membershipLevel = user.getMembershipLevel();
        this.isSmileClubMember = user.isSmileClubMember();
        this.createdAt = user.getCreatedAt().format(BaseLocalDateTimeFormatter.getLocalTimeFormatter());
    }
}
