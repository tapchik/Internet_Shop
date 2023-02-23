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
        ArrayList<Product> products;
        if (sort_by.equals("description-asc")) {
            products = productRepository.getProductsSortedByDescriptionAsc();
        }
        else if (sort_by.equals("description-desc")) {
            products = productRepository.getProductsSortedByDescriptionDesc();
        }
        else if (sort_by.equals("price-asc")) {
            products = productRepository.getProductsSortedByPriceAsc();
        }
        else if (sort_by.equals("price-desc")) {
            products = productRepository.getProductsSortedByPriceDesc();
        }
        else {
            products = productRepository.getProductsUnsorted();
        }

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
        System.out.println(sort_by);
        ArrayList<Product> products;
        if (sort_by.equals("description-asc")) {
            products = productRepository.getProductsSortedByDescriptionAsc();
        }
        else if (sort_by.equals("description-desc")) {
            products = productRepository.getProductsSortedByDescriptionDesc();
        }
        else if (sort_by.equals("price-asc")) {
            products = productRepository.getProductsSortedByPriceAsc();
        }
        else if (sort_by.equals("price-desc")) {
            products = productRepository.getProductsSortedByPriceDesc();
        }
        else {
            products = productRepository.getProductsUnsorted();
        }

        model.addAttribute("products", products);
        model.addAttribute("filter", filter);
        return "fragments/listofproducts";
    }

    @RequestMapping(value="/sort-by-description-asc")
    public String sortByDescriptionAsc(Model model, HttpSession session) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        model.addAttribute("products", productRepository.getProductsSortedByDescriptionAsc());
        return "index";
    }

    @RequestMapping(value="/sort-by-description-desc")
    public String sortByDescriptionDesc(Model model, HttpSession session) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        model.addAttribute("products", productRepository.getProductsSortedByDescriptionDesc());
        return "index";
    }

    @RequestMapping(value="/sort-by-price-asc")
    public String sortByPriceAsc(Model model, HttpSession session) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        model.addAttribute("products", productRepository.getProductsSortedByPriceAsc());
        return "index";
    }

    @RequestMapping(value="/sort-by-price-desc")
    public String sortByPriceDesc(Model model, HttpSession session) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        model.addAttribute("products", productRepository.getProductsSortedByPriceDesc());
        return "index";
    }
}
