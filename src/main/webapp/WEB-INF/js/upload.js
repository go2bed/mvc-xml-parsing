function  validation(thisform) {
    with (thisform) {
        if (validateFileExtension(files, "valid_msg", "xml files only allowed!!",
                new Array("xml")) == false) {
            return false;
        }
    }
}

function validateFileExtension(component, msg_id, msg, extns) {
    var flag = 0;
    with (component) {
        var ext = value.substring(value.lastIndexOf('.') + 1);
        for (i = 0; i < extns.length; i++) {
            if (ext == extns[i]) {
                flag = 0;
                break;
            } else {
                flag = 1;
            }
        }
    }
    if (flag != 0) {
        document.getElementById(msg_id).innerHTML = msg;
        component.value = "";
        component.style.backgroundColor = "#eab1b1";
        component.style.border = "thin solid #000000";
        component.focus();
        return false;
    } else {
        return true;
    }
}
