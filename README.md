# i2i-Academy-EDAWithApacheKafka-1
# EDA with Apache Kafka

Bu proje, Event-Driven Architecture (EDA) prensiplerini öğrenmek ve 
Apache Kafka ile hands-on deneyim kazanmak amacıyla geliştirilmiştir.

## İçerik
- Docker ile ayağa kaldırılan Apache Kafka broker (KRaft mode)
- Java Producer uygulaması: özel Java nesnelerini (Order) JSON'a 
  serialize ederek Kafka topic'ine gönderir
- Java Consumer uygulaması: topic'i dinler, gelen mesajları deserialize 
  ederek konsola yazdırır
- Cloud VM (Google Cloud) üzerinde deployment

## Teknolojiler
- Apache Kafka 3.7.0
- Java 17
- Maven
- Jackson (JSON serialization)
- Docker / Docker Compose

## Çalıştırma
1. `docker compose up -d` ile Kafka broker'ı başlat
2. `Consumer.java`'yı çalıştır
3. `Producer.java`'yı çalıştır
