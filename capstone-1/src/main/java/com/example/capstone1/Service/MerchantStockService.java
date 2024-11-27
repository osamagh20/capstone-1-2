package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    private final ProductService productService;
    private final MerchantService merchantService;
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public MerchantStockService(ProductService productService, MerchantService merchantService) {
        this.productService = productService;
        this.merchantService = merchantService;
    }

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public int addMerchantStock(MerchantStock merchantStock){
        for (int i = 0; i < productService.getProducts().size(); i++) {
            if(merchantStock.getProductId().equals(productService.getProducts().get(i).getId())){
                for (int j=0; j<merchantService.getMerchants().size(); j++) {
                    if (merchantStock.getMerchantId().equals(merchantService.getMerchants().get(j).getId())) {
                        merchantStocks.add(merchantStock);
                        return 1;
                    }
                }
                return 2;
            }
        }
        return 3;
    }

    public boolean updateMerchantStock(String id,MerchantStock merchantStock){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(String id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addStock(String merid,String prodid,int additionalStock){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantId().equals(merid)) {
                if (merchantStocks.get(i).getProductId().equals(prodid)) {
                    merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + additionalStock);
                    return true;
                }
            }
        }
        return false;
    }

}
