package ro.ar.spring.kafka

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestPropertySource
import ro.ar.spring.kafka.consumer.KafkaConsumer
import ro.ar.spring.kafka.producer.KafkaMessageProducer

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = [ "listeners=PLAINTEXT://localhost:9092", "port=9092" ], topics = ["\${spring.kafka.topic}" ])
@TestPropertySource("classpath:application-test.yml")
class SpringKafkaApplicationTests(
    @Autowired val kafkaConsumer: KafkaConsumer,
    @Autowired val kafkaMessageProducer: KafkaMessageProducer,
    @Value("\${spring.kafka.topic}") val topic: String
) {

    @Test
    fun testSendMessage() {
        kafkaMessageProducer.sendMessage("Hello Kafka!", topic)
        Thread.sleep(1000)
        assert(kafkaConsumer.messages.size == 1)
    }

}
