function fill_fild_for_select(field_id, field_select_id){
    var prop = document.getElementById(field_id).valueOf().value;
    if (prop != undefined){
        var propS = document.getElementById(field_select_id);
        var prop1 = JSON.parse("["+prop+"]");

        $.each(prop1, function (i, customer) {
            propS.options[i] = new Option(customer.name);
            propS.options[i].value = customer.id;
        });
    }
    save_list_data(field_id, field_select_id);
}


function delete_cur_item(field_id, field_select_id) {
    var propS = document.getElementById(field_select_id);
    var selected = propS.selectedIndex;
    var last = propS.options.length - 1;
    for(i = selected; i < last; i++){
        propS.options[i] = new Option(propS.options[i + 1].text);
    }
    propS.options[last] = null;
    save_list_data(field_id, field_select_id);
}


function add_cur_item(field_id, field_select_id, field_select_all_id) {
    var propS = document.getElementById(field_select_id);
    var propSall = document.getElementById(field_select_all_id);
    var size = propS.options.length;
    for(i = 0; i < size; i++) { // this is required for IE  ???
        if(propS.options[i] == null || propS.options[i].text == ""){
            propS.options[i] = new Option(propSall[propSall.selectedIndex].text);
            propS.options[i].value = propSall[propSall.selectedIndex].value;
            save_list_data(field_id, field_select_id);
            return;
        }}
    propS.options[size] = new Option(propSall[propSall.selectedIndex].text);
    propS.options[size].value = propSall[propSall.selectedIndex].value;
    save_list_data(field_id, field_select_id);
}


function save_list_data(field_id, field_select_id){
    var prop = document.getElementById(field_id);
    var propS = document.getElementById(field_select_id);
    var last = propS.options.length;
    var s = "";
    for(i = 0; i < last; i++){
        //s=s+"{\"id\":\""+propS[i].value+"\",\"name\":\""+propS[i].text+"\"},";
        s=s+propS[i].value+",";
    }
    prop.value = s;
}

