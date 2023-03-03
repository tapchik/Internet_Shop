// check if sort not chosen
$(document).ready(function() {
        if ($('input[name="sort-by"]:checked').length == 0) {
            $('#radio5').prop('checked', true);
        }
});

// refreshes list of products without reloading page
$(document).ready(function() {
    $("#filteringform").submit(function(e) {
        e.preventDefault();
        var filter = $('input[name=filter]').val().toString();
        var sort_by = $('input[name=sort-by]:checked').val().toString();
        var parameters = `?filter=${filter}&sort-by=${sort_by}`;
        var current_path = '/catalogue';
        var api_path = `/listofproducts`;
        $.ajax({type: "POST",
            url: api_path + parameters,
            //data: {"filter": filter, "sort_by": sort_by},
            dataType: "text",
            success:function(result) {
                $("#listik").html(result);
                history.replaceState(null, null, window.location.pathname + parameters); // could use pushState
        }});
    });
});