package com.ze.partner.infrastructure.configuration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.util.ContentCachingRequestWrapper
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ApplicationLogInterceptor : HandlerInterceptor {

    private val logger: Logger = LoggerFactory.getLogger(ApplicationLogInterceptor::class.java)
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val requestCacheWrapperObject: HttpServletRequest = ContentCachingRequestWrapper(request)
        logger.info(
            requestCacheWrapperObject.method + " - " +
                    requestCacheWrapperObject.requestURI
        )
        return true
    }
}