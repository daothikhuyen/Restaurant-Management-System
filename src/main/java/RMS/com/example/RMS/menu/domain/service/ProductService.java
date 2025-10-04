package RMS.com.example.RMS.menu.domain.service;

import RMS.com.example.RMS.common.expection.AppException;
import RMS.com.example.RMS.common.expection.ErrorCode;
import RMS.com.example.RMS.customer.domain.CustomerEntity;
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

    public ProductResponse updateProduct(Long productId, ProductRequest request) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.DATA_NOT_FOUND));

        try {
            product.setCategoryId(request.getCategoryId());
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setPricePromotion(request.getPricePromotion());
            product.setImageUrl(request.getImageUrl());
            product.setUpdatedBy(request.getUpdatedBy());
            product.setUpdatedDate(LocalDateTime.now());
            product.setDescription(request.getDescription());
            productRepository.save(product);

            return productMapper.toProductResponse(product);
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public String deleteProduct(Long productId){
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.DATA_NOT_FOUND));

        try {
            product.setDeleted(true);
            productRepository.save(product);

            return "Data deletion successful";
        }catch (DataAccessException e){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }

    }
}
