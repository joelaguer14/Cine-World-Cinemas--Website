/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var auditorium = {name: "", seatsNumber: 0};

var url = "http://localhost:8080/CineWorldCinemas/";

function reset() {
    auditorium = {name: "", seatsNumber: 0};
}

function validate() {
    var error = false;
    $("#register-auditorium-form input").removeClass("invalid");
    error |= $("#register-auditorium-form input[type='text']").filter((i, e) => {
        return e.value === '';
    }).length > 0;
    $("#register-auditorium-form input[type='text']").filter((i, e) => {
        return e.value === '';
    }).addClass("invalid");
    return !error;
}

function load() {
    auditorium = Object.fromEntries((new FormData($("#register-auditorium-form").get(0))).entries());
}

function add() {
    load();
    if (!validate()) {
        return;
    }
    let request = new Request(url + 'api/register/auditorium', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(auditorium)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#register-auditorium-form #error"));
            return;
        }
        reset();
        render();
    })();
}

function render() {
    $("#register-auditorium-name").val(auditorium.name);
    $("#register-auditorium-seats-quantity").val("");
}

function loaded() {
    $("#register-auditorium-button").click(add);
}

$(loaded);  