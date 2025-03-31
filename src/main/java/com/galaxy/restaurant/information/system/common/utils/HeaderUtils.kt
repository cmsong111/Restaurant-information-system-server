package com.galaxy.restaurant.information.system.common.utils

import jakarta.servlet.http.HttpServletRequest

object HeaderUtils {
    /** JWT 토큰을 담고 있는 Authorization 헤더의 키 */
    const val AUTHORIZATION_HEADER = "Authorization"
    const val BEARER_PREFIX = "Bearer "

    /**
     * Authorization 헤더에서 JWT 토큰을 추출합니다.
     */
    fun getAccessToken(request: HttpServletRequest): String? {
        val headerValue = request.getHeader(AUTHORIZATION_HEADER) ?: return null
        return if (headerValue.startsWith(BEARER_PREFIX)) {
            headerValue.substring(BEARER_PREFIX.length).trim()
        } else {
            null
        }
    }
}
