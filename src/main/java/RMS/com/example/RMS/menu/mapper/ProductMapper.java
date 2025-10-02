package RMS.com.example.RMS.menu.mapper;

import RMS.com.example.RMS.menu.domain.model.ProductEntity;
import RMS.com.example.RMS.menu.web.request.ProductRequest;
import RMS.com.example.RMS.menu.web.response.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toProduct(ProductRequest request) ;
    ProductResponse toProductResponse(ProductEntity product);

}
