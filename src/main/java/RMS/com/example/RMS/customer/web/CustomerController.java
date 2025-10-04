package RMS.com.example.RMS.customer.web;

import RMS.com.example.RMS.common.ApiResponse;
import RMS.com.example.RMS.customer.domain.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("getAll")
    public ApiResponse<List<CustomerResponse>> getAllCustomer() throws Exception {
        ApiResponse<List<CustomerResponse>> response = null;

        response = ApiResponse.<List<CustomerResponse>>builder()
                .result(service.getAll())
                .build();
        return  response;
    }

    @PostMapping("create")
    public ApiResponse<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) throws Exception {
        ApiResponse<CustomerResponse> response = null;

        response = ApiResponse.<CustomerResponse>builder()
                .result(service.createCustomer(request))
                .build();
        return  response;
    }

    @PostMapping("update")
    public ApiResponse<CustomerResponse> updateCustomer( @RequestBody CustomerRequest request) throws Exception {
        ApiResponse<CustomerResponse> response = null;

        response = ApiResponse.<CustomerResponse>builder()
                .result(service.updateCustomer(request))
                .build();
        return  response;
    }

    @PostMapping("/delete/{customerId}")
    public ApiResponse<CustomerResponse> deleteCustomer(@PathVariable Long customerId) throws Exception {
        ApiResponse<CustomerResponse> response = null;
        System.out.println("hi " + customerId);
        response = ApiResponse.<CustomerResponse>builder()
                .message(service.deleteCustomer(customerId))
                .build();
        return  response;
    }
}
