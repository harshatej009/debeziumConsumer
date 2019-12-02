package harsh.rane.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import harsh.rane.dao.TargetTableDao;
import harsh.rane.model.TargetTable;

@Service
public class ConsumerListener {
	 
	 @Autowired
	 TargetTableDao targettabledao;
	 
	 private static final Logger LOGGER = LogManager.getLogger(ConsumerListener.class);
	 
	@KafkaListener(topics = "connect-neel", group = "StreamApp", containerFactory="kafkaListenerContainerFactory")
	public void consumeJson(TargetTable targettable)
	{
		LOGGER.info("Read the data");	
		LOGGER.info(targettable);
		targettabledao.UpdateTargetDetails(targettable.getCfid(),targettable.getCfname());
		
	}
}
