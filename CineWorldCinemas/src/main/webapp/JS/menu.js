/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var menu = "<header class='header'>" +
        "<div class='header-inner px-lg-5 '>" +
        "<div class='container-fluid'>" +
        "<nav class='navbar fixed-top navbar-expand-lg navbar-dark my-navbar'>" +
        "<div class='container-fluid'>" +
        "<a class='navbar-brand' href='http://localhost:8080/CineWorldCinemas/'>Cine World Cinemas</a>" +
        "<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarSupportedContent' aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'>" +
        "<span class='navbar-toggler-icon'></span>" +
        "</button>" +
        "<div class='collapse navbar-collapse' id='navbarSupportedContent'>" +
        "<ul class='navbar-nav me-auto mb-2 mb-lg-0'>" +
        "<li class='nav-item'>" +
        "<a class='nav-link' href='http://localhost:8080/CineWorldCinemas/'>Home</a>" +
        "</li>" +
        "<li class='nav-item'>" +
        "<a class='nav-link' href='http://localhost:8080/CineWorldCinemas/'>Contact Us</a>" +
        "</li>";

let userJSON = sessionStorage.getItem("user");

if (userJSON !== null) {
    let user = JSON.parse(userJSON);

    if (user.isAdmin === false) {
        menu += "<li class='nav-item'> <a class='nav-link' href='/CineWorldCinemas/presentation/History.html'>History</a> </li>";
    } else {
        menu += "<li class='nav-item dropdown'>" +
                "<a class='nav-link dropdown-toggle' href='#' id='navbarDropdownMenuLink' role='button' data-bs-toggle='dropdown' aria-expanded='false'>" +
                "Add new..." +
                "</a>" +
                "<ul class='dropdown-menu' aria-labelledby='navbarDropdownMenuLink'>" +
                "<li><a class='dropdown-item' href='/CineWorldCinemas/presentation/admin/RegisterMovies.html'>Movie</a></li>" +
                "<li><a class='dropdown-item' href='/CineWorldCinemas/presentation/admin/RegisterAuditorium.html'>Auditorium</a></li>" +
                "<li><a class='dropdown-item' href='/CineWorldCinemas/presentation/admin/RegisterScreenings.html'>Screenings</a></li>" +
                "</ul>" +
                "</li>";
    }

    menu += "<li class='nav-item'>" +
            "<a class='nav-link' id='logout-link' href='http://localhost:8080/CineWorldCinemas/'>Log out [" + user.name + "]</a>" +
            "</li>";

} else {
    menu += "<li class='nav-item'>" +
            "<a class='nav-link' data-bs-toggle='modal' data-bs-target='#register-modal' href='#'>Register</a>" +
            "</li>" +
            "<li class='nav-item'>" +
            "<a class='nav-link' href='#' data-bs-toggle='modal' data-bs-target='#login-modal'>Log in</a>" +
            "</li>";
}

menu +=
        "</ul>" +
        "<form class='d-flex'>" +
        "<input id='search-input' class='form-control me-2' type='search' placeholder='Search movies' aria-label='Search'>" +
        "<button id='search-button' class='btn btn-outline-light' type='button'>Search</button>" +
        "</form>" +
        "</div>" +
        "</div>" +
        "</nav>" +
        "</div>" +
        "</div>" +
        "</header>";

function loadMenu() {
    $("body").prepend(menu);
}

$(loadMenu);