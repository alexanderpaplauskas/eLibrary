var last_first_id = 0;

$(document).ready(fill_table(true));

function fill_table(forward) {
    ajaxGet();

    // DO GET
    function ajaxGet() {
        $.ajax({
            type: "GET",
            url: window.location.origin + "/"+table_name+"/getmax",
            data: {forward: forward, first: last_first_id, max: max_size_page},
            success: function (result) {

                if (result.length > 0) {
                    remove_all_rows();
                }

                $.each(result, function (i, customer) {
                    var customerRow = '<tr ondblclick = "get_cur_customer(' + customer.id + ')">' +
                        fill_table_columns(customer) +
                        '</tr>';
                    $('#customerTable tbody').append(customerRow);
                    last_first_id = customer.id;
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
}

function remove_all_rows() {
    var table = document.getElementById('customerTable');
    while (table.rows.length > 1) {
        table.deleteRow(1);
    }
}

function go_prev() {
    if (last_first_id < 0) {
        last_first_id = 0
    }
    if (last_first_id < 1) {
        return
    }
    fill_table(false);
}

function go_next() {
    fill_table(true);
}

function edit_customer(id) {
    ajaxGet();

    // DO GET
    function ajaxGet() {
        $.ajax({
            type: "GET",
            url: window.location.origin + "/"+table_name+"/get",
            data: {id: id},
            success: function (result) {
                var w = window.open("", "_blank");
                w.document.write(result);
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    }
}

function get_cur_customer(id) {
    edit_customer(id);
}
