package RMS.com.example.RMS.table_management.domain.mapper;

import RMS.com.example.RMS.table_management.domain.model.OrderEntity;
import RMS.com.example.RMS.table_management.web.request.OrderRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity toOrder(OrderRequest request);
}
