/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var movies = [
    {
        title: "Spiderman",
        imagesrc: "https://pauladeveraescritora.files.wordpress.com/2019/10/spider-man-far-from-home-banner.jpg",
        description: "Parker is recruited by Nick Fury and Mysterio to face the Elementals while he is on a school trip to Europe."
    },
    {
        title: "Batman",
        imagesrc: "http://splashreport.com/wp-content/uploads/2017/01/legobatman-header.png",
        description: "In the Lego Universe, Batman continues to protect Gotham City and fight crime. During his latest mission to stop Joker from destroying the city, he hurts his arch-rival's feelings by telling him he is not as important in his life as he thinks he is, leading Joker to seek the ultimate revenge on him.In the Lego Universe, Batman continues to protect Gotham City and fight crime. During his latest mission to stop Joker from destroying the city, he hurts his arch-rival's feelings by telling him he is not as important in his life as he thinks he is, leading Joker to seek the ultimate revenge on him"
    },
    {
        title: "Superman",
        imagesrc: "https://collider.com/wp-content/uploads/man-of-steel-poster-banner.jpg",
        description: "Set in 1981, it follows Arthur Fleck, a failed clown and stand-up comedian whose descent into insanity and nihilism inspires a violent counter-cultural revolution against the wealthy in a decaying Gotham City."
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
            "<div class='overlay-image' style='background-image:url(" + movie.imagesrc + "); background-position: center center;'> </div>" +
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
        $("#" + movie.title).removeClass("active");
    }
    cont++;
}

function rowContentDisplay(rowContent, movie) {
    rowContent.append(
            "<div class='col'>" +
            "<div class='card shadow-sm my-card'>" +
            "<img class='image-grid d-block w-100' src='" + movie.imagesrc + "' alt=''>" +
            "<div class='card-body'>" +
            "<div class='d-flex justify-content-between align-items-center'>" +
            "<div class='btn-group'>" +
            "<button type='button' class='btn btn-sm btn-outline-secondary'>View</button>" +
            "<button type='button' class='btn btn-sm btn-outline-secondary'>Edit</button>" +
            "</div>" +
            "<small class='text-muted'>9 mins</small>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>"
            );
}