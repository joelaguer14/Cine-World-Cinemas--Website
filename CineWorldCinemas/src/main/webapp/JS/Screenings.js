/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var screening = {movieId: 0, auditoriumId: 0, startingDate: ""};
var movies = new Array();
var auditoriums = new Array();

var url = "http://localhost:8080/CineWorldCinemas/";

function reset() {
    screening = {movieId: 0, auditoriumId: 0, startingDate: ""};
}

function validate() {
    var error = false;
    $("#register-screening-start").removeClass("invalid");
    error |= $("#register-screening-start").filter((i, e) => {
        return e.value === '';
    }).length > 0;
    $("#register-screening-start").filter((i, e) => {
        return e.value === '';
    }).addClass("invalid");
    return !error;
}

function load() {
    let screeningAux = Object.fromEntries((new FormData($("#register-screening-form").get(0))).entries());
    screening = {movie: {id: screeningAux.movieId}, auditorium: {id: screeningAux.auditoriumId}, screeningStart: screeningAux.screeningStart};
    
}

function add() {
    load();
    if (!validate()) {
        return;
    }
    let request = new Request(url + 'api/register/screening', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(screening)});
    (async () => {
        console.log(screening);
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#register-screening-form #error"));
            return;
        }
        reset();
        render();
    })();
}

function render() {
    $("#register-screening-movie").val(screening.movieId);
    $("#register-screening-auditorium").val(screening.auditoriumId);
    $("#register-screening-start").val(screening.screeningStart);
}
function fetchAndListMovies() {
    let request = new Request(url + 'api/movies', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        movies = await response.json();
        moviesOptions = "<option selected>Select a Movie</option>";
        console.log(movies);
        movies.forEach((movie) => {
            console.log(moviesOptions);
            moviesOptions += "<option value='" + movie.id + "'>" + movie.title + "</option>";
        });
        $("#register-screening-movie").append(moviesOptions);
    })();
}
function fetchAndListAuditoriums() {
    let request = new Request(url + 'api/auditoriums', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        auditoriums = await response.json();
        audOptions = "<option selected>Select a Auditorium</option>";
        auditoriums.forEach((auditorium) => {
            audOptions += "<option value='" + auditorium.id + "'>" + auditorium.name + "</option>";
        });
        $("#register-screening-auditorium").append(audOptions);
    })();
}

function loaded() {
    $("#register-screening-button").click(add);
    fetchAndListAuditoriums();
    fetchAndListMovies();
}

$(loaded);  