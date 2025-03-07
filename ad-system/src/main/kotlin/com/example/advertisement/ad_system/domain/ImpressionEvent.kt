package com.example.advertisement.ad_system.domain

data class ImpressionEvent (
    val requestId: String,
    val adId: String,
    val adTitle: String,
    val advertiserCost: Double,
    val appId: String,
    val appTitle: String,
    val impressionTime: Long
)