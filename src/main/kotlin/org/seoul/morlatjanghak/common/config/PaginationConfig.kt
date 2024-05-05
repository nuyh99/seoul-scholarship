package org.seoul.morlatjanghak.common.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class PaginationConfig : WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        val paginationResolver = PageableHandlerMethodArgumentResolver()
        paginationResolver.setFallbackPageable(PageRequest.of(0, 20))
        resolvers.add(paginationResolver)
    }
}