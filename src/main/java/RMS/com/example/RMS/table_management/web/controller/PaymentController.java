package RMS.com.example.RMS.table_management.web.controller;

import RMS.com.example.RMS.common.ApiResponse;
import RMS.com.example.RMS.table_management.domain.service.PaymentService;
import RMS.com.example.RMS.table_management.web.request.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/payment")
@CrossOrigin
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService service;

    @PostMapping("/create")
    public ApiResponse<String> createPayment(@RequestBody PaymentRequest request){

        return ApiResponse.<String>builder()
                .message(service.createPayment(request))
                .build();
    }
}
