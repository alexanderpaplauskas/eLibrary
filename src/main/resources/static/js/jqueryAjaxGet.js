$(document).ready(function () {

    ajaxGet();
    
    // DO GET
    function ajaxGet() {
        $.ajax({
            type: "GET",
            url: window.location.origin + "/user/get10",
            success: function (result) {
                $.each(result, function (i, customer) {
                    var customerRow = '<tr>' +
                        '<td>' + customer.id + '</td>' +
                        '<td>' + customer.name.toUpperCase() + '</td>' +
                        '<td>' + customer.email + '</td>' +
                        '<td>' + customer.birth_date + '</td>' +
                        '<td>' + customer.password + '</td>' +
                        '</tr>';

                    $('#customerTable tbody').append(customerRow);

                });

                $("#customerTable tbody tr:odd").addClass("info");
                $("#customerTable tbody tr:even").addClass("success");
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    }
})