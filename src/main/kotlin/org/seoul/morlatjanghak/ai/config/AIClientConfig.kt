package org.seoul.morlatjanghak.ai.config

import org.seoul.morlatjanghak.ai.client.AIClient
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class AIClientConfig {

    @Bean
    fun aiClient(): AIClient {
        val restClient = RestClient.builder()
            .baseUrl("http://localhost:5000/api")
            .build()
        val restClientAdapter = RestClientAdapter.create(restClient)

        return HttpServiceProxyFactory.builderFor(restClientAdapter)
            .build()
            .createClient(AIClient::class.java)
    }
}
