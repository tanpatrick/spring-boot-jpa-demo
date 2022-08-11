package github.tanpatrick.demo.others

import org.apache.commons.lang3.NotImplementedException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class DemoFilter : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        if(request.requestURI.contains("throw-error")) {
            throw NotImplementedException("Not yet implemented")
        }

        filterChain.doFilter(request, response)
    }
}