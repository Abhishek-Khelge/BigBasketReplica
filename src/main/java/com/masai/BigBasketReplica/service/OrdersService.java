package com.masai.BigBasketReplica.service;

import com.masai.BigBasketReplica.entity.Orders;
import com.masai.BigBasketReplica.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class OrdersService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;


    public GenericDto getOrderDetails(Integer userId)
    {
        GenericDto orders = new GenericDto();
        orders.setData(orderRepository.findOrdersByUsers(userRepository.findById(userId).get()));
        orders.setMessage("Orders fetch completed");
        return orders;
    }

    public GenericDto confirmOrder(Users users, Address address,Float totalPrice)
    {
        Orders orders = new Orders();
        orders.setUsers(users);
        orders.setPlacedDate(LocalDate.now());
        orders.setPlacedTime(LocalTime.now());
        orders.setStatus("Incomplete");
        orders.setDeliveredTime(orders.getPlacedTime().plusHours(3));
        orders.setModeOfPay("Cash on Delivery");
        orders.setPaymentStatus("Incomplete");
        orders.setAddress(address);
        orders.setTotalPrice(totalPrice);
        GenericDto genericDto = new GenericDto();
        genericDto.setData(orderRepository.save(orders));
        return genericDto;

    }
}
