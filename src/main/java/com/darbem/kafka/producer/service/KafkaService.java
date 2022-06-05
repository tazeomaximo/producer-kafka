package com.darbem.kafka.producer.service;

public interface KafkaService {

	public void send(String message, String topicName);
}
