package RMS.com.example.RMS.order.web;

import RMS.com.example.RMS.billing.web.BillingResponse;
import RMS.com.example.RMS.common.ApiResponse;
import RMS.com.example.RMS.order.domain.service.OrderService;
import RMS.com.example.RMS.order.web.request.OrderRequest;
import RMS.com.example.RMS.order.web.response.OrderTransactionResponse;
import jakarta.validation.Valid;
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
    public ApiResponse<OrderTransactionResponse> createOrder(@Valid @RequestBody OrderRequest request){
        return ApiResponse.<OrderTransactionResponse>builder()
                .result(service.createOrder(request))
                .build();
    }

    @PostMapping("/update/{orderId}")
    public ApiResponse<OrderTransactionResponse> updateOrder(@PathVariable Long orderId, @Valid @RequestBody OrderRequest request){
        return ApiResponse.<OrderTransactionResponse>builder()
                .result(service.updateOrder(request, orderId))
                .build();
    }

    @PostMapping("/delete/{orderId}")
    public ApiResponse<OrderTransactionResponse> deleteOrder(@PathVariable Long orderId){
        return ApiResponse.<OrderTransactionResponse>builder()
                .message(service.deleteOrder(orderId))
                .build();
    }
}
