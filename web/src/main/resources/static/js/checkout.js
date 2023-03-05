// increase count of chosen product
$('body').on('click', 'button[name="increase-qty"]', function() {
        var product_id = $(this).val().toString();
        var parameters = `?product_id=${product_id}`;
        $.ajax({type: "POST",
            url: '/cart_increase' + parameters,
            dataType: "text",
            success:function(result) {
                const jsonResponse = JSON.parse(result);
                console.log("Quantity of product_id="+product_id+" just increased by one and now equals "+jsonResponse['new_amount']);
                $('p#'+product_id).text(jsonResponse['new_amount']);
                $('#make-order-btn').text('Make an order, total: ' + jsonResponse['order_price']);
        }});
});

// decrease count of chosen product
$('body').on('click', 'button[name="decrease-qty"]', function() {
        var product_id = $(this).val().toString();
        var parameters = `?product_id=${product_id}`;
        $.ajax({type: "POST",
            url: '/cart_decrease' + parameters,
            dataType: "text",
            success:function(result) {
                const jsonResponse = JSON.parse(result);
                if (jsonResponse['new_amount'] == '0') {
                    $('p#'+product_id).text(jsonResponse['new_amount']);
                    console.log("product_id="+product_id+" will stay at zero, can't decrease");
                } else {
                    $('p#'+product_id).text(jsonResponse['new_amount']);
                    console.log("Quantity of product_id="+product_id+" just decreased by one and now equals "+jsonResponse['new_amount']);
                }
                $('#make-order-btn').text('Make an order, total: ' + jsonResponse['order_price']);
        }});
});

// delete chosen product
$('body').on('click', 'button[name="delete-from-cart"]', function() {
    var product_id = $(this).val().toString();
    var parameters = `?product_id=${product_id}`;
    $.ajax({type: "POST",
        url: '/cart_remove' + parameters,
        dataType: "text",
        success:function(result) {
            const jsonResponse = JSON.parse(result);
            $('#cont-'+product_id).remove();
            $('#make-order-btn').text('Make an order, total: ' + jsonResponse['order_price']);
            console.log("product_id="+product_id+" was completely removed from cart");
    }});
});