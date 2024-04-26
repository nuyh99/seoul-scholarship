package org.seoul.morlatjanghak.worker.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
@EnableAsync
class AsyncConfig {

    @Bean
    fun asyncExecutor(): ThreadPoolTaskExecutor = ThreadPoolTaskExecutor().apply {
        corePoolSize = 5
        maxPoolSize = 5
        queueCapacity = 500
        setThreadNamePrefix("worker-")
        initialize()
    }
}
