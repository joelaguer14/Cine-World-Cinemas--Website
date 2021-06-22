
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var user = {email: "", id: "", name: "", password: ""};
var url = "http://localhost:8080/CineWorldCinemas/";

function reset() {
    user = {email: "", id: "", name: "", password: ""};
}

function validate() {
    var error = false;
    $("#register-modal-form input").removeClass("invalid");
    error |= $("#register-modal-form input[type='text']").filter((i, e) => {
        return e.value === '';
    }).length > 0;
    $("#register-modal-form input[type='text']").filter((i, e) => {
        return e.value === '';
    }).addClass("invalid");

    error |= $("#register-modal-form input[type='email']").filter((i, e) => {
        return e.value === '';
    }).length > 0;
    $("#register-modal-form input[type='email']").filter((i, e) => {
        return e.value === '';
    }).addClass("invalid");

    error |= $("#register-modal-form input[type='password']").filter((i, e) => {
        return e.value === '';
    }).length > 0;
    $("#register-modal-form input[type='password']").filter((i, e) => {
        return e.value === '';
    }).addClass("invalid");
    return !error;
}

function load() {
    user = Object.fromEntries((new FormData($("#register-modal-form").get(0))).entries());
    user.isAdmin = false;
}

function addUser() {
    load();
    if (!validate()) {
        console.log("validate");
        return;
    }
    let request = new Request(url + 'api/register', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(user)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#register-modal #error"));
            return;
        }
        reset();
        $('#register-modal').modal('hide');
    })();
}

function errorMessage(status, place) {
    switch (status) {
        case 404:
            error = "User not found";
            break;
        case 403:
        case 405:
            error = "Unauthorized user";
            break;
        case 406:
        case 405:
            error = "User already exists";
            break;
        default:
            error = "Error: " + status;
            break;
    }
    ;

    place.html("<div class='alert alert-danger alert-dismissible fade show' role='alert'>" +
            "<strong>Error!</strong> " + error + " " +
            "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>" +
            "</div>");
    return;
}

function loaded() {
    let request = new Request(url + 'presentation/Register.html', {method: 'GET'});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#register-modal #error"));
            return;
        }
        content = await response.text();
        $('body').append(content);
        $("#register-button").click(addUser);
    })();
}

$(loaded);  