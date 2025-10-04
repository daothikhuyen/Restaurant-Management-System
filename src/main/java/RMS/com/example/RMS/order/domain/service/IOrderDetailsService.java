package RMS.com.example.RMS.order.domain.service;

import RMS.com.example.RMS.order.web.request.OrderDetailRequest;
import RMS.com.example.RMS.order.web.response.OrderDetailResponse;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public interface IOrderDetailsService {

    List<OrderDetailResponse> createOrderDetail(@NotEmpty List<OrderDetailRequest> orderDetails, Long orderId);

    List<OrderDetailResponse> updateOrderDetail(@NotEmpty List<OrderDetailRequest> orderDetails, Long orderId);
}
