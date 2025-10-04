package RMS.com.example.RMS.order.mapper;

import RMS.com.example.RMS.order.domain.model.OrderDetailEntity;
import RMS.com.example.RMS.order.web.request.OrderDetailRequest;
import RMS.com.example.RMS.order.web.response.OrderDetailResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailEntity toOrderDetail(OrderDetailRequest request);

    List<OrderDetailResponse> toOrderDetailResponse(List<OrderDetailEntity> details);
}
