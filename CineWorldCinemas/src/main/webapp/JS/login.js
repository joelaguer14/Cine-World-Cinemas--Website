/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$('.li-modal').on('click', function (e) {
    e.preventDefault();
    $('#login-modal').modal('show').find('.modal-content').load($(this).attr('href'));
});