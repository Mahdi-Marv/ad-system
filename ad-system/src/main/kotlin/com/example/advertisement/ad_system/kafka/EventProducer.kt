package com.example.advertisement.ad_system.kafka

import com.example.advertisement.ad_system.domain.ImpressionEvent
import com.example.advertisement.ad_system.domain.ClickEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class EventProducer(private val kafkaTemplate: KafkaTemplate<String, Any>) {

    fun sendImpressionEvent(impressionEvent: ImpressionEvent) {
        kafkaTemplate.send("impression-topic", impressionEvent)
    }

    fun sendClickEvent(clickEvent: ClickEvent) {
        kafkaTemplate.send("click-topic", clickEvent)
    }
}
