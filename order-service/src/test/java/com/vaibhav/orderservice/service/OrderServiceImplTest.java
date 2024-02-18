package com.vaibhav.orderservice.service;

import com.vaibhav.orderservice.entity.Order;
import com.vaibhav.orderservice.external.client.PaymentService;
import com.vaibhav.orderservice.external.client.ProductService;
import com.vaibhav.orderservice.external.response.PaymentResponse;
import com.vaibhav.orderservice.external.response.ProductResponse;
import com.vaibhav.orderservice.model.OrderResponse;
import com.vaibhav.orderservice.model.PaymentMode;
import com.vaibhav.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceImplTest {


    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    OrderService orderService = new OrderServiceImpl();

    @Test
    @DisplayName("Get Oder - Success Scenario")  // In reports this name will be displayed
    void test_when_order_success(){
        // Mocking
        Order order = getMockOrder();

        when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(order));

        when(restTemplate.getForObject(
                "http://PRODUCT-SERVICE/product/" +  order.getProductId(), ProductResponse.class
        )).thenReturn(getMockProductResponse());

        when(paymentService.getPaymentDetailsByOrderId(anyLong()))
                .thenReturn(ResponseEntity.ok(getMockPaymentResponse()));


        // Actual method call
        OrderResponse orderResponse = orderService.getOrderById(1);

        // Verification
        verify(orderRepository, times(1)).findById(anyLong()); // In order repo we called the findById with any long value 1 time.
        verify(restTemplate, times(1)).getForObject(
                "http://PRODUCT-SERVICE/product/" +  order.getProductId(), ProductResponse.class
        );
        verify(paymentService, times(1)).getPaymentDetailsByOrderId(order.getId());

        // Assert
        assertNotNull(orderResponse);
        assertEquals(order.getId(), orderResponse.getOrderId());

    }
//    @Test
//    @DisplayName("Get Oder - Not Found")  // In reports this name will be displayed
//    void test_when_order_not_found(){
//        when(orderRepository.findById(anyLong()))
//                .thenReturn(Optional.ofNullable(null));
//
//        CustomException exception =
//                    assertThrows(CustomException.class, ()-> orderService.getOrderById(1));
//
//
//        assertEquals("Order not found for orderId = " + 1, exception.getMessage());
//        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
//    }

    private PaymentResponse getMockPaymentResponse() {
        return PaymentResponse
                .builder()
                .orderId(1)
                .paymentId(1)
                .amount(200)
                .paymentDate(Instant.now())
                .paymentMode(PaymentMode.UPI)
                .status("ACCEPTED")
                .build();
    }

    private ProductResponse getMockProductResponse() {
        return ProductResponse
                .builder()
                .productName("Iphone")
                .productId(2)
                .quantity(200)
                .price(100)
                .build();
    }

    private Order getMockOrder() {
        return Order
                .builder()
                .orderStatus("PLACED")
                .orderDate(Instant.now())
                .id(1)
                .amount(100)
                .quantity(200)
                .productId(2)
                .build();

    }

}