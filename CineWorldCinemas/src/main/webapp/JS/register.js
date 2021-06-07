/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function loaded() {
    $("#register-button").on("click", function () {
        if (!validate()) {
            register();
        }
    });
}

$(loaded);

function validate() {
    var error = false;
    $('.register-input').each(function () {
        if (!$(this).val()) {
            $("#alert").show();
            error = true;
        }
    });
    return error;
}

function register() {
    user = {
        id: $("#register-id").val(),
        name: $("#register-fullname").val(),
        email: $("#register-email").val(),
        password: $("#register-password").val(),
        administrator: 0
    };

    $.ajax({type: "POST", url: "/CineWorldCinemas/api/register/", data: JSON.stringify(user), contentType: "application/json"})
            .then(() => {
                window.location.replace("http://localhost:8080/CineWorldCinemas/");
            },
                    (error) => {
                alert(errorMessage(error.status));
            });
}

function errorMessage(status) {
    switch (status) {
        case 404:
            return "User not found";
        case 403:
            return "Access denied";
        case 405:
            return "Unauthorized user";
        case 406:
            return "Duplicated user";
        default:
            return "Error: " + status;
    }
}
