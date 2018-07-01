function delete_customer(table_name) {
    var _id = document.getElementsByName("id")[0].value;
    ajaxGet();
    // DO DELETE
    function ajaxGet() {
        $.ajax({
            type: "POST",
            url: window.location.origin + "/"+table_name+"/delete",
            data: {id: _id},
            success: function (result) {
                console.log("DELETE: ", result);
                window.close();
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    }
}
