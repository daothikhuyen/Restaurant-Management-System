package RMS.com.example.RMS.menu.domain.service;

import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.menu.domain.model.CategoryEntity;
import RMS.com.example.RMS.menu.domain.repository.CategoryRepository;
import RMS.com.example.RMS.menu.mapper.CategoryMapper;
import RMS.com.example.RMS.menu.web.request.CategoryRequest;
import RMS.com.example.RMS.menu.web.response.CategoryResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public String createCategory(CategoryRequest request) {
        if(categoryRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.DATA_EXISTED);
        }

        try {
            CategoryEntity category = categoryMapper.toCategory(request);
            categoryRepository.save(category);
            return "Data creation successful";
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public List<CategoryResponse> getAll() {
        try {
            List<CategoryResponse> listCategory;

            List<CategoryEntity> categories =categoryRepository.findAll();

            listCategory = categories.stream().map(categoryMapper::toCategoryResponse).toList();

            return listCategory;
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        try {
            category.setName(request.getName());
            category.setIconUrl(request.getIconUrl());
            categoryRepository.save(category);

            return categoryMapper.toCategoryResponse(category);
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public String deleteCategory(Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.DATA_NOT_FOUND));

        try {
            category.setDeleted(true);
            categoryRepository.save(category);

            return "Data deletion successful";
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
