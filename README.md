# **Ad System Service**

This project is a distributed event-driven system for managing and processing ad events, utilizing **Spring Boot**, **Apache Kafka**, and **Apache Cassandra**. It efficiently handles ad events, such as impressions, in a scalable and fault-tolerant manner.

## **Features**
- **Real-time Event Processing**: Event data is ingested and processed via **Apache Kafka** topics.
- **Highly Scalable NoSQL Storage**: Ad event information is stored in **Apache Cassandra** for fault tolerance and high availability.
- **Spring Boot Microservice**: A lightweight backend application managing Kafka consumers and Cassandra data storage.

---

## **System Overview**
This application is designed to handle a high volume of ad events distributed across multiple systems in real time. The key components are:

### **Kafka Integration**
- Processes ad events via the Kafka topic `ad-events`.
- Uses a Kafka consumer (`@KafkaListener`) to process incoming messages asynchronously.

### **Cassandra Database**
- Stores ad events in a fault-tolerant and distributed way.
- The data schema includes fields such as:
  - `requestId`: Unique identifier for each event.
  - `adId`, `adTitle`: Information about the advertisement.
  - `appId`, `appTitle`: Information about the application where the ad appeared.
  - `impressionTime`: Timestamp of the ad impression.
  - `advertiserCost`: Cost associated with the advertisement.

### **Spring Boot Microservice**
- Provides configuration for both Kafka and Cassandra.
- Uses `spring-data-cassandra` to manage database repositories.
- Automatically creates tables in Cassandra during runtime if the schema does not exist.

---

## **Project Structure**
### **Main Components:**

#### 1. `AdEvent` Entity
Defines the Cassandra table schema for storing ad events. Example fields:
```java
@Table("ad_event")
public class AdEvent {
    @PrimaryKey private String requestId;
    private String adId;
    private String adTitle;
    private double advertiserCost;
    private String appId;
    private String appTitle;
    private long impressionTime;
}
