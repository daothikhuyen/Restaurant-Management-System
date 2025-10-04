package RMS.com.example.RMS.billing.web;

import RMS.com.example.RMS.billing.domain.service.BillingService;
import RMS.com.example.RMS.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/billing")
@CrossOrigin
@Slf4j
public class BillingController {

    @Autowired
    BillingService service;

    @PostMapping("/{billingId}/status")
    public ApiResponse<BillingResponse> updateStatusBilling(@PathVariable Long billingId,@RequestParam String status){
        return ApiResponse.<BillingResponse>builder()
                .result(service.updateStatusBilling(billingId, status))
                .build();
    }

    @PostMapping("/delete/{billingId}")
    public ApiResponse<BillingResponse> deleteBilling(@PathVariable Long billingId){
        return ApiResponse.<BillingResponse>builder()
                .message(service.deleteBilling(billingId))
                .build();
    }
}
