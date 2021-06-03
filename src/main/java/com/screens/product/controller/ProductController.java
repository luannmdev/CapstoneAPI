package com.screens.product.controller;

import com.common.form.ResponseCommonForm;
import com.screens.product.form.*;
import com.screens.product.service.ProductService;
import com.screens.store.form.RequestCreateStoreForm;
import com.util.ResponseSupporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final String MSG_009 = "MSG-009";
    private static final String MSG_023 = "MSG-023";

    @GetMapping(value = "/admin/product")
    public String getProductDetail(
            @Validated RequestGetProductDetailForm requestForm,
            BindingResult result){
        // Check Validate
        if(result.hasErrors()){
            return ResponseSupporter.responseErrorResult(result);
        }
        // Do Get Product Detail
        ResponseProductDetailForm res = productService.getProductDetail(requestForm);
        if(res == null){
            List<String> errorCodes = new ArrayList<>();
            errorCodes.add(MSG_023);
            return ResponseSupporter.responseErrorResult(errorCodes);
        }
        // Return result
        return ResponseSupporter.resonpseResult(res);
    }

    @GetMapping(value = "/admin/products")
    public String getProductList(
            @Validated RequestGetProductListForm requestForm,
            BindingResult result){
        // Check Validate
        if(result.hasErrors()){
            return ResponseSupporter.responseErrorResult(result);
        }
        // Do Get Product Detail
        ResponseProductListForm res = productService.getProductList(requestForm);
        if(res == null){
            List<String> errorCodes = new ArrayList<>();
            errorCodes.add(MSG_009);
            return ResponseSupporter.responseErrorResult(errorCodes);
        }
        // Return result
        return ResponseSupporter.resonpseResult(res);
    }

    @PostMapping(value = "/admin/product/create")
    public String createProduct(
            @Validated @RequestBody RequestCreateProductForm requestForm,
            BindingResult result) throws IOException {
        // Check Validate
        if(result.hasErrors()){
            return ResponseSupporter.responseErrorResult(result);
        }

        // Do Create Product
        ResponseCommonForm rs = productService.createProduct(requestForm);
        if(rs.getErrorCodes() != null){
            return ResponseSupporter.responseErrorResult(rs.getErrorCodes());
        }
        // Return result
        return ResponseSupporter.resonpseResult(true);
    }

}