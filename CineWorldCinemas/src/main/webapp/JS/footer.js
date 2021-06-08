/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var footer = "<footer class='text-muted py-5'>" +
                "<div class='container'>" +
                    "<p class='float-end mb-1'>" +
                        "<a class='footer-link' href='#'>Back to top</a>" +
                    "</p>" +
                    "<p class='footer-text mb-1'>&copy; 2021 Copyright: Brute Force Technologies</p>" +
                "</div>" +
            "</footer>";

function loadFooter() {
    $("body").append(footer);
}

$(loadFooter);


