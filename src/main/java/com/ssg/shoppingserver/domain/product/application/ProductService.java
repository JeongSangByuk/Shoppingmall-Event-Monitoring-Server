package com.ssg.shoppingserver.domain.product.application;

import com.ssg.shoppingserver.domain.product.domain.Product;
import com.ssg.shoppingserver.domain.product.domain.ProductCategory;
import com.ssg.shoppingserver.domain.product.dto.ProductInfoGetResponse;
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

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Getter
    // product 메모리 보관 queue
    private Queue<Product> products = new ConcurrentLinkedQueue<Product>();

    @PostConstruct
    private void init() throws IOException, ParseException {

        // create mock product data
        createMockData();
    }

    // get total products
    public List<ProductInfoGetResponse> getTotalProducts() {

        // get total products
        List<ProductInfoGetResponse> productInfoGetRespons = products.stream()
                .map(product -> ProductInfoGetResponse.builder()
                        .product(product).build())
                .collect(Collectors.toList());

        return productInfoGetRespons;
    }

    // create mock product data
    public void createMockData() throws IOException, ParseException {

        // get mock data json file
        ClassPathResource resource = new ClassPathResource("mock-data/product-mock-data.json");
        Path path = Paths.get(resource.getURI());
        String json = Files.readString(path);

        // parsing json
        JSONArray mockProducts = (JSONArray) new JSONParser().parse(json);

        // add mock product data
        for (Object mockProductsObjet : mockProducts) {

            JSONObject mockProduct = (JSONObject) mockProductsObjet;

            // create mock product entity
            Product product = Product.builder()
                    .id(UUID.fromString((String) mockProduct.get("id")))
                    .name((String) mockProduct.get("name"))
                    .quantity((Long) mockProduct.get("quantity"))
                    .price((Long) mockProduct.get("price"))
                    .productCategory(ProductCategory.findByCode((Long) mockProduct.get("productCategory")))
                    .createdAt(LocalDateTime.parse((String) mockProduct.get("createdAt"), BaseLocalDateTimeFormatter.getLocalTimeFormatter())).build();

            // mock product data add
            products.add(product);
        }
    }

}
