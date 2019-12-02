package harsh.rane.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import harsh.rane.model.TargetTable;


@EnableKafka
@Configuration
public class KafkaConfig {

	@Bean
	public ConsumerFactory<String, TargetTable> consumerFactory() {
		Map<String, Object> config = new HashMap<String, Object>();
		
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"b-2.cfkafkapoc.nj0tf5.c7.kafka.us-east-1.amazonaws.com:9094,b-1.cfkafkapoc.nj0tf5.c7.kafka.us-east-1.amazonaws.com:9094");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "StreamApp");
		
		config.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");	
		config.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "/tmp/kafka.client.truststore.jks");
		config.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "changeit");
		
//		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		return new DefaultKafkaConsumerFactory<String, TargetTable>(config, new StringDeserializer(),
				new JsonDeserializer<TargetTable>(TargetTable.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TargetTable> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, TargetTable> factory = new ConcurrentKafkaListenerContainerFactory<String, TargetTable>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
}
