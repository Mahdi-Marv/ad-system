package com.example.advertisement.ad_system.repository

import com.example.advertisement.ad_system.domain.AdEvent
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface AdEventRepository : CassandraRepository<AdEvent, String>
