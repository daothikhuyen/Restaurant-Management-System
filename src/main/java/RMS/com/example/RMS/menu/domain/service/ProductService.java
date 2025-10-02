package RMS.com.example.RMS.menu.domain.service;

import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.menu.domain.model.ProductEntity;
import RMS.com.example.RMS.menu.domain.repository.ProductRepository;
import RMS.com.example.RMS.menu.mapper.ProductMapper;
import RMS.com.example.RMS.menu.web.request.ProductRequest;
import RMS.com.example.RMS.menu.web.response.ProductResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    public String createProduct(ProductRequest request) {
        if(productRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.DATA_EXISTED);
        }

        try {
            ProductEntity product = productMapper.toProduct(request);
            product.setCreatedDate(LocalDateTime.now());
            product.setUpdatedDate(LocalDateTime.now());
            productRepository.save(product);
            return "Data creation successful";
        }catch (DataAccessException e){
            log.error("Database error when saving product", e);
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public List<ProductResponse> getByCategoryId(Long categoryId) {

        try {
            List<ProductResponse> listProduct;

            List<ProductEntity> products =productRepository.findByCategoryId(categoryId);

            listProduct = products.stream().map(productMapper::toProductResponse).toList();

            return listProduct;
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }

    }

    public ProductResponse getProductById(Long productId) {

        try {

            Optional<ProductEntity> product = productRepository.findById(productId);

            if (product.isPresent()) {
                return productMapper.toProductResponse(product.get());
            } else {
                throw new AppException(ErrorCode.DATA_NOT_FOUND);
            }

        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
