package RMS.com.example.RMS.order.domain.service;

import RMS.com.example.RMS.billing.domain.service.BillingService;
import RMS.com.example.RMS.billing.domain.service.IBillingService;
import RMS.com.example.RMS.billing.mapper.BillingMapper;
import RMS.com.example.RMS.billing.web.BillingResponse;
import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.order.domain.model.OrderDetailEntity;
import RMS.com.example.RMS.order.domain.repository.OrderDetailRepository;
import RMS.com.example.RMS.order.mapper.OrderDetailMapper;
import RMS.com.example.RMS.order.mapper.OrderMapper;
import RMS.com.example.RMS.order.domain.model.OrderEntity;
import RMS.com.example.RMS.order.domain.repository.OrderRepository;
import RMS.com.example.RMS.order.web.request.OrderRequest;
import RMS.com.example.RMS.order.web.response.OrderResponse;
import RMS.com.example.RMS.order.web.response.OrderTransactionResponse;
import jakarta.validation.constraints.NotEmpty;
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
public class OrderService {

    OrderMapper orderMapper;
    OrderRepository orderRepository;
    IBillingService iBillingService;
    IOrderDetailsService iOrderDetailsService;

    public OrderTransactionResponse createOrder(OrderRequest request) {
        if(orderRepository.existsByTableId(request.getTableId())){
            throw new AppException(ErrorCode.DATA_EXISTED);
        }

        try {
            OrderTransactionResponse response = new OrderTransactionResponse();
            OrderEntity order = orderMapper.toOrder(request);
            order.setCreatedDate(LocalDateTime.now());
            order.setUpdatedDate(LocalDateTime.now());
            orderRepository.save(order);
            response.setOrderResponse(orderMapper.toOrderResponse(order));

            response.setOrderDetails(iOrderDetailsService.createOrderDetail(request.getOrderDetails(), order.getId()));
            BillingResponse billingResponse = iBillingService.createBillingFromOrder(order.getId(), request.getTableId(), request.getUserId(), request.getTotalAmount(), request.getNotes());
            response.setBilling(billingResponse);

            return response;
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public OrderTransactionResponse updateOrder(OrderRequest request, Long orderId){
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.DATA_NOT_FOUND));
        try {
            OrderTransactionResponse response = new OrderTransactionResponse();
            order.setOrderType(request.getOrderType());
            order.setTableId(request.getTableId());
            order.setTotalAmount(request.getTotalAmount());
            order.setUserId(request.getUserId());
            order.setUpdatedBy(request.getUpdatedBy());
            order.setUpdatedDate(order.getUpdatedDate());
            orderRepository.save(order);
            response.setOrderResponse(orderMapper.toOrderResponse(order));

            response.setOrderDetails(iOrderDetailsService.updateOrderDetail(request.getOrderDetails(),order.getId()));
            BillingResponse billing= iBillingService.updateBilling(order.getId(), request.getTableId(), request.getUserId(), request.getTotalAmount(), request.getNotes());
            response.setBilling(billing);

            return response;
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public String deleteOrder(Long orderId){
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.DATA_NOT_FOUND));

        try {
            order.setDeleted(true);
            orderRepository.save(order);

            return "Data deletion successful";
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
