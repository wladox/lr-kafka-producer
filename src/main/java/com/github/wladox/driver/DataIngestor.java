package com.github.wladox.driver;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by wladox on 27.11.16.
 */
public class DataIngestor {

    public static void main(String[] args) {

        if (args.length != 2){
            System.out.println("Usage: <inputFilePath> <topicName>");
            System.exit(1);
        }
        final String inputPath = args[0];
        final String topicName = args[1];

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 0);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        final Producer<String, String> producer = new KafkaProducer<>(props);
        DataDriver driver = new DataDriver();
        DataDriverLibrary.TupleReceivedCallback callback = new DataDriverLibrary.TupleReceivedCallback() {

            public void invoke(String tuple) {
                String[] array = tuple.split(",");
                producer.send(new ProducerRecord<>(topicName, array[0], tuple));
            }
        };

        driver.getLibrary().startProgram(inputPath, callback);

    }

}
