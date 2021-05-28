/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var movies = [{
        title: "Spiderman",
        imagesrc: "/CineWorldCinemas/IMAGES/logo.png",
        description: "Spiderman!!"
    },
    {
        title: "Batman",
        imagesrc: "/CineWorldCinemas/IMAGES/logo.png",
        description: "Batman!!"
    },
    {
        title: "Superman",
        imagesrc: "/CineWorldCinemas/IMAGES/logo.png",
        description: "Superman!!"
    },
    {
        title: "Thor",
        imagesrc: "/CineWorldCinemas/IMAGES/logo.png",
        description: "Thor!!"
    }
];

var cont = 0;

function loaded() {
    listMovies(movies);
}

$(loaded);

function listMovies(movies) {
    var listado = $("#carousel-content");
    listado.html("");
    movies.forEach((movie) => {
        row(listado, movie);
    });
}

function row(listado, movie) {
    
    var indicator = $("#indicators");
    listado.append(
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
