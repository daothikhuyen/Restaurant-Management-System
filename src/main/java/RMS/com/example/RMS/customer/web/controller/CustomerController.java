package RMS.com.example.RMS.customer.web.controller;

import RMS.com.example.RMS.common.ApiResponse;
import RMS.com.example.RMS.customer.domain.service.CustomerService;
import RMS.com.example.RMS.customer.web.request.CustomerRequest;
import RMS.com.example.RMS.customer.web.response.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping("/profile")
    public ApiResponse<CustomerResponse> updateProfile(@RequestBody CustomerRequest request) throws Exception {
        ApiResponse<CustomerResponse> response = null;

        response = ApiResponse.<CustomerResponse>builder()
                .result(service.updateProfile(request))
                .build();
        return  response;
    }
}
