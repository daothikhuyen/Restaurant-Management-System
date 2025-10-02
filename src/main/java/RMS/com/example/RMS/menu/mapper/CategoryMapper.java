package RMS.com.example.RMS.menu.mapper;

import RMS.com.example.RMS.menu.domain.model.CategoryEntity;
import RMS.com.example.RMS.menu.web.request.CategoryRequest;
import RMS.com.example.RMS.menu.web.response.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryEntity toCategory(CategoryRequest request) ;
    CategoryResponse toCategoryResponse(CategoryEntity category);
}
