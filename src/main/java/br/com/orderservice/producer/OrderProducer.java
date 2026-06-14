package br.com.orderservice.producer;

import br.com.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderEvent(Order order) {
        String messageKey = order.getId().toString();

        kafkaTemplate.send("pedidos-criados", messageKey, order);
    }
}