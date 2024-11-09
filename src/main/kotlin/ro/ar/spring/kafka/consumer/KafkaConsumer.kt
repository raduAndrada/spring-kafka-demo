package ro.ar.spring.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


@Service
class KafkaConsumer {

    val messages = mutableListOf<ConsumerRecord<String, String>>()

    @KafkaListener(topics = ["\${spring.kafka.topic}"])
    fun listen(consumerRecord: ConsumerRecord<String, String>) {
        println("Received Message in group foo: $consumerRecord.value()")
        messages.add(consumerRecord)
    }
}