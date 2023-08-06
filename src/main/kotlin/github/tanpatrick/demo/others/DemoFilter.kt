package github.tanpatrick.demo.others

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.lang3.NotImplementedException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class DemoFilter : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        if(request.requestURI.contains("throw-error")) {
            throw NotImplementedException("Not yet implemented")
        }

        filterChain.doFilter(request, response)
    }
}