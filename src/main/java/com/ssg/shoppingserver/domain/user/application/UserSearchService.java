package com.ssg.shoppingserver.domain.user.application;

import com.ssg.shoppingserver.domain.order.application.OrderService;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import com.ssg.shoppingserver.domain.user.domain.User;
import com.ssg.shoppingserver.domain.user.dto.UserInfoGetResponse;
import com.ssg.shoppingserver.domain.user.dto.UserSearchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserSearchService {

    private final UserService userService;

    private final OrderService orderService;

    private final String MEMBERSHIP_LEVEL_CODE_KEY = "membershipLevelCodes";

    private final String SMILE_CLUB_MEMBERS_KEY = "isSmileClubMembers";

    private final String ORDER_PRODUCTS_IDS_KEY = "orderedProductIds";

    private final String ORDER_STATE_KEY = "orderStates";

    // user 검색
    public List<UserInfoGetResponse> searchUser(UserSearchRequest userSearchRequest) {

        List<User> searchedUser = userService.getUsers().stream()
                .filter(user ->

                        // 멤버십, 스마일 클럽 가입 여부, 주문된 상품 내역 필터링
                        checkByMembershipLevel(user, userSearchRequest)
                                && checkBySmilClubMember(user, userSearchRequest)
                                && checkByOrderedProduct(user, userSearchRequest)
                                && checkByOrderState(user, userSearchRequest)

                ).collect(Collectors.toList());

        // change to dto
        List<UserInfoGetResponse> searchedUsersResponse = searchedUser.stream()
                .map(user -> UserInfoGetResponse.builder()
                        .user(user).build())
                .collect(Collectors.toList());

        return searchedUsersResponse;

    }

    // 멤버 등급으로 검색 체크
    public boolean checkByMembershipLevel(User user, UserSearchRequest userSearchRequest) {

        Long[] membershipLevelCodes = userSearchRequest.getMembershipLevelCodes();

        // ALL 조건일 경우. 그냥 true 리턴
        if (userSearchRequest.getIsAllChecked().get(MEMBERSHIP_LEVEL_CODE_KEY))
            return true;

        // 멤버십 리스트에 하나라도 속한다면, true 반환
        for (long membershipLevelCode : membershipLevelCodes)
            if (user.getMembershipLevel() == MembershipLevel.findByCode(membershipLevelCode))
                return true;

        // 하나도 속하지 않는 다면 false 반환
        return false;
    }

    // 스마일클럽 가입 여부로 검색 체크
    public boolean checkBySmilClubMember(User user, UserSearchRequest userSearchRequest) {

        Boolean[] isSmileClubMembers = userSearchRequest.getIsSmileClubMembers();

        // ALL 조건일 경우. 그냥 true 리턴
        if (userSearchRequest.getIsAllChecked().get(SMILE_CLUB_MEMBERS_KEY))
            return true;

        // 스마일클럽 리스트에 하나라도 속한다면, true 반환
        for (boolean isSmileClubMember : isSmileClubMembers)
            if (user.isSmileClubMember() == isSmileClubMember)
                return true;

        // 하나도 속하지 않는 다면 false 반환
        return false;
    }

    // 주문된 상품 내역으로 검색 체크
    public boolean checkByOrderedProduct(User user, UserSearchRequest userSearchRequest) {

        String[] orderedProductIds = userSearchRequest.getOrderedProductIds();

        // ALL 조건일 경우. 그냥 true 리턴
        if (userSearchRequest.getIsAllChecked().get(ORDER_PRODUCTS_IDS_KEY))
            return true;

        for (String orderedProductId : orderedProductIds) {

            // order list 순회하며 product 주문 내역이 있는지 검사.
            boolean isOrderExist = orderService.getOrders().stream().anyMatch(order ->

                    // order 내역의 userId와 productId 모두 같은지
                    order.getUserId().equals(user.getId()) &&
                            order.getProductId().equals(UUID.fromString(orderedProductId)));

            // order 내역이 하나라도 있다면 true 반환
            if (isOrderExist)
                return true;
        }

        // order 내역 하나도 없다면 false 반환
        return false;
    }

    // 주문 상태로 검색
    public boolean checkByOrderState(User user, UserSearchRequest userSearchRequest) {

        Long[] orderStates = userSearchRequest.getOrderStates();

        // ALL 조건일 경우. 그냥 true 리턴
        if (userSearchRequest.getIsAllChecked().get(ORDER_STATE_KEY))
            return true;

        for (Long orderState : orderStates) {

            boolean isMatchOrderState = orderService.getOrders().stream().anyMatch(order ->

                    // order 내역의 userId와 orderState 모두 같은지
                    order.getUserId().equals(user.getId()) &&
                            order.getOrderState().equals(OrderState.findByCode(orderState)));

            // order 내역이 하나라도 있다면 true 반환
            if (isMatchOrderState)
                return true;
        }

        // order 내역 하나도 없다면 false 반환
        return false;
    }

}
