package com.ssg.shoppingserver.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class UserSearchRequest {

    private Map<String, Boolean> isAllChecked;

    private Long[] membershipLevelCodes;

    private boolean[] isSmileClubMembers;

    private String[] orderedProductIds;

}
