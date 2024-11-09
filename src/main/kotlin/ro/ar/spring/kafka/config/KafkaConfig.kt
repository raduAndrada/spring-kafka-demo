package ro.ar.spring.kafka.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaConfig(@Value("\${spring.kafka.bootstrap-servers}") val bootstrapServers: String,
    @Value("\${spring.kafka.topic}") val topic: String) {

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        return KafkaAdmin(mapOf("bootstrap.servers" to bootstrapServers))
    }

    @Bean
    fun topic1(): NewTopic {
        return NewTopic(topic, 1, 1)
    }
}