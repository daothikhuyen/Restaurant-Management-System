package RMS.com.example.RMS.order.mapper;

import RMS.com.example.RMS.order.domain.model.OrderEntity;
import RMS.com.example.RMS.order.web.request.OrderRequest;
import RMS.com.example.RMS.order.web.response.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity toOrder(OrderRequest request);

    OrderResponse toOrderResponse(OrderEntity order);
}
