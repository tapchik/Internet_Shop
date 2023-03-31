from django.shortcuts import render
from django.views import View
from django.http import HttpResponse, JsonResponse
from django.utils.decorators import method_decorator
from django.views.decorators.csrf import csrf_exempt
import django.core.exceptions as exceptions
from django.utils.datastructures import MultiValueDictKeyError
import json
from .models import Product, CartItem, Cart, CartItem, Order, OrderDetails

# Create your views here.

@method_decorator(csrf_exempt, name='dispatch')
class ViewProduct(View):
    def get(self, request):
        #count = Product.objects.count()

        filter = request.GET['filter'] if 'filter' in request.GET else ''
        print(filter)
        '''
        if 'filter' not in request.GET:
            products = Product.objects.all()
        else:
            products = Product.objects.filter(title__contains=request.GET['filter'])
        '''
        if 'sort' in request.GET:
            match request.GET['sort']:
                case 'title-asc':
                    products = Product.objects.filter(title__contains=filter).order_by('title')
                case 'title-desc':
                    products = Product.objects.filter(title__contains=filter).order_by('-title')
                case 'price-asc':
                    products = Product.objects.filter(title__contains=filter).order_by('price')
                case 'price-desc':
                    products = Product.objects.filter(title__contains=filter).order_by('-price')
                case _:
                    products = Product.objects.filter(title__contains=filter)
        else:
            products = Product.objects.all()
        
        products_data = []
        for product in products:
            products_data.append({
                'id': product.id,
                'title': product.title,
                'price': product.price,
                'price_beautiful': "{:,.0f} ₽".format(product.price),
                'image': product.image})
        data = {'products': products_data}
        return JsonResponse(data)

@method_decorator(csrf_exempt, name='dispatch')
class ViewCart(View):
    def get(self, request):
        session_id = request.COOKIES.get('sessionid')
        cart, created = Cart.objects.get_or_create(session_id=session_id)
        cart_items = []
        total_items_in_cart = 0
        total_order_price = 0
        for cartItem in CartItem.objects.filter(cart=cart):
            cart_items.append({
                'product_id': cartItem.product.id,
                'quantity': cartItem.quantity,
                'title': cartItem.product.title,
                'price': cartItem.product.price * cartItem.quantity,
                'price_beautiful': "{:,.0f} ₽".format(cartItem.product.price * cartItem.quantity),
                }) 
            total_items_in_cart += cartItem.quantity
            total_order_price += cartItem.product.price * cartItem.quantity
        response = {
            'session_id': cart.session_id,
            'cart_items': cart_items,
            'total_items_in_cart': total_items_in_cart,
            'total_order_price': total_order_price,
            'total_order_price_beautiful': "{:,.0f} ₽".format(total_order_price)}
        return JsonResponse(response, status=201)

@method_decorator(csrf_exempt, name='dispatch')
class CartIncrease(View):

    def post(self, request):

        if 'product_id' not in request.GET:
            response = {"message": "Error, bring parameter 'product_id'"}
            return JsonResponse(response, status=500)
        product_id = request.GET['product_id']
        
        if not Product.objects.filter(id=product_id).exists():
            response = {"message": f"Error, no such product with id={product_id}"}
            return JsonResponse(response, status=500)
        product = Product.objects.get(id=product_id)
        
        session_id = request.COOKIES.get('sessionid')
        cart, created = Cart.objects.get_or_create(session_id=session_id)
        cartItem, created = CartItem.objects.get_or_create(cart=cart, product=product)
        cartItem.quantity += 1
        cartItem.save()

        total_items_in_cart = 0
        for cartItem in CartItem.objects.filter(cart=cart):
            total_items_in_cart += cartItem.quantity;

        response = {
            "message": f"One item of Product(id=\'{cartItem.product.id}\') is added to the cart",
            "product_id": cartItem.product.id,
            "new_quantity": cartItem.quantity,
            "total_items_in_cart": total_items_in_cart}
        return JsonResponse(response, status=201)

@method_decorator(csrf_exempt, name='dispatch')
class CartDecrease(View):

    def post(self, request):

        if 'product_id' not in request.GET:
            response = {"message": "Error, bring parameter 'product_id'"}
            return JsonResponse(response, status=500)
        product_id = request.GET['product_id']
        
        if not Product.objects.filter(id=product_id).exists():
            response = {"message": f"Error, no such product with id={product_id}"}
            return JsonResponse(response, status=500)
        product = Product.objects.get(id=product_id)

        session_id = request.COOKIES.get('sessionid')
        cart, created = Cart.objects.get_or_create(session_id=session_id)
        if not CartItem.objects.filter(cart=cart, product=product).exists():
            response = {
                "message": f"Product with id={product.id} is not in your cart",
                "product_id": product.id,
                "new_quantity": 0}
            return JsonResponse(response, status=500)
        
        cartItem = CartItem.objects.get(cart=cart, product=product)
        if cartItem.quantity > 0:
            cartItem.quantity -= 1
        cartItem.save()

        response = {
            "message": f"Product with id=\'{product.id}\' is decreased by one from the cart",
            "product_id": cartItem.product.id,
            "new_quantity": cartItem.quantity}
        return JsonResponse(response, status=201)

@method_decorator(csrf_exempt, name='dispatch')
class CartRemove(View):

    def post(self, request):

        if 'product_id' not in request.GET:
            response = {"message": "Error, bring parameter 'product_id'"}
            return JsonResponse(response, status=500)
        product_id = request.GET['product_id']
        
        if not Product.objects.filter(id=product_id).exists():
            response = {"message": f"Error, no such product with id={product_id}"}
            return JsonResponse(response, status=500)
        product = Product.objects.get(id=product_id)

        session_id = request.COOKIES.get('sessionid')
        cart, created = Cart.objects.get_or_create(session_id=session_id)
        if not CartItem.objects.filter(cart=cart, product=product).exists():
            response = {
                "message": f"Product with id={product.id} is not in your cart",
                "product_id": product.id,
                "new_quantity": 0}
            return JsonResponse(response, status=500)
        
        cartItem = CartItem.objects.get(cart=cart, product=product)
        cartItem.delete()

        response = {
            "message": f"Product with id=\'{product.id}\') was wiped from the cart",
            "product_id": product.id,
            "new_quantity": 0,
        }
        return JsonResponse(response, status=201)
    
@method_decorator(csrf_exempt, name='dispatch')
class MakeOrder(View):
    def post(self, request):
        request_data = json.loads(request.body.decode("utf-8"))
        city = request_data['city']

        session_id = request.COOKIES.get('sessionid')
        cart, created = Cart.objects.get_or_create(session_id=session_id)
        order = Order.objects.create(city=city)
        for cartItem in CartItem.objects.filter(cart=cart):
            product = Product.objects.get(id=cartItem.product.id)
            quantity = cartItem.quantity
            OrderDetails.objects.create(order=order, product=product, quantity=quantity)
            cartItem.delete()
        cart.delete()
        response = {
            "message": f"Your order with id=\'{order.id}\'is proceeded and will be delivered to {order.city} city",
        }
        return JsonResponse(response, status=201)