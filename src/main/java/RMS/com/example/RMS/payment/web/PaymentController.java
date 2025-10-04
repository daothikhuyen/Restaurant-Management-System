package RMS.com.example.RMS.payment.web;

import RMS.com.example.RMS.common.ApiResponse;
import RMS.com.example.RMS.payment.domain.PaymentService;
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

    @PostMapping("/update/{paymentId}")
    public ApiResponse<PaymentResponse> updatePayment(@PathVariable Long paymentId, @RequestBody PaymentRequest request){

        return ApiResponse.<PaymentResponse>builder()
                .result(service.updatePayment(paymentId, request))
                .build();
    }

    @PostMapping("/delete/{paymentId}")
    public ApiResponse<String> deletePayment(@PathVariable Long paymentId){

        return ApiResponse.<String>builder()
                .message(service.deletePayment(paymentId))
                .build();
    }

}
