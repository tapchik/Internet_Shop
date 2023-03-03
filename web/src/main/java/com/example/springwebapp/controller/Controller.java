package com.example.springwebapp.controller;

import com.example.springwebapp.model.Order;
import com.example.springwebapp.model.Product;
import com.example.springwebapp.repository.CartRepository;
import com.example.springwebapp.repository.OrderRepository;
import com.example.springwebapp.repository.ProductRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.example.springwebapp.repository.ShopRepository;

import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller {

    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;

    public Controller() {
        productRepository = new ProductRepository();
        cartRepository = new CartRepository();
        orderRepository = new OrderRepository();
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/catalogue")
    public String documentCatalogue(Model model, HttpSession session,
                                  @RequestParam(required=false) String filter,
                                  @RequestParam(name="sort-by", defaultValue = "none") String sort_by) {

        ArrayList<Product> products = productRepository.getProducts(filter=filter,sort_by=sort_by);
        model.addAttribute("products", products);
        model.addAttribute("filter", filter);

        String items_in_cart = cartRepository.countItemsInCart(session);
        model.addAttribute("items_in_cart", items_in_cart);

        String sid = session.getId();
        model.addAttribute("sid", sid);

        return "catalogue";
    }

    @RequestMapping("/checkout")
    public String documentCheckout(Model model, HttpSession session) {
        ArrayList<HashMap<String, String>> cart_items = cartRepository.getCartItems(session, productRepository);
        model.addAttribute("cart_items", cart_items);
        String order_price = cartRepository.getBeautifulOrderPrice(session, productRepository);
        model.addAttribute("order_price", order_price);
        return "checkout";
    }

    @RequestMapping("/listofproducts")
    public String fragmentListOfProducts(Model model, HttpSession session,
                                  @RequestParam(required=false) String filter,
                                  @RequestParam(name="sort-by", defaultValue = "none") String sort_by) {
        ArrayList<Product> products = productRepository.getProducts(filter=filter,sort_by=sort_by);
        model.addAttribute("products", products);
        model.addAttribute("filter", filter);
        return "fragments/listofproducts";
    }

    @RequestMapping("/add_to_cart")
    public String fragmentCartCounterButton(Model model, HttpSession session, @RequestParam(name="product_id") String product_id) {
        cartRepository.plusOneItemToCart(session, product_id);
        String items_in_cart = cartRepository.countItemsInCart(session);
        model.addAttribute("items_in_cart", items_in_cart);
        return "fragments/cart-counter";
    }

    @RequestMapping(value="/cart_increase")
    @ResponseBody
    public Map<String, String> cartIncrease(Model model, HttpSession session, @RequestParam(name="product_id") String product_id) {
        cartRepository.plusOneItemToCart(session, product_id);
        String new_amount = cartRepository.amountOf(session, product_id);
        String order_price = cartRepository.getBeautifulOrderPrice(session, productRepository);
        Map<String, String> response = Map.of("new_amount", new_amount, "order_price", order_price);
        return response;
    }

    @RequestMapping("/cart_decrease")
    @ResponseBody
    public Map<String, String> cartDecrease(Model model, HttpSession session, @RequestParam(name="product_id") String product_id) {
        cartRepository.minusOneItemFromCart(session, product_id);
        String new_amount = cartRepository.amountOf(session, product_id);
        String order_price = cartRepository.getBeautifulOrderPrice(session, productRepository);
        Map<String, String> response = Map.of("new_amount", new_amount, "order_price", order_price);
        return response;
    }

    @RequestMapping("/cart_remove")
    @ResponseBody
    public Map<String, String> cartRemove(Model model, HttpSession session, @RequestParam(name="product_id") String product_id) {
        cartRepository.removeProductFromCart(session, product_id);
        String new_amount = cartRepository.amountOf(session, product_id);
        String order_price = cartRepository.getBeautifulOrderPrice(session, productRepository);
        Map<String, String> response = Map.of("new_amount", new_amount, "order_price", order_price);
        return response;
    }

    @RequestMapping("/make_order")
    @ResponseBody
    public String fragmentMakeOrderButton(Model model, HttpSession session, @RequestParam(name="city") String city) {
        Boolean success = orderRepository.makeOrder(new Date().toString(), new Date().toString(), city);
        if (city.equals("null")) {
            throw new Error("City is null");
        }
        if (success.equals(false)) {
            return "Error";
        }
        session.invalidate();
        return "OK";
    }

}
