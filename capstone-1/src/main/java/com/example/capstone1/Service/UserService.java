package com.example.capstone1.Service;

import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private final ProductService productService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;
    ArrayList<User> users = new ArrayList<>();


    public UserService(ProductService productService, MerchantService merchantService, MerchantStockService merchantStockService) {
        this.productService = productService;
        this.merchantService = merchantService;
        this.merchantStockService = merchantStockService;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean updateUser(String id,User user){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }



    public String buyProduct(String idu, String idp, String idm){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(idu)){
                for (int j = 0; j < productService.Products.size(); j++) {
                   if(productService.getProducts().get(j).getId().equals(idp)){
                       for (int k = 0; k < merchantService.merchants.size(); k++) {
                           if(merchantService.getMerchants().get(k).getId().equals(idm)){
                              for (int b=0;b<merchantStockService.merchantStocks.size();b++) {
                                  if(merchantStockService.merchantStocks.get(b).getMerchantId().equals(idm)){
                                      if(merchantStockService.merchantStocks.get(b).getStock() >0){
                                          if(users.get(i).getBalance()>productService.getProducts().get(j).getPrice()){
                                              merchantStockService.merchantStocks.get(b).setStock(merchantStockService.merchantStocks.get(b).getStock()-1);
                                              users.get(i).setBalance(users.get(i).getBalance()-productService.getProducts().get(j).getPrice());
                                              users.get(i).setItem(users.get(i).getItem()+1);
                                              users.get(i).setVisiting(users.get(i).getVisiting()+1);
                                              users.get(i).getOrederList().add(productService.getProducts().get(j));
                                              return "Done";
                                          }
                                          return "Balance less than price";
                                      }
                                      return "Not have enough stock";
                                  }

                              }
                               return "merchant id not equal with merchant stock id";
                           }

                       }
                       return "merchant id not found";
                   }

                }
                return "product id not found";
            }

        }
        return "user id not found";
    }

    // extra-1 (delivery Order)
    public boolean deliverProduct(String id){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id) && users.get(i).getItem()>0){
                        users.get(i).setItem(0);
                        return true;
            }
        }
        return false;
    }

    // extra-2 (subscribe)
    public boolean giveSubscribe(String ida,String idc){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(ida) && users.get(i).getRole().equals("Admin")){
                for (int j = 0; j < users.size(); j++) {
                    if(users.get(j).getId().equals(idc) && users.get(j).getVisiting()>5){
                        users.get(j).setSubscribe("prime");
                        return true;
                    }
                }

            }
        }
        return false;
    }

    // extra-3 (recover product)
    public String recoverProduct(String idu, String idp) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(idu)) {
                for (int j = 0; j < users.get(i).getOrederList().size(); j++) {
                    if (productService.getProducts().get(j).getId().equals(idp)) {
                        users.get(i).setBalance(users.get(i).getBalance()+productService.getProducts().get(j).getPrice());
                        users.get(i).setItem(users.get(i).getItem()+1);
                        users.get(i).getOrederList().remove(j);
                        return "Done Recovery";
                    }
                }

            }

        }
        return "user id not found";
    }



    // extra-4 (write comment)
    public String commentProduct(String ida,String idp,String comment){

        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(ida)){
                for (int j = 0; j < productService.Products.size(); j++) {
                    if(productService.getProducts().get(j).getId().equals(idp)){
                        productService.getProducts().get(j).setComments(productService.getProducts().get(j).getComments()+("("+comment+")"));
                        return "Done comment";
                    }
                }
                return "product id not found";
            }
        }
        return "user id not found";
    }

    // extra-5 (prime feature)
    public String PrimebuyProduct(String idu, String idp, String idm){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(idu)){
                for (int j = 0; j < productService.Products.size(); j++) {
                    if(productService.getProducts().get(j).getId().equals(idp)){
                        for (int k = 0; k < merchantService.merchants.size(); k++) {
                            if(merchantService.getMerchants().get(k).getId().equals(idm)){
                                for (int b=0;b<merchantStockService.merchantStocks.size();b++) {
                                    if(merchantStockService.merchantStocks.get(b).getMerchantId().equals(idm)){
                                        if(merchantStockService.merchantStocks.get(b).getStock() >0){
                                            if(users.get(i).getBalance()>productService.getProducts().get(j).getPrice()){
                                               if(users.get(i).getSubscribe().equals("Prime") && users.get(i).getVisiting()>5 && users.get(i).getVisiting()<10){
                                                   merchantStockService.merchantStocks.get(b).setStock(merchantStockService.merchantStocks.get(b).getStock()-1);
                                                   users.get(i).setBalance(users.get(i).getBalance()-((productService.getProducts().get(j).getPrice())-(productService.getProducts().get(j).getPrice()*0.10)));
                                                   users.get(i).setItem(users.get(i).getItem()+1);
                                                   users.get(i).setVisiting(users.get(i).getVisiting()+1);
                                                   return "Done";
                                               } else if(users.get(i).getSubscribe().equals("Prime") && users.get(i).getVisiting()>10 && users.get(i).getVisiting()<15){
                                                   merchantStockService.merchantStocks.get(b).setStock(merchantStockService.merchantStocks.get(b).getStock()-1);
                                                   users.get(i).setBalance(users.get(i).getBalance()-((productService.getProducts().get(j).getPrice())-(productService.getProducts().get(j).getPrice()*0.20)));
                                                   users.get(i).setItem(users.get(i).getItem()+1);
                                                   users.get(i).setVisiting(users.get(i).getVisiting()+1);
                                                   return "Done";
                                               } else if(users.get(i).getSubscribe().equals("Prime") && users.get(i).getVisiting()>=15){
                                                   merchantStockService.merchantStocks.get(b).setStock(merchantStockService.merchantStocks.get(b).getStock()-1);
                                                   users.get(i).setBalance(users.get(i).getBalance()-((productService.getProducts().get(j).getPrice())-(productService.getProducts().get(j).getPrice()*0.30)));
                                                   users.get(i).setItem(users.get(i).getItem()+1);
                                                   users.get(i).setVisiting(users.get(i).getVisiting()+1);
                                                   return "Done";
                                               }

                                            }
                                            return "Balance less than price";
                                        }
                                        return "Not have enough stock";
                                    }

                                }
                                return "merchant id not equal with merchant stock id";
                            }

                        }
                        return "merchant id not found";
                    }

                }
                return "product id not found";
            }

        }
        return "user id not found";
    }


    public ArrayList<Product> orderList(String id){
        for (int i = 0; i <users.size(); i++) {
            if(users.get(i).getId().equals(id)){
                return users.get(i).getOrederList();
            }
        }
        return null;
    }

}


