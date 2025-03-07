package com.example.advertisement.ad_system

import com.example.advertisement.ad_system.domain.ImpressionEvent
import com.example.advertisement.ad_system.domain.ClickEvent
import com.example.advertisement.ad_system.kafka.EventProducer
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class AdSystemApplication(val eventProducer: EventProducer) {

	@Bean
	fun commandLineRunner() = ApplicationRunner {
		// Create a sample ImpressionEvent
		val impressionEvent = ImpressionEvent(
			requestId = UUID.randomUUID().toString(),
			adId = "ad1",
			adTitle = "Sample Ad",
			advertiserCost = 10.5,
			appId = "app1",
			appTitle = "Sample App",
			impressionTime = System.currentTimeMillis()
		)
		eventProducer.sendImpressionEvent(impressionEvent)

		Thread.sleep(1000)

		val clickEvent = ClickEvent(
			requestId = impressionEvent.requestId,
			clickTime = System.currentTimeMillis()
		)
		eventProducer.sendClickEvent(clickEvent)
	}
}

fun main(args: Array<String>) {
	runApplication<AdSystemApplication>(*args)
}
