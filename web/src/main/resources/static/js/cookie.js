function setCookie(key, value) {
    var expires = new Date();
    var minutes = 10;
    expires.setTime(expires.getTime() + minutes * 60 * 1000); //
    document.cookie = key + '=' + value + ';expires=' + expires.toUTCString();
}

function getCookie(key) {
    var keyValue = document.cookie.match('(^|;) ?' + key + '=([^;]*)(;|$)');
    return keyValue ? keyValue[2] : null;
}

// CITY PICKER
// learned from here
// var str = 'https://www.codeproject.com/tips/1009583/how-to-use-cookies-in-jquery-without-a-plugin';
$(document).ready(function() {
    if (!getCookie('current_city')) {
        var city = "No city selected";
        console.log('Current city cookie is not set');
    } else {
        var city = getCookie('current_city');
        console.log('Current city cookie equals ' + city);
    }
    $('#interface-current-city').text(city);
});

$('body').on('click', '#save-current-city', function() {
    var selected_city = $("select[name='city-selected']").val();
    if (selected_city != "none") {
        setCookie('current_city', selected_city);
        console.log("Cookie 'current_city' was saved with value: " + selected_city);
        $('#interface-current-city').text(selected_city);
    }
});