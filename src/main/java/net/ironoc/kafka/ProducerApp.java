package net.ironoc.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import java.util.Scanner;

public class ProducerApp {

    public static void main(String[] args) {

        String bootstrapServer = args[0];

        // Create Producer Properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter messages to send to Kafka (type 'exit' to quit):");

            while (true) {
                String message = scanner.nextLine();
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                // Create / send a Producer Record
                producer.send(new ProducerRecord<>(args[1], message));

                System.out.println("Sent: " + message);
            }

            // Flush and Close the Producer
            producer.flush();
            producer.close();
        }
    }
}