package RMS.com.example.RMS.order.domain.service;

import RMS.com.example.RMS.order.domain.model.OrderDetailEntity;
import RMS.com.example.RMS.order.domain.repository.OrderDetailRepository;
import RMS.com.example.RMS.order.mapper.OrderDetailMapper;
import RMS.com.example.RMS.order.web.request.OrderDetailRequest;
import RMS.com.example.RMS.order.web.response.OrderDetailResponse;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderDetailService implements IOrderDetailsService {

    OrderDetailMapper orderDetailMapper;
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetailResponse> createOrderDetail(@NotEmpty List<OrderDetailRequest> orderDetails, Long orderId) {
        List<OrderDetailEntity> details = orderDetails.stream().map(item -> {
            OrderDetailEntity orderDetail = orderDetailMapper.toOrderDetail(item);
            orderDetail.setOrderId(orderId);
            return orderDetail;
        }).toList();

        orderDetailRepository.saveAll(details);

        return orderDetailMapper.toOrderDetailResponse(details);
    }

    @Override
    public List<OrderDetailResponse> updateOrderDetail(@NotEmpty List<OrderDetailRequest> orderDetails, Long orderId) {
        List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findAllByOrderId(orderId);
        orderDetailRepository.deleteAll(orderDetailEntities);
        return createOrderDetail(orderDetails, orderId);
    }
}
