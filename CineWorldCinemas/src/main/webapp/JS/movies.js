/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var cont = 1;

var url = "http://localhost:8080/CineWorldCinemas/";

var movies = new Array();

function carouselContentDisplay(carouselContent, movie) {
    var indicator = $("#indicators");
    carouselContent.append(
            "<div class='carousel-item active' id='" + movie.id + "'>" +
            "<div class='overlay-image' style='background-image:url(" + url + "api/movies/" + movie.title + "/image" + ");'> </div>" +
            "<div class='container'>" +
            "<div class='carousel-caption text-start'>" +
            "</div>" +
            "</div>" +
            "</div>"
            );
    if ($("#indicators").is(':empty')) {
        indicator.append("<button type='button' data-bs-target='#myCarousel' data-bs-slide-to='" + cont + "' class='active' aria-current='true' aria-label='Slide " + cont + "'></button>");
    } else {
        indicator.append("<button type='button' data-bs-target='#myCarousel' data-bs-slide-to='" + cont + "' aria-label='Slide " + cont + "'></button>");
        $("#" + movie.id).removeClass("active");
    }
    cont++;
}

function rowContentDisplay(rowContent, movie) {
    let screeningsTxt = "";
    //movie.screeningsList.forEach((s) => screeningsTxt += "<a href=# class='screening-link'>" + s + "</a>");

    rowContent.append(
            "<div class='col'>" +
            "<div class='card my-card'>" +
            "<img class='image-grid d-block w-100' src='" + url + "api/movies/" + movie.title + "/image' alt=''>" +
            "<div class='card-body'>" +
           // "<div class='screenings text-center'>" + screeningsTxt + "</div>" +
            "<div class='d-flex justify-content-between align-items-center'>" +
            "<small class='card-body-text text-muted px-2'>Duration: " + movie.duration + " minutes</small>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>"
            );
}

function listMovies() {
    var carouselContent = $("#carousel-content");
    var rowContent = $("#row-content");
    carouselContent.html("");
    movies.forEach((movie) => {
        carouselContentDisplay(carouselContent, movie);
        rowContentDisplay(rowContent, movie);
    });
}

function fetchAndList() {
    let request = new Request(url + 'api/movies', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        movies = await response.json();
        listMovies();
    })();
}

//=========================================================================================================================================================================
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
    fetchAndList();
    $("#register-movie-button").click(add);
}

$(loaded);
