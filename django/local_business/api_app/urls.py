from django.urls import path
from .views import ViewCart, ViewProduct, CartIncrease, CartDecrease, CartRemove, MakeOrder

urlpatterns = [
    path('api/cart-items', ViewCart.as_view()),
    path('api/products', ViewProduct.as_view()),
    path('api/add-to-cart', CartIncrease.as_view()),
    path('api/cart-increase', CartIncrease.as_view()),
    path('api/cart-decrease', CartDecrease.as_view()),
    path('api/cart-remove', CartRemove.as_view()),
    path('api/make-order', MakeOrder.as_view()),
]