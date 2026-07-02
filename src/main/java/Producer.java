import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {
    public static void main(String[] args) throws Exception {
        // 1. Kafka'ya nasıl bağlanacağımızı tanımlıyoruz
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "34.88.251.160:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 2. Producer nesnesini oluşturuyoruz
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ObjectMapper mapper = new ObjectMapper();

        // 3. Örnek 5 sipariş oluşturup gönderelim
        for (int i = 1; i <= 5; i++) {
            Order order = new Order("ORD-" + i, "Laptop", i);
            String jsonMessage = mapper.writeValueAsString(order); // Java objesini JSON'a çeviriyoruz

            ProducerRecord<String, String> record =
                    new ProducerRecord<>("siparisler", order.getOrderId(), jsonMessage);

            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.println("Gönderildi -> partition: " + metadata.partition()
                            + ", offset: " + metadata.offset());
                } else {
                    exception.printStackTrace();
                }
            });

            Thread.sleep(500); // Sadece izlemesi kolay olsun diye biraz bekliyoruz
        }

        producer.flush(); // Bellekteki mesajların gerçekten gönderildiğinden emin oluyoruz
        producer.close();
        System.out.println("Tüm siparişler gönderildi.");
    }
}