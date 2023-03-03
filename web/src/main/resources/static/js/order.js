// make order
$('body').on('click', '#make-order-btn', function() {
    var city = getCookie('current_city');
    var parameters = `?city=${city}`;
    $.ajax({type: "POST",
        url: '/make_order' + parameters,
        dataType: "text",
         success:function(result) {
            var response = result;
            console.log(response);
            if (response=='OK') {
                alert("Order is processed!");
                window.location.replace("catalogue");
            } else {
                alert("Sorry, could not proceed your purchase");
            }
        },
        error:function(xhr, status, error) {
            if (city==null) {
                alert("City is null, please choose city to make order");
            }
            else {
                alert(xhr.responseText);
            }
        }
    });
});