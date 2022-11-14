package com.ssg.shoppingserver.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class UserSearchRequest {

    private Map<String, Boolean> isAllChecked;

    private Long[] membershipLevelCodes;

    private Boolean[] isSmileClubMembers;

    private String[] orderedProductIds;

    private Long[] orderStates;

    private Long time;

}