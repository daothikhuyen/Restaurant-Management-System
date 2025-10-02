package RMS.com.example.RMS.table_management.domain.service;

import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.table_management.domain.mapper.OrderMapper;
import RMS.com.example.RMS.table_management.domain.model.OrderEntity;
import RMS.com.example.RMS.table_management.domain.repository.OrderRepository;
import RMS.com.example.RMS.table_management.web.request.OrderRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderService {

    OrderMapper orderMapper;
    OrderRepository orderRepository;

    public String createOrder(OrderRequest request) {
        if(orderRepository.existsByTableId(request.getTableId())){
            throw new AppException(ErrorCode.DATA_EXISTED);
        }

        try {
            OrderEntity order = orderMapper.toOrder(request);
            order.setCreatedDate(LocalDateTime.now());
            order.setUpdatedDate(LocalDateTime.now());
            orderRepository.save(order);

            return "Data saved successfully";
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
