package com.ssg.shoppingserver.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Getter
@NoArgsConstructor
public class UserSearchRequest {

    @NotNull
    private Map<String, Boolean> isAllChecked;

    @NotNull
    private Long[] membershipLevelCodes;

    @NotNull
    private Boolean[] isSmileClubMembers;

    @NotNull
    private String[] orderedProductIds;

    @NotNull
    private Long[] orderStates;

    @NotNull
    private Long time;

}
