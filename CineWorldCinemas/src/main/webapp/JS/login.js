/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var url = "http://localhost:8080/CineWorldCinemas/";

function login() {
    if (!loginValidate()) {
        return;
    }

    user = {
        id: $("#login-id").val(),
        password: $("#login-password").val()
    };

    let request = new Request(url + "api/login", {method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(user)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#login-modal #error"));
            return;
        }
        user = await response.json();
        sessionStorage.setItem("user", JSON.stringify(user));
        $("#login-modal").modal("hide");
        switch (user.isAdmin) {
            case false:
                document.location = url;
                break;
            case true:
                document.location = url;
                break;
        }
    })();
}

function loginValidate() {
    $("#login-modal-form").addClass("was-validated");
    return $("#login-modal-form").get(0).checkValidity();
}

function logout() {
    let request = new Request(url + "api/login", {method: "DELETE", headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#login-modal #error"));
            return;
        }
        sessionStorage.removeItem("user");
        document.location = url;
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

function loadLogin() {
    let request = new Request(url + 'presentation/Login.html', {method: 'GET'});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#login-modal #error"));
            return;
        }
        content = await response.text();
        $('body').append(content);
        $("#login-button").click(login);
        $("#logout-link").click(logout);
    })();
}

$(loadLogin);