package com.example.advertisement.ad_system.kafka

import com.example.advertisement.ad_system.domain.AdEvent
import com.example.advertisement.ad_system.domain.ImpressionEvent
import com.example.advertisement.ad_system.domain.ClickEvent
import com.example.advertisement.ad_system.repository.AdEventRepository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class EventConsumer(private val adEventRepository: AdEventRepository) {

    @KafkaListener(topics = ["impression-topic"], groupId = "ad-system-group")
    fun processImpressionEvent(impressionEvent: ImpressionEvent) {
        val adEvent = AdEvent(
            requestId = impressionEvent.requestId,
            adId = impressionEvent.adId,
            adTitle = impressionEvent.adTitle,
            advertiserCost = impressionEvent.advertiserCost,
            appId = impressionEvent.appId,
            appTitle = impressionEvent.appTitle,
            impressionTime = impressionEvent.impressionTime
        )
        adEventRepository.save(adEvent)
    }

    @KafkaListener(topics = ["click-topic"], groupId = "ad-system-group")
    fun processClickEvent(clickEvent: ClickEvent) {
        val adEvent = adEventRepository.findById(clickEvent.requestId)
        if (adEvent.isPresent) {
            val updatedAdEvent = adEvent.get().apply {
                clickTime = clickEvent.clickTime
            }
            adEventRepository.save(updatedAdEvent)
        }
    }
}
