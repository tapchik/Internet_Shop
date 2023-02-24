package com.example.springwebapp.controller;

import com.example.springwebapp.model.Product;
import com.example.springwebapp.repository.ProductRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestParam;

//import com.example.springwebapp.repository.ShopRepository;

import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller {

    public ProductRepository productRepository;
    private int temporary_count;

    public Controller() {
        productRepository = new ProductRepository();
        temporary_count = 0;
    }

    @RequestMapping("/")
    public String home() {
        return "navigation";
    }

    @RequestMapping("/catalogue")
    public String homeWithSession(Model model, HttpSession session,
                                  @RequestParam(required=false) String filter,
                                  @RequestParam(name="sort-by", defaultValue = "none") String sort_by
                                  ) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        ArrayList<Product> products = productRepository.getProducts(filter=filter,sort_by=sort_by);
        model.addAttribute("products", products);
        String items_in_cart = String.valueOf(temporary_count); //TODO count real number of items in cart
        model.addAttribute("items_in_cart", items_in_cart);
        model.addAttribute("filter", filter);
        return "index";
    }

    @RequestMapping("/listofproducts")
    public String listOfProductsFragment(Model model, HttpSession session,
                                  @RequestParam(required=false) String filter,
                                  @RequestParam(name="sort-by", defaultValue = "none") String sort_by
    ) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        ArrayList<Product> products = productRepository.getProducts(filter=filter,sort_by=sort_by);
        model.addAttribute("products", products);
        String items_in_cart = String.valueOf(temporary_count); //TODO count real number of items in cart
        model.addAttribute("filter", filter);
        return "fragments/listofproducts";
    }

    @RequestMapping("/add_to_cart")
    public String addToCart(Model model, HttpSession session, @RequestParam(name="product_id") String product_id) {
        String sid = session.getId();
        this.temporary_count += 1;
        String items_in_cart = String.valueOf(temporary_count); //TODO count real number of items in cart
        model.addAttribute("items_in_cart", items_in_cart);
        return "fragments/cart-counter";
    }
}
