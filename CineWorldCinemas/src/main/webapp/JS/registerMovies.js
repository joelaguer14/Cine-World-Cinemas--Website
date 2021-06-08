/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var movie={title:"", description:"", duration:0, price:0.0, image:""};

function loaded(){
    $('#save-movie-btn').off('click').on('click', load);
};
$(loaded);
function load(){
        movie = Object.fromEntries( (new FormData($("#formulario").get(0))).entries());  
        alert(movie);
    }
