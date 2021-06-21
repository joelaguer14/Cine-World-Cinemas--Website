/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var url = "http://localhost:8080/CineWorldCinemas/";
var list = new Array();
var user;
var screening;
var screeningList = new Array();

function fetchAndList() {
    let userJSON = sessionStorage.getItem("user");
    let user = JSON.parse(userJSON);
    let request = new Request(url + 'api/users/' + user.id, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status);
            return;
        }
        user = await response.json();
        list = user.ticketsList;
    })();
}

function rowContentDisplay() {
    var rowContent = $("#history-user-list");
    let ticketTxt = "";
    for (const t of list) {
        let screenings = screeningList.filter(s => {
            return s.id === t.seatsReservedList[0].screeningId;
        });
        seats = new Array();
        seats = t.seatsReservedList;
        screening = screenings[0];
        seatsNumbers = printSeats(t.seatsReservedList, screening.auditorium.seatsList);
        ticketTxt += "<li class='list-group-item'>" +
                "<div class='media align-items-lg-center flex-column flex-lg-row p-1'>" +
                "<div class='media-body order-2 order-lg-1 align-items-lg-center'>" +
                "<h5 class='mt-0 mb-1 font-weight-bold mb-2'> Ticket id: " + t.id + "</h5>" +
                "<p class='font-italic text-muted mb-0 small'>Function date: " + screening.screeningStart + "</p>" +
                "<p class='font-italic text-muted mb-0 small'>Seats: " + seatsNumbers + "</p>" +
                "<p class='font-italic text-muted mb-0 small'>Price: " + t.totalPrice + "$</p>" +
                "</div>" +
                "</li>";
    }
    rowContent.append(ticketTxt);
}

function printSeats(ticketSeatsList, auditoriumSeats) {
    var seatsTxt = "";
    for (const t of ticketSeatsList) {
        for (const s of auditoriumSeats) {
            for (const a of s.seatsReserved) {
                if (a.id === t.id) {
                    seatsTxt += " Number: " + s.number + " Row: " + s.row;
                    seatsTxt += " ";
                }
            }
        }
    }
    console.log(seatsTxt);
    return seatsTxt;
}

function fetchScreenings() {
    let request = new Request(url + 'api/screenings', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status);
            return;
        }
        screeningList = await response.json();
        rowContentDisplay();
    })();
}

function load() {
    fetchAndList();
    fetchScreenings();
}

$(load());