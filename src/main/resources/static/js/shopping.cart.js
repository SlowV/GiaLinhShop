var btn_add_to_cart;
// const BASE_URL = "http://localhost:1998";
const BASE_URL = "https://gialinh.herokuapp.com/";
const API_CART = BASE_URL + '/cart';
$(document).ready(function () {
    start();
});

function start() {
    btn_add_to_cart = $('.add-to-cart');
    onClick();
}

function onClick() {
    btn_add_to_cart.click(function () {
        addToCart($(this).attr('id'));
    })
}

function addToCart(id) {
    var statusCode = 404;
    $.ajax({
        url: API_CART + '/add/' + id,
        method: 'POST',
        contentType: 'application/json',
        success: function (data, textStatus, xhr) {
            console.log('STATUS: ' + xhr.status);
            console.log("========================")
            console.log('Message: ' + data);
        },
        error: function (msg) {
            console.log('Message errors: ' + msg);
        }
    });
    return statusCode;
}