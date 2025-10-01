package RMS.com.example.RMS.customer.domain.service;

import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.customer.domain.mapper.CustomerMapper;
import RMS.com.example.RMS.customer.domain.model.CustomerEntity;
import RMS.com.example.RMS.customer.domain.repository.CustomerRepository;
import RMS.com.example.RMS.customer.domain.mapper.AuthMapper;
import RMS.com.example.RMS.customer.domain.repository.AuthRepository;
import RMS.com.example.RMS.customer.web.request.CustomerRequest;
import RMS.com.example.RMS.customer.web.response.CustomerResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CustomerService {

    PasswordEncoder passwordEncoder;
    AuthMapper userMapper;
    CustomerMapper customerMapper;
    CustomerRepository customerRepository;
    AuthRepository userRepository;


    public CustomerResponse updateProfile(CustomerRequest request) {
        try {
            CustomerEntity customer = customerMapper.toCustomer(request);
            customer.setUpdatedDate(LocalDateTime.now());
            customerRepository.save(customer);

            return customerMapper.toCustomerResponse(customer);
        }catch (Exception e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }
    }
}
