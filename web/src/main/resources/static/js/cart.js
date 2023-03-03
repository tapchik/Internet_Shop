// adds product to cart
$('body').on('click', '.add-to-cart', function() {
    var product_id = $(this).val().toString();
    console.log("Adding to cart product with id: " + product_id);
    var parameters = `?product_id=${product_id}`;
    $.ajax({type: "POST",
        url: '/add_to_cart' + parameters,
        dataType: "text",
        success:function(result) {
            $(".cart-counter").html(result);
    }});
});

//open cart
$('body').on('click', '#cart-btn', function() {
    window.location.assign("/checkout");
});