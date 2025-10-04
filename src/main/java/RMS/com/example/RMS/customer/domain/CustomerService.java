package RMS.com.example.RMS.customer.domain;

import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.customer.mapper.CustomerMapper;
import RMS.com.example.RMS.customer.web.CustomerRequest;
import RMS.com.example.RMS.customer.web.CustomerResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CustomerService {

    CustomerMapper customerMapper;
    CustomerRepository customerRepository;

    public List<CustomerResponse> getAll() {
        try {
            List<CustomerEntity> customers = customerRepository.findAll();

            return customers.stream().map(customerMapper::toCustomerResponse).toList();
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public CustomerResponse createCustomer(CustomerRequest request) {
        if(customerRepository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.DATA_EXISTED);
        }

        try {
            CustomerEntity customer = customerMapper.toCustomer(request);
            customer.setCreatedDate(LocalDateTime.now());
            customer.setUpdatedDate(LocalDateTime.now());
            customerRepository.save(customer);

            return customerMapper.toCustomerResponse(customer);
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public CustomerResponse updateCustomer(CustomerRequest request) {
        CustomerEntity customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        try {
            customer.setAddress(request.getAddress());
            customer.setFirstName(request.getFirstName());
            customer.setMiddleName(request.getMiddleName());
            customer.setLastName(request.getLastName());
            customer.setGender(request.getGender());
            customer.setUpdatedBy(request.getCreatedBy());
            customer.setUpdatedDate(LocalDateTime.now());
            customerRepository.save(customer);

            return customerMapper.toCustomerResponse(customer);
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public String deleteCustomer(Long customerId){
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        try {
            customer.setDeleted(true);
            customerRepository.save(customer);

            return "Data deletion successful";
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
