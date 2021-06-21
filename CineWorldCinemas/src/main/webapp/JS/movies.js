var cont = 0;
var url = "http://localhost:8080/CineWorldCinemas/";
var movies = new Array();
var screnning = {};
var movieMatched = {};
var ticket = {};
var totalPrice = 0;
var selectedSeats = new Array();
var DBTicket;

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
    let request = new Request(url + 'api/register/movie', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(movieEntries)});
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
    let rows = seatsNumber / 10;
    for (let i = 0; i < rows; i++) {
        seatsHtml += "<div class='row row-seat'>";
        for (let j = 0; j < 10; j++) {
            let occupied = "";
            let reservedSeats = screening.auditorium.seatsList[i * 10 + j].seatsReserved;
            reservedSeats.forEach((seat) => {
                if (seat.screeningId === screening.id) {
                    occupied = "occupied";
                }
            });

            seatsHtml += "<div id='seat" + screening.auditorium.seatsList[i * 10 + j].id + "' class='seat " + occupied + " col-md-3'></div>";
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
                var total = count * price;
                event.target.classList.add("selected");
                event.target.classList.add("selectedSeat");
                document.getElementById("count").innerText = count;
                document.getElementById("total").innerText = total;
                totalPrice = total;
            } else if (event.target.classList.contains('selected')) {
                event.target.classList.remove("selected");
                event.target.classList.remove("selectedSeat");
                count--;
                var total = count * price;
                document.getElementById("count").innerText = count;
                document.getElementById("total").innerText = total;
                totalPrice = total;
            }
        });
    }
}

function addTicket() {
//    console.log("addTicket");
    loadTicket();
//    console.log(ticket);
//    if (!validateTicket()) {
//        console.log("validate");
//        return;
//    }
    let request = new Request(url + 'api/register/ticket', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(ticket)});
    let requestGetTicket = new Request(url + 'api/tickets/last', {method: 'GET', headers: {}});

    (async () => {

        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#ticket-modal #error"));
            return;
        }

        //------get Ticket from DB for its id------
        const responseGetTicket = await fetch(requestGetTicket);
        if (!responseGetTicket.ok) {
            errorMessage(responseGetTicket.status, $("#ticket-modal #error"));
            return;
        }
        DBTicket = await responseGetTicket.json();

        //------- POST each seatReserved with its ticket-----
        createReservedSeats();
        for (const seatR of selectedSeats) {
            let requestPostSeat = new Request(url + 'api/register/seatReserved', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(seatR)});
            const responsePostSeat = await fetch(requestPostSeat);
            if (!responsePostSeat.ok) {
                errorMessage(responsePostSeat.status, $("#ticket-modal #error"));
                return;
            }
        }
        resetTicket();
        $('#ticket-modal').modal('hide');


        //------get Ticket from DB------
        const responseGetTicketDB = await fetch(requestGetTicket);
        if (!responseGetTicketDB.ok) {
            errorMessage(responseGetTicketDB.status, $("#payment-modal-body #error"));
            return;
        }
        DBTicket = await responseGetTicketDB.json();

        if (!(sessionStorage.getItem("user"))) {
            if ($("#payment-modal-body").children().length > 2) {
                $(".anonymous").remove();
            }
            $('#payment-modal-body').append("<div class='form-input mb-3'>" +
                    "<span><i class='fa fa-user anonymous' aria-hidden='true'></i></span>" +
                    "<input class='register-input anonymous' id='register-fullname-payment' type='text' name='name' placeholder='Full name' tabindex='10'>" +
                    "</div>" +
                    "<div class='form-input mb-3 anonymous'>" +
                    "<span><i class='fa fa-envelope anonymous' aria-hidden='true'></i></span>" +
                    "<input class='register-input anonymous' id='register-email-payment' type='email' name='email' placeholder='Email' tabindex='10'>" +
                    "</div>"
                    );
        }
        $('#payment-modal').prepend("<div class='fs-5'>" +
                "Total amount: " + DBTicket.totalPrice + "</div>");
        $('#payment-modal').modal('show');
        console.log(ticket);
//        createPdf(DBTicket.id, ticket);
    })();
}
function loadTicket() {
    let userJSON = sessionStorage.getItem("user");
    let user = JSON.parse(userJSON);

    ticket.user = user;
    ticket.screening = screening;
    ticket.totalPrice = totalPrice;
}

function makePurchase() {
    DBTicket.creditCard = $("#payment-card").val();

    (async () => {
        console.log(DBTicket);
        let request = new Request(url + 'api/tickets/creditCard', {method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(DBTicket)});
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#payment-modal #error"));
            return;
        }
        DBTicket.screening = ticket.screening;
        createPdf(DBTicket.id, DBTicket);
        resetPayment();

    })();
}
function resetPayment() {
    $("#payment-card").val("");
    $("#register-fullname-payment").val("");
    $("#register-fullname-payment").val("");
    DBTicket = {user: {}, creditCard: "", screening: {screeningStart: ""}, totalPrice: 0.0};
}


function createReservedSeats() {

    $('.selectedSeat').each(function () {
        selectedSeats.push({seat: {id: $(this).attr('id')}, screeningId: screening.id, ticket: DBTicket});
    });

    for (const sel of selectedSeats) {
        sel.seat.id = sel.seat.id.split("t")[1];
    }
}

function resetTicket() {
    //resetea los valores del ticket
    totalPrice = 0;
    selectedSeats = [];
}
function createPdf(id, ticket) {
    console.log(ticket);
    var doc = new jsPDF();
    doc.text(20, 20, 'Ticket id: ' + id);
    doc.text(20, 30, 'Auditorium: ' + ticket.screening.auditorium.name);
    doc.text(20, 40, 'Screening date: ' + ticket.screening.screeningStart);
    doc.text(20, 50, 'Price: $' + ticket.totalPrice);
    doc.text(20, 50, 'Credit card number: ' + ticket.creditCard);
    if (!(sessionStorage.getItem("user"))) {
        doc.text(20, 50, 'Full Name: ' + $("#register-fullname-payment").val());
        doc.text(20, 50, 'E-Mail: ' + $("#register-fullname-payment").val());
    }
    doc.save('Ticket' + id + '.pdf');
}

//function validateTicket(){
//    //valida si hay asientos seleccionados
//}


function errorMessage(status, place) {
    switch (status) {
        case 404:
            error = "User not found";
            break;
        case 403:
        case 405:
            error = "Unauthorized user";
            break;
        case 406:
        case 405:
            error = "User already exists";
            break;
        default:
            error = "Error: " + status;
            break;
    }
    ;

    place.html("<div class='alert alert-danger alert-dismissible fade show' role='alert'>" +
            "<strong>Error!</strong> " + error + " " +
            "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>" +
            "</div>");
    return;
}

function loaded() {
    fetchAndList();
    $("#register-movie-button").click(add);
    $("#purchase-ticket-button").click(addTicket);
    $("#purchase-payment-button").click(makePurchase);
}

$(loaded);