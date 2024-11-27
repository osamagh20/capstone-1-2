package com.example.capstone1.Controller;

import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.ProductService;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        ArrayList<User> users = userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id,@RequestBody @Valid User user, Errors errors){
        boolean isUpdated = userService.updateUser(id,user);
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(isUpdated){
            return ResponseEntity.status(200).body("user updated successfully");
        }
        return ResponseEntity.status(400).body("id not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        boolean isDeleted = userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("user deleted successfully");
        }
        return ResponseEntity.status(400).body("id not found");
    }

    @PutMapping("/buy-product/{idu}/{idp}/{idm}")
    public ResponseEntity buyproduct(@PathVariable String idu,@PathVariable String idp,@PathVariable String idm){
        String buyResponse = userService.buyProduct(idu,idp,idm);

        if (buyResponse.equalsIgnoreCase("user id not found")) {
            return ResponseEntity.status(400).body("user id not found");
        } else if (buyResponse.equalsIgnoreCase("product id not found")) {
            return ResponseEntity.status(400).body("product id not found");
        } else if (buyResponse.equalsIgnoreCase("merchant id not found")) {
            return ResponseEntity.status(400).body("merchant id not found");
        } else if (buyResponse.equalsIgnoreCase("merchant Stock id not equal with merchant id")) {
            return ResponseEntity.status(400).body("merchant id not equal with merchant id");
        } else if (buyResponse.equalsIgnoreCase("Not have enough stock")) {
            return ResponseEntity.status(400).body("Not have enough stock");
        }else if(buyResponse.equalsIgnoreCase("Balance less than price")){
            return ResponseEntity.status(400).body("Balance less than price");
        }else if (buyResponse.equalsIgnoreCase("Done")){
            return ResponseEntity.status(200).body("you buy successful");
        }
        return ResponseEntity.status(400).body("buy product not working please again");
    }

    @PutMapping("/deliver-order/{id}")
    public ResponseEntity deliverOrder(@PathVariable String id){
        boolean isDilvered = userService.deliverProduct(id);
        if(isDilvered){
            return ResponseEntity.status(200).body("deliver order successfully");
        }
        return ResponseEntity.status(400).body("Not have order");
    }

    @PutMapping("/make-subscribe/{ida}/{idc}")
    public ResponseEntity makeSubscribe(@PathVariable String ida,@PathVariable String idc){
        boolean subscribed = userService.giveSubscribe(ida,idc);
        if(subscribed){
            return ResponseEntity.status(200).body("Prime subscribe successfully");
        }
        return ResponseEntity.status(400).body("Not can be Prime subscribe");
    }

    @PutMapping("/recover-product/{idu}/{idp}")
    public ResponseEntity recoverProduct(@PathVariable String idu,@PathVariable String idp){
        String recoverResponse = userService.recoverProduct(idu,idp);

         if (recoverResponse.equalsIgnoreCase("user id not found")) {
            return ResponseEntity.status(400).body("user id not found");
        } else if (recoverResponse.equalsIgnoreCase("user id not equal with user id in order list")) {
            return ResponseEntity.status(400).body("user id not equal with user id in order list");
        } else if (recoverResponse.equalsIgnoreCase("Done Recovery")){
            return ResponseEntity.status(200).body("you recover successful");
        }
        return ResponseEntity.status(400).body("buy product not working please again");
    }

    @PutMapping("/write-comment/{idu}/{idp}")
    public ResponseEntity writeComment(@PathVariable String idu,@PathVariable String idp,@RequestBody String comment){
        String result = userService.commentProduct(idu,idp,comment);
        if (result.equalsIgnoreCase("Done comment")){
            return ResponseEntity.status(200).body("comment successfully");
        } else if (result.equalsIgnoreCase("product id not found")) {
            return ResponseEntity.status(400).body("product id not found");
        }else if (result.equalsIgnoreCase("user id not found")) {
            return ResponseEntity.status(400).body("user id not found");
        }
        return ResponseEntity.status(400).body("comment not working please again");
    }

    @PutMapping("/prime-buy-product/{idu}/{idp}/{idm}")
    public ResponseEntity PrimeDiscount(@PathVariable String idu,@PathVariable String idp,@PathVariable String idm){
        String PrimebuyResponse = userService.PrimebuyProduct(idu,idp,idm);

        if (PrimebuyResponse.equalsIgnoreCase("user id not found")) {
            return ResponseEntity.status(400).body("user id not found");
        } else if (PrimebuyResponse.equalsIgnoreCase("product id not found")) {
            return ResponseEntity.status(400).body("product id not found");
        } else if (PrimebuyResponse.equalsIgnoreCase("merchant id not found")) {
            return ResponseEntity.status(400).body("merchant id not found");
        } else if (PrimebuyResponse.equalsIgnoreCase("merchant Stock id not equal with merchant id")) {
            return ResponseEntity.status(400).body("merchant id not equal with merchant id");
        } else if (PrimebuyResponse.equalsIgnoreCase("Not have enough stock")) {
            return ResponseEntity.status(400).body("Not have enough stock");
        }else if(PrimebuyResponse.equalsIgnoreCase("Balance less than price")){
            return ResponseEntity.status(400).body("Balance less than price");
        }else if (PrimebuyResponse.equalsIgnoreCase("Done")){
            return ResponseEntity.status(200).body("you buy successful");
        }
        return ResponseEntity.status(400).body("buy product not working please again");
    }

    @GetMapping("/order-list/{id}")
    public ResponseEntity getOrderList(@PathVariable String id){
        ArrayList<Product> orderList = userService.orderList(id);
        if (orderList == null){
            return ResponseEntity.status(400).body("order list is empty");
        }
        return ResponseEntity.status(200).body(orderList);
    }

}
