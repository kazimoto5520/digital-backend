package com.kazimoto.digitalbackend.service.order;

import com.kazimoto.digitalbackend.dto.order.OrderDto;
import com.kazimoto.digitalbackend.dto.order.OrderResponse;
import com.kazimoto.digitalbackend.entity.Order;
import com.kazimoto.digitalbackend.entity.Product;
import com.kazimoto.digitalbackend.entity.User;
import com.kazimoto.digitalbackend.repository.OrderRepository;
import com.kazimoto.digitalbackend.repository.ProductRepository;
import com.kazimoto.digitalbackend.repository.UserRepository;
import com.kazimoto.digitalbackend.utils.JsonResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public JsonResponse saveOrder(OrderDto dto){

        Product product = productRepository.findByRowId(dto.getProductId()).orElseThrow();
        User user = userRepository.findById(Long.valueOf(dto.getUserId())).orElseThrow();

        Order order = new Order();

        order.setOrderName(dto.getOrderName());
        order.setDescription(dto.getDescription());
        order.setTotalAmount(dto.getAmount() * dto.getQuantity());
        order.setQuantity(dto.getQuantity());
        order.setShippingAddress(dto.getShippingAddress());

        String lockNumber = generateLockNumber();
        order.setLockNumber(lockNumber);

        order.setProduct(product);
        order.setUser(user);

         orderRepository.save(order);

         return new JsonResponse("Order created successfully");
    }

    public OrderResponse getSingleOrder(String rowId){
        Order order = orderRepository.findByRowId(rowId)
                .orElseThrow(()-> new NoSuchElementException("No order with id: " + rowId));

        return OrderResponse.builder()
                .rowId(rowId)
                .orderName(order.getOrderName())
                .amount(order.getTotalAmount())
                .description(order.getDescription())
                .quantity(order.getQuantity())
                .shippingAddress(order.getShippingAddress())
                .lockNumber(order.getLockNumber())
                .product(order.getProduct().getProductName())
                .user(order.getUser().getUsername())
                .status(order.getStatus())
                .build();
    }

    public String generateLockNumber(){
        return RandomStringUtils.randomNumeric(12);
    }
}
