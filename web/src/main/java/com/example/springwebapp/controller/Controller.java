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

    private ProductRepository productRepository;

    public Controller() {
        productRepository = new ProductRepository();
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
        model.addAttribute("filter", filter);
        return "fragments/listofproducts";
    }
}
