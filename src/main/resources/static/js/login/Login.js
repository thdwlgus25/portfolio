$(document).ready(function() {
    $('#signup').click(function() {
        console.log('Signup button clicked'); // 디버깅용
        $('.pinkbox').css('transform', 'translateX(80%)');
        $('.signin').addClass('nodisplay');
        $('.signup').removeClass('nodisplay');
    });

    $('#signin').click(function() {
        console.log('Signin button clicked'); // 디버깅용
        $('.pinkbox').css('transform', 'translateX(0%)');
        $('.signup').addClass('nodisplay');
        $('.signin').removeClass('nodisplay');
    });
});