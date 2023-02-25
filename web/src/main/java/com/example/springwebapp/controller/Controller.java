package com.example.springwebapp.controller;

import com.example.springwebapp.model.Cart;
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
    private int temporary_count;

    public Controller() {
        productRepository = new ProductRepository();
        cartRepository = new CartRepository();
        orderRepository = new OrderRepository();
        temporary_count = 0;
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

        int value = cartRepository.countItemsInCart(session);
        String items_in_cart = String.valueOf(value);
        model.addAttribute("items_in_cart", items_in_cart);

        String sid = session.getId();
        model.addAttribute("sid", sid);

        return "catalogue";
    }

    @RequestMapping("/checkout")
    public String documentCheckout(Model model, HttpSession session) {
        ArrayList<HashMap<String, String>> cart_items = cartRepository.getCartItems(session, productRepository);
        model.addAttribute("cart_items", cart_items);
        return "checkout";
    }

    @RequestMapping("/listofproducts")
    public String fragmentListOfProducts(Model model, HttpSession session,
                                  @RequestParam(required=false) String filter,
                                  @RequestParam(name="sort-by", defaultValue = "none") String sort_by) {
        ArrayList<Product> products = productRepository.getProducts(filter=filter,sort_by=sort_by);
        model.addAttribute("products", products);
        String items_in_cart = String.valueOf(temporary_count); //TODO count real number of items in cart
        model.addAttribute("filter", filter);
        return "fragments/listofproducts";
    }

    @RequestMapping("/add_to_cart")
    public String fragmentCartCounterButton(Model model, HttpSession session, @RequestParam(name="product_id") String product_id) {
        cartRepository.plusOneItemToCart(session, product_id);
        int value = cartRepository.countItemsInCart(session);
        String items_in_cart = String.valueOf(value);
        model.addAttribute("items_in_cart", items_in_cart);
        return "fragments/cart-counter";
    }

    @RequestMapping(value="/cart_increase")
    @ResponseBody
    public String cartIncrease(Model model, HttpSession session, @RequestParam(name="product_id") String product_id) {
        cartRepository.plusOneItemToCart(session, product_id);
        String new_amount = cartRepository.amountOf(session, product_id);
        return new_amount;
    }

    @RequestMapping("/cart_decrease")
    @ResponseBody
    public String cartDecrease(Model model, HttpSession session, @RequestParam(name="product_id") String product_id) {
        // TODO fix error when zero amount
        cartRepository.minusOneItemFromCart(session, product_id);
        String new_amount = cartRepository.amountOf(session, product_id);
        return new_amount;
    }

    @RequestMapping("/cart_remove")
    @ResponseBody
    public String cartRemove(Model model, HttpSession session, @RequestParam(name="product_id") String product_id) {
        // TODO fix error when zero amount
        cartRepository.removeProductFromCart(session, product_id);
        String new_amount = cartRepository.amountOf(session, product_id);
        return new_amount;
    }

    @RequestMapping("/make_order")
    public String fragmentMakeOrderButton(Model model, HttpSession session, @RequestParam(name="city") String city) {
        // TODO Сохранить заказ в базу данных и опустошить корзину
        orderRepository.makeOrder(new Date().toString(), new Date().toString(), city);
        //cartRepository.deleteCartForSession(session);
        /*for (Order order: orderRepository ) {
            System.out.println(order.order_city + " " + order.order_time);
        }*/
        return "ok";
    }

}
