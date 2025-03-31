package com.galaxy.restaurant.information.system.auth.filter

import com.galaxy.restaurant.information.system.auth.JwtTokenProvider
import com.galaxy.restaurant.information.system.common.utils.HeaderUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtTokenFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        HeaderUtils.getAccessToken(request)?.let { token ->
            try {
                SecurityContextHolder.getContext().authentication = jwtTokenProvider.getAuthentication(token)
            } catch (e: Exception) {
                SecurityContextHolder.clearContext()
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token")
                return
            }
        }
        filterChain.doFilter(request, response)
    }
}
