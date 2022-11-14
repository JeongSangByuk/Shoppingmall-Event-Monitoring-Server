package com.ssg.shoppingserver.domain.product.application;

import com.ssg.shoppingserver.domain.product.domain.Product;
import com.ssg.shoppingserver.domain.product.domain.ProductCategory;
import com.ssg.shoppingserver.domain.product.dto.response.ProductInfoGetResponse;
import com.ssg.shoppingserver.domain.product.repository.ProductRepository;
import com.ssg.shoppingserver.global.common.BaseLocalDateTimeFormatter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // get total products
    public List<ProductInfoGetResponse> getTotalProducts() {

        // get total products
        List<ProductInfoGetResponse> productInfoGetResponse = productRepository.getProducts().stream()
                .map(product -> ProductInfoGetResponse.builder()
                        .product(product).build())
                .collect(Collectors.toList());

        return productInfoGetResponse;
    }
}
