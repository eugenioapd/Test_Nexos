package com.epinerez.testnexos.controllers;

import com.epinerez.testnexos.models.Product;
import com.epinerez.testnexos.models.User;
import com.epinerez.testnexos.service.IProductService;
import com.epinerez.testnexos.service.IUserService;
import com.epinerez.testnexos.service.imple.ProductServiceImple;
import com.epinerez.testnexos.service.imple.UserServiceImple;
import com.epinerez.testnexos.utils.Errors;
import com.epinerez.testnexos.utils.ProductsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) {
        try {

            //Si ya existe un ´producto con ese nombre
            if (productService.findByNameProduct(product.getNameProduct()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponseError(HttpStatus.BAD_REQUEST, ProductsConstants.PRODUCT_NAME_UNIQUE_VALIDATION, "", "/api/product"));
            }
            //Si la fecha de creación es mayor a la actual
            if (product.getDate().after(Calendar.getInstance().getTime())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponseError(HttpStatus.BAD_REQUEST, ProductsConstants.PRODUCT_DATE_INVALID, "", "/api/product"));
            }

            //Si la cantidad del producto es inválida
            if (product.getAmount() == null || product.getAmount() < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponseError(HttpStatus.BAD_REQUEST, ProductsConstants.PRODUCT_AMOUNT_INVALID, "", "/api/product"));
            }

            if (product.getId() != null) {
                Product newProduct = productService.save(product);
                return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
            } else {
                Product newProduct = productService.save(product);
                return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ProductsConstants.PRODUCT_ERROR_REGISTER, ex.getStackTrace()[0].toString(), "/api/product"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product,@PathVariable("id") Integer id){
        Product productBd = productService.findById(id);

        if(productBd == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try{

            //Si la fecha de creación es mayor a la actual
            if (product.getDate().after(Calendar.getInstance().getTime())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponseError(HttpStatus.BAD_REQUEST, ProductsConstants.PRODUCT_DATE_INVALID, "", "/api/product"));
            }

            //Si la cantidad del producto es inválida
            if (product.getAmount() == null || product.getAmount() < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponseError(HttpStatus.BAD_REQUEST, ProductsConstants.PRODUCT_AMOUNT_INVALID, "", "/api/product"));
            }

        productBd.setId(product.getId());
        productBd.setNameProduct(product.getNameProduct());
        productBd.setAmount(product.getAmount());
        productBd.setDate(product.getDate());
        productBd.setUser(product.getUser());
        productService.save(productBd);
        return ResponseEntity.status(HttpStatus.CREATED).body(productBd);

        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/dates")
    public ResponseEntity<List<Product>> findAllByDates(@RequestParam("date_start") Date dateStart,@RequestParam("date_end") Date date_end){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.findByDates(dateStart,date_end));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        try {
            return new ResponseEntity(productService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> findById(@PathVariable("id") Integer id) {
        try{
            return new ResponseEntity(productService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@RequestParam("user_id") Integer user_id, @PathVariable("id") Integer id) {

        Product product = productService.findById(id);

        if(product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getResponseError(HttpStatus.NOT_FOUND,ProductsConstants.PRODUCT_NOT_FOUND,"","/api/product/"+id));
        }

        try {
            //Si el usuario del producto registrado es igual al usuario que ejecuta la acción eliminar
            if (product.getUser().getId() == user_id) {
                productService.delete(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(getResponseError(HttpStatus.FORBIDDEN, ProductsConstants.PRODUCT_USER_DELETE_INVALID, "", "/api/product"));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ProductsConstants.PRODUCT_USER_DELETE_INVALID, ex.getStackTrace()[0].toString(), "/api/product"));
        }
    }


    public Errors getResponseError(HttpStatus httpStatus, String message, String detail, String url) {
        return Errors.builder().status(httpStatus.value()).message(message).url(url).build();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
