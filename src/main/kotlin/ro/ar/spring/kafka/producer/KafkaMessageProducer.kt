package ro.ar.spring.kafka.producer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaMessageProducer(@Autowired val kafkaTemplate: KafkaTemplate<String, String>) {

    fun sendMessage(message: String, topic: String) {
        val future = kafkaTemplate.send(topic, message)
        future.whenComplete({ result, exception ->
            if (exception != null) {
                println("Error sending message: $message")
            } else {
                println("Message: $message sent with offset: ${result.recordMetadata.offset()}")
            }
        })
    }
}