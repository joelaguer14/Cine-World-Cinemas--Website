/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var url = "http://localhost:8080/CineWorldCinemas/";
var list = new Array();
var ticket = {};
var screening;
var screeningList = new Array();

function fetchAndListTickets(id) {
    let request = new Request(url + 'api/tickets/' + id, {method: 'GET', headers: {}});
    let requestScreening = new Request(url + 'api/screenings', {method: 'GET', headers: {}});
    (async () => {
        //ticket by id
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status);
            return;
        }
        ticket = await response.json();
        //Screenings
        const responseScreening = await fetch(requestScreening);
        if (!responseScreening.ok) {
            errorMessage(responseScreening.status);
            return;
        }
        screenings = await responseScreening.json();
        screenings.forEach((s) => {
            if (s.id === ticket.seatsReservedList[0].screeningId) {
                screening = s;
            }
        });
        ticket.screening = screening;
        createPdf(ticket.id, ticket);
    })();
}

function createPdf(id, ticket) {

    var doc = new jsPDF();
    doc.text(20, 20, 'Ticket id: ' + id);
    doc.text(20, 30, 'Auditorium: ' + ticket.screening.auditorium.name);
    doc.text(20, 40, 'Screening date: ' + ticket.screening.screeningStart);
    doc.text(20, 50, 'Price: $' + ticket.totalPrice);
    doc.text(20, 60, 'Credit card number: ' + ticket.creditCard);
    if (!(sessionStorage.getItem("user"))) {
        doc.text(20, 80, 'Full Name: ' + $("#register-fullname-payment").val());
        doc.text(20, 90, 'E-Mail: ' + $("#register-email-payment").val());
    }

    doc.save('Ticket' + id + '.pdf');
}

function load() {
    console.log($("#search-tickets-button"));
    $("#search-tickets-button").click(function () {
        console.log($("#search-tickets-input").val());
        fetchAndListTickets($("#search-tickets-input").val());
    });
}

$(load);
