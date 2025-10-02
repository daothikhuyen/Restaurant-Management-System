package RMS.com.example.RMS.table_management.web.controller;

import RMS.com.example.RMS.common.ApiResponse;
import RMS.com.example.RMS.table_management.domain.service.OrderService;
import RMS.com.example.RMS.table_management.web.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@CrossOrigin
@Slf4j
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping("/create")
    public ApiResponse<String> createOrder(@RequestBody OrderRequest request){
        System.out.println("Hi" + request);
        return ApiResponse.<String>builder()
                .message(service.createOrder(request))
                .build();
    }
}
