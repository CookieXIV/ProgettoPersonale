package com.Github.ProgettoPersonale.Services;

import com.Github.ProgettoPersonale.DTO.OrderDTO;
import com.Github.ProgettoPersonale.Entities.Order;
import com.Github.ProgettoPersonale.Entities.Product;
import com.Github.ProgettoPersonale.Repositories.OrderRepo;
import com.Github.ProgettoPersonale.Repositories.ProductRepo;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductService productService;

    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }

    public Order getOrderByClientNameAndClientSurname(String clientName, String clientSurname) throws Exception {
        Optional<Order> optionalOrder = orderRepo.findOrderByClientNameAndClientSurname(clientName, clientSurname);
        if (optionalOrder.isPresent()){
            return optionalOrder.get();
        }else throw new Exception("Order with Client Name :  " + clientName + " " +clientSurname+ " does not exist");
    }

    public Order getOrderByClientNumber(String clientNumber) throws Exception {
        Optional<Order> optionalOrder = orderRepo.findOrderByClientNumber(clientNumber);
        if (optionalOrder.isPresent()){
            return optionalOrder.get();
        }else throw new Exception("Order with Client cell number :  " +clientNumber+ " does not exist");
    }

    public Order getOrderByClientEmail(String clientEmail) throws Exception {
        Optional<Order> optionalOrder = orderRepo.findOrderByClientEmail(clientEmail);
        if (optionalOrder.isPresent()){
            return optionalOrder.get();
        }else throw new Exception("Order with Client Email :  " + clientEmail+ " does not exist");
    }

    public Order saveOrder(OrderDTO orderDTO) throws Exception {
        try {
            Order order = new Order(
                    orderDTO.getClientName(),
                    orderDTO.getClientSurname(),
                    orderDTO.getClientEmail(),
                    orderDTO.getClientNumber());
            order.setProducts(new ArrayList<>());

            for (long id : orderDTO.getProductIds()) {
                Optional<Product> optional = productRepo.findById(id);
                if (optional.isPresent()) {
                    order.setId(id);
                }else {
                    throw new Exception("Cannot find book with id " + id);
                }
            }
            order = orderRepo.save(order);
            return order;

        } catch (Exception e) {
            throw new Exception("Couldn't create order. Check if everything is all right!");
        }
    }

    public String deleteOrderById(long id) throws Exception{
        try{
            orderRepo.deleteById(id);
            return "The order with ID: " +id+ " has been deleted successfully";
        }catch (Exception e) {
            throw new Exception("Can't find any order with ID: " + id);
        }
    }

    public Order updateOrder( long id, OrderDTO orderDetails) throws Exception {
        try {
            Order updateOrder = orderRepo.findById(id).get();

            updateOrder.setClientName(orderDetails.getClientName());
            updateOrder.setClientSurname(orderDetails.getClientSurname());
            updateOrder.setClientEmail(orderDetails.getClientEmail());
            updateOrder.setClientNumber(orderDetails.getClientNumber());
            updateOrder.setProducts(new ArrayList<>());
            for (Long bookId : orderDetails.getProductIds()) {
                updateOrder.getProducts().add(productRepo.findById(bookId).get());
            }

            orderRepo.save(updateOrder);
            return updateOrder;
        } catch (Exception e) {
            throw new Exception("Cannot find Order with ID : "+id);
        }
    }
}