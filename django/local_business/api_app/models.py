from django.db import models

# Create your models here.

class Product(models.Model):
    id = models.CharField(primary_key=True, max_length=100)
    title = models.CharField(null=False, max_length=255)
    price = models.FloatField(null=False)
    image = models.URLField()
    def __str__(self):
        return f"Product(id='{self.id}', title='{self.title}', price={self.price})"

class Cart(models.Model):
    session_id = models.CharField(primary_key=True, max_length=255)

class CartItem(models.Model):
    cart = models.ForeignKey(Cart, on_delete=models.CASCADE)
    product = models.ForeignKey(Product, on_delete=models.CASCADE)
    quantity = models.PositiveSmallIntegerField(null=False, default=0)

class Order(models.Model):
    id = models.AutoField(primary_key=True)
    city = models.CharField(null=False, max_length=255)
    timestemp = models.DateTimeField(auto_now_add=True)

class OrderDetails(models.Model):
    order = models.ForeignKey(Order, on_delete=models.CASCADE)
    product = models.ForeignKey(Product, on_delete=models.CASCADE)
    quantity = models.PositiveSmallIntegerField(null=False)
    def __str__(self):
        return f"OrderDetails(order.id={self.order.id}, product.id='{self.product.id}', qty={self.quantity})"

