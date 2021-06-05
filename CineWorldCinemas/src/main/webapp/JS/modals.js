/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('#signup-link').on('click', function (e) {
    e.preventDefault();
    $('#register-modal').modal('show').find('.modal-content').load($(this).attr('href'));
});

$('#login-link').on('click', function (e) {
    e.preventDefault();
    $('#login-modal').modal('show').find('.modal-content').load($(this).attr('href'));
});