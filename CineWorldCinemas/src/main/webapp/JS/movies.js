/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var movies = [{
        title: "Spiderman",
        imagesrc: "/CineWorldCinemas/IMAGES/banner-JOKER.jpg",
        description: "Spiderman!!"
    },
    {
        title: "Batman",
        imagesrc: "/CineWorldCinemas/IMAGES/banner-JOKER.jpg",
        description: "Batman!!"
    },
    {
        title: "Superman",
        imagesrc: "/CineWorldCinemas/IMAGES/banner-JOKER.jpg",
        description: "Superman!!"
    },
    {
        title: "Thor",
        imagesrc: "/CineWorldCinemas/IMAGES/banner-JOKER.jpg",
        description: "Thor!!"
    }
];

var cont = 0;

function loaded() {
    listMovies(movies);
}

$(loaded);

function listMovies(movies) {
    var carouselContent = $("#carousel-content");
    var rowContent = $("#row-content");
    carouselContent.html("");
    movies.forEach((movie) => {
        carouselContentDisplay(carouselContent, movie);
        rowContentDisplay(rowContent, movie);
    });
}

function carouselContentDisplay(carouselContent, movie) {
    var indicator = $("#indicators");
    carouselContent.append(
            "<div class='carousel-item active' id='" + movie.title + "'>" +
            "<div class='overlay-image' style='background-image:url(/CineWorldCinemas/IMAGES/banner-JOKER.jpg);'> </div>" +
            "<div class='container'>" +
            "<div class='carousel-caption text-start'>" +
            "<h1>" + movie.title + "</h1>" +
            "<p>" + movie.description + "</p>" +
            "</div>" +
            "</div>" +
            "</div>"
            );
    if ($("#indicators").is(':empty')) {
        indicator.append("<button type='button' data-bs-target='#myCarousel' data-bs-slide-to='" + cont + "' class='active' aria-current='true' aria-label='Slide " + cont + "'></button>");
    } else {
        indicator.append("<button type='button' data-bs-target='#myCarousel' data-bs-slide-to='" + cont + "' aria-label='Slide " + cont + "'></button>");
        $("#" + movie.title).removeClass("active");
    }
    cont++;
}

function rowContentDisplay(rowContent, movie) {
    rowContent.append(
            "<div class='col-lg-4 py-3'>" +
            "<img src='/CineWorldCinemas/IMAGES/banner-JOKER.jpg' class='image-grid' alt=''>" +
            "<h2>" + movie.title + "</h2>" +
            "<p>" + movie.description + "</p>" +
            "<p><a class='btn btn-secondary' href='#'>View details &raquo;</a></p>" +
            "</div>"
            );
}