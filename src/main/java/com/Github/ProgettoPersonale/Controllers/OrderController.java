package com.Github.ProgettoPersonale.Controllers;


import com.Github.ProgettoPersonale.DTO.OrderDTO;
import com.Github.ProgettoPersonale.Entities.Order;
import com.Github.ProgettoPersonale.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public List<Order> getAllOrder(){
        return orderService.getAllOrders();
    }

    @GetMapping("/clientName")
    public ResponseEntity getOrderByClientNameAndClientSurname(@RequestParam String clientName, @RequestParam String clientSurname) throws Exception{
        try {
            Optional<Order> name = Optional.ofNullable(orderService.getOrderByClientNameAndClientSurname(clientName, clientSurname));
            return ResponseEntity.ok(name);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/clientNumber")
    public ResponseEntity getOrderByClientNumber(@RequestParam String clientNumber) throws Exception {
        try {
            Optional<Order> number = Optional.ofNullable(orderService.getOrderByClientNumber(clientNumber));
            return ResponseEntity.ok(number);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/clientEmail")
    public ResponseEntity getOrderByClientEmail(@RequestParam String clientEmail) throws Exception {
        try {
            Optional<Order> email = Optional.ofNullable(orderService.getOrderByClientEmail(clientEmail));
            return ResponseEntity.ok(email);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity saveOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        try {
            return ResponseEntity.ok(orderService.saveOrder(orderDTO));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteOrderById(@RequestParam long id) throws Exception {
        try {
            return ResponseEntity.ok(orderService.deleteOrderById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable long id, @RequestBody OrderDTO orderDetail ){
        try {
            Order o = orderService.updateOrder(id, orderDetail);
            return ResponseEntity.ok(o);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
