package com.galaxy.restaurant.information.system.restaurant.entity

enum class RestaurantType(
    val displayName: String
) {
    JOKBAL_BOSSAM("족발, 보쌈"),
    JJIM_TANG("찜, 탕"),
    DONKATSU_SUSHI("돈까스, 회 (일식)"),
    PIZZA("피자"),
    CHICKEN("치킨"),
    CHINESE("중식"),
    KOREAN("한식"),
    BUNSIK("분식"),
    CAFE_DESSERT("카페, 디저트"),
    FASTFOOD("패스트푸드"),
    ;
}
