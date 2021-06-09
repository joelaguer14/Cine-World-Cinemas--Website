/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var movie = {title: "", description: "", duration: 0, price: 0};

var url = "http://localhost:8080/CineWorldCinemas/";

function reset() {
    movie = {title: "", description: "", duration: 0, price: 0};
}

function validate() {
    var error = false;
    $("#register-movie-form input").removeClass("invalid");
    error |= $("#register-movie-form input[type='text']").filter((i, e) => {
        return e.value === '';
    }).length > 0;
    $("#register-movie-form input[type='text']").filter((i, e) => {
        return e.value === '';
    }).addClass("invalid");
    return !error;
}

function load() {
    movie = Object.fromEntries((new FormData($("#register-movie-form").get(0))).entries());
}

function addImage() {
    var imageData = new FormData();
    imageData.append("title", movie.title);
    imageData.append("image", $("#register-movie-image").get(0).files[0]);
    let request = new Request(url + 'api/movies/' + movie.title + "/image", {method: 'POST', body: imageData});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#add-modal #errorDiv"));
            return;
        }
    })();
}

function add() {
    load();
    if (!validate()) {
        return;
    }
    let request = new Request(url + 'api/movies/register', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(movie)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#register-movie-form #error"));
            return;
        }
        addImage();
        reset();
        render();
    })();
}

function render() {
    $("#register-movie-price").val("");
    $("#register-movie-duration").val("");
    $("#register-movie-title").val("");
    $("#register-movie-description").val("");
}

function loaded() {
    $("#register-movie-button").click(add);
}

$(loaded);  