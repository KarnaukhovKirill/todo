$('#registration').submit(function (e) {
    e.preventDefault();
    var $email = $('#inputRegEmail');
    var $password = $('#inputRegPassword');
    var $name = $('#inputRegName');
    $.ajax({
        type: "POST",
        url: "./reg.do",
        data: jQuery.param({ email: $email.val(), password : $password.val(), name : $name.val()}),
        dataType: 'json'
    }).done(function (data) {
        alert(data);
        $email.val('');
        $password.val('');
        $name.val('');
    }).fail(function () {
        window.location.href = './index.html';
    })
})