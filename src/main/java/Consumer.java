import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) throws Exception {
        // 1. Kafka'ya nasıl bağlanacağımızı ve hangi gruba ait olduğumuzu tanımlıyoruz
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "34.88.251.160:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "siparis-consumer-grubu");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // en baştan okumaya başla

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("siparisler")); // "siparisler" köşesine abone ol

        ObjectMapper mapper = new ObjectMapper();

        System.out.println("Consumer dinlemeye başladı...");

        // 2. Sürekli döngüde yeni mesaj var mı diye kontrol ediyoruz
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> record : records) {
                Order order = mapper.readValue(record.value(), Order.class); // JSON'u tekrar Java objesine çeviriyoruz
                System.out.println("Alındı -> key: " + record.key() + ", obje: " + order);
            }
        }
    }
}