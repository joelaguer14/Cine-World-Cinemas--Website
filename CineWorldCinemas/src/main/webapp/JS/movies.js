var cont = 0;
var url = "http://localhost:8080/CineWorldCinemas/";
var movies = new Array();
var screnning = {};
var movieMatched = {};

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
    movie.screeningsList.forEach((s) => {
        screeningsTxt += "<a id='ticket-link-" + s.id + "' data-bs-toggle='modal' data-bs-target='#ticket-modal' class='screening-link'>" + s.screeningStart.split("T")[0] +
                ", " + s.screeningStart.split("T")[1].split("Z")[0] + ", Auditorium: " + s.auditorium.name + "</a>";
    });
    rowContent.append(
            "<div class='col'>" +
            "<div class='card my-card'>" +
            "<img class='image-grid d-block w-100' src='" + url + "api/movies/" + movie.title + "/image' alt=''>" +
            "<div class='card-body'>" +
            "<div class='screenings text-center'>" + screeningsTxt + "</div>" +
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
        movie.screeningsList.forEach((s) => {
            $('#ticket-link-' + s.id).click(function () {
                renderTicketModal(s.id);
            });
        });
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
        movies = movies.filter((m) => m.screeningsList.length > 0);
        listMovies();
    })();
}

//=========================================================================================================================================================================

function reset() {
    movieEntries = {title: "", description: "", duration: 0, price: 0};
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
    movieEntries = Object.fromEntries((new FormData($("#register-movie-form").get(0))).entries());
}

function addImage() {
    var imageData = new FormData();
    imageData.append("title", movieEntries.title);
    imageData.append("image", $("#register-movie-image").get(0).files[0]);
    let request = new Request(url + 'api/movies/' + movieEntries.title + "/image", {method: 'POST', body: imageData});
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
    let request = new Request(url + 'api/movies/register', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(movieEntries)});
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

//Ticket Modal
function renderTicketModal(id) {
    let request = new Request(url + 'api/movies/screening/' + id, {method: 'GET', headers: {}});
    let requestMovie = new Request(url + 'api/movies', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        screening = await response.json();
        const responseMovie = await fetch(requestMovie);
        if (!responseMovie.ok) {
            errorMessage(responseMovie.status, $("#buscarDiv #errorDiv"));
            return;
        }
        movies = await responseMovie.json();
        console.log(movies);
        renderScreening();
    })();
}

function renderScreening() {
    movies.forEach((m) => {
        m.screeningsList.forEach((s) => {
            if (s.id === screening.id) {
                movieMatched = m;
            }
        });
    });
    if ($("#ticket-title").children().length > 0) {
        $("#h6-screening-title").remove();
    }
    if ($("#ticket-modal-body").children().length > 0) {
        $("#ticket-modal-body-child").remove();
        $("#ticket-modal-body-child-seats").remove();
        $("#modal-ticket-price").remove();
    }
    if ($("#modal-ticket-price").children().length > 0) {
        $("#p-modal-price").remove();
    }


    $("#ticket-title").prepend(
            '<h6 id="h6-screening-title" class="modal-title fs-5">' + movieMatched.title + " - " +
            screening.screeningStart.split("T")[0] + "," + screening.screeningStart.split("T")[1].split("Z")[0] +
            ", Auditorium: " + screening.auditorium.name + '</h6>');
    $("#ticket-modal-body").append("<div id='ticket-modal-body-child'></div>");
    $("#ticket-modal-body-child").append("<div class = 'd-block' id='image-ticket-modal-div'><img class='image-grid" +
            " d-block w-100'  src='" + url + "api/movies/" + movieMatched.title + "/image' alt='' id='image-ticket-modal'></div>"
            + "<p class='text-center'>Price: $" + movieMatched.price + "</p>");
    $('#ticket-modal-body').append("<div id='ticket-modal-body-child-seats'>" +
            "<p class='text-center'>Seats</p>" +
            "<ul class='showcase'>" +
            "<li>" +
            "<div id='seat' class='seat'></div>" +
            "<small class='status' style='font-size: 1em;'>N/A</small>" +
            "</li>" +
            "<li>" +
            "<div id='seat' class='seat selected'></div>" +
            "<small class='status' style='font-size: 1em;'>Selected</small>" +
            "</li>" +
            "<li>" +
            "<div id='seat' class='seat occupied'></div>" +
            "<small class='status' style='font-size: 1em;'>Occupied</small>" +
            "</li>" +
            "</ul>" +
            "<div class='container-fluid text-center'>" +
            "<div class='screen'></div>" +
            mapSeats() +
            "</div>" +
            "</div>" +
            "<div id='modal-ticket-price'>" +
            "<p id='p-modal-price' class='text text-center' style='font-size: 1em; margin:0px 0px 15px 0px'>" +
            "You have selected <span id='count'> 0 </span> seats for a price of $" +
            "<span id='total'> 0 </span>" +
            "</p>" +
            "</div>");


    calculateTotal(movieMatched.price);
}

function mapSeats() {
    var seatsHtml = "";
    let seatsNumber = screening.auditorium.seatsNumber;
    let rows = seatsNumber/10;
    console.log(rows);
    for (let i = 0; i < rows; i++) {
         seatsHtml += "<div class='row row-seat'>";
        for (let j = 0; j < 10; j++) {         
          seatsHtml += "<div id='seat" +screening.auditorium.seatsList[i*10+j].id+"' class='seat col-md-3'></div>";                    
        }
        seatsHtml += "</div>";
    }

    return seatsHtml;
}

function calculateTotal(price) {
    var count = 0;
    var seats = document.getElementsByClassName("seat");
    for (var i = 0; i < seats.length; i++) {
        var item = seats[i];
        item.addEventListener("click", (event) => {
            if (!event.target.classList.contains('occupied') && !event.target.classList.contains('selected')) {
                count++;
                console.log(count);
                var total = count * price;
                event.target.classList.add("selected");
                document.getElementById("count").innerText = count;
                document.getElementById("total").innerText = total;

            }
        });
    }
}

function addTicket() {
    loadTicket();
    if (!validate()) {
        console.log("validate");
        return;
    }
    let request = new Request(url + 'api/register', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(user)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#register-modal #error"));
            return;
        }
        reset();
        $('#register-modal').modal('hide');
    })();

}

function loaded() {
    fetchAndList();
    $("#register-movie-button").click(add);
}

$(loaded);
