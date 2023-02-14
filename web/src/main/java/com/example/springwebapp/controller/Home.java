package com.example.springwebapp.controller;

import com.example.springwebapp.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springwebapp.model.Product;
import com.example.springwebapp.model.NewProduct;
//import com.example.springwebapp.repository.ShopRepository;

import com.example.springwebapp.model.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Denis on 2/20/2016.
 */

@Controller
public class Home {

    //@Autowired
    //private ShopRepository shopRepository;

    private Storage storage;

    public Home() {
        this.storage = new Storage();
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/home-with-session")
    public String homeWithSession(Model model, HttpSession session) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        model.addAttribute("products", storage.getProductsUnsorted());
        return "index";
    }

    @RequestMapping(value="/sort-by-description-asc")
    public String sortByDescriptionAsc(Model model, HttpSession session) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        model.addAttribute("products", storage.getProductsSortedByDescriptionAsc());
        return "index";
    }

    @RequestMapping(value="/sort-by-description-desc")
    public String sortByDescriptionDesc(Model model, HttpSession session) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        model.addAttribute("products", storage.getProductsSortedByDescriptionDesc());
        return "index";
    }

    @RequestMapping(value="/sort-by-price-asc")
    public String sortByPriceAsc(Model model, HttpSession session) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        model.addAttribute("products", storage.getProductsSortedByPriceAsc());
        return "index";
    }

    @RequestMapping(value="/sort-by-price-desc")
    public String sortByPriceDesc(Model model, HttpSession session) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        model.addAttribute("products", storage.getProductsSortedByPriceDesc());
        return "index";
    }


    /*
    @GetMapping("/tutorials")
    public String getAll(Model model, @RequestParam(required = false) String keyword,
                         @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            List<NewProduct> tutorials = new ArrayList<NewProduct>();
            Pageable paging = PageRequest.of(page - 1, size);

            Page<NewProduct> pageTuts;
            if (keyword == null) {
                pageTuts = shopRepository.findAll(paging);
            } else {
                pageTuts = shopRepository.findByTitleContainingIgnoreCase(keyword, paging);
                model.addAttribute("keyword", keyword);
            }

            tutorials = pageTuts.getContent();

            model.addAttribute("tutorials", tutorials);
            model.addAttribute("currentPage", pageTuts.getNumber() + 1);
            model.addAttribute("totalItems", pageTuts.getTotalElements());
            model.addAttribute("totalPages", pageTuts.getTotalPages());
            model.addAttribute("pageSize", size);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "tutorials";
    }*/
}
