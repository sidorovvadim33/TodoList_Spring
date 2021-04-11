$(function () {
    $('#save-user').click(function () {
        var data = $('#new-user-reg-form form').serialize();
        $.ajax({
            method: "POST",
            data: data,
            success: function () {
                setTimeout(windowReload, 1000);
                window.location.href = "/login.html";
            }
        });
    });


    function windowReload() {
        window.location.reload();
    }
});