package com.ssg.shoppingserver.domain.product.api;

import com.ssg.shoppingserver.domain.product.application.ProductService;
import com.ssg.shoppingserver.domain.product.dto.ProductTotalInfoGetResponse;
import com.ssg.shoppingserver.domain.user.dto.UserTotalInfoGetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductService productService;

    // total product list get api
    @GetMapping("/products")
    public List<ProductTotalInfoGetResponse> getUsers(){
        return productService.getTotalProducts();
    }


}
