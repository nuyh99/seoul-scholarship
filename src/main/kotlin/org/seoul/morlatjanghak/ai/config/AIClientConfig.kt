package org.seoul.morlatjanghak.ai.config

import org.seoul.morlatjanghak.ai.client.AIClient
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class AIClientConfig {

    @Bean
    fun aiClient(): AIClient {
        val restClient = RestClient.builder()
            .baseUrl("https://api.upstage.ai/v1/solar/chat/completions")
            .defaultHeaders { headers ->
                headers.setBearerAuth(API_TOKEN)
                headers.contentType = MediaType.APPLICATION_JSON
            }
            .requestInterceptor { request, body, execution ->
                log.info("Request: ${String(body)}")
                execution.execute(request, body)
            }
            .build()
        val restClientAdapter = RestClientAdapter.create(restClient)

        return HttpServiceProxyFactory.builderFor(restClientAdapter)
            .build()
            .createClient(AIClient::class.java)
    }

    companion object {
        private const val API_TOKEN = "zwQIZh75qKnjMjWih8dIDcBGFwA3BWs0"
        private val log = LoggerFactory.getLogger(AIClientConfig::class.java)
    }
}
