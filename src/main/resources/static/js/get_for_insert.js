function fill_select_table(table_name,max,field_select_id) {
    var sel = window.document.getElementById(field_select_id);
    ajaxGet();
    // DO GET
    function ajaxGet() {
        $.ajax({
            type: "GET",
            url: window.location.origin + "/"+table_name+"/getmax",
            data: {forward: true, first: 0, max: max},
            success: function (result) {
                $.each(result, function (i, customer) {
                    if (table_name == "author") {
                        var curname = customer.first_name+" "+customer.last_name;
                        sel.options[i] = new Option(curname);
                    }
                    else {
                        sel.options[i] = new Option(customer.name);
                    }
                    sel.options[i].value = customer.id;
                });
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    }
}
