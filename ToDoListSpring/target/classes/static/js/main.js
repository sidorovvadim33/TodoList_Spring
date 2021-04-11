$(function () {
    // const appendTask = function(data) {
    //     if (data.status === 'CREATED' && new Date() < new Date(data.date)) {
    //         var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
    //             ' <a href="#" class="in-progress-link">In Progress</a> <a href="#" class="canceled-link">Cancel</a><br>'
    //             + 'Task description: ' + data.description;
    //         $('#task-list-created').append('<div id="' + data.id + '">' + task + "</div>");
    //     }
    //     if (data.status === 'IN_PROGRESS' && new Date() < new Date(data.date)) {
    //         var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
    //             ' <a href="#" class="completed-link">Complete</a>  <a href="#" class="canceled-link">Cancel</a><br>'
    //             + 'Task description: ' + data.description;
    //         $('#task-list-in-progress').append('<div id="' + data.id + '">' + task + "</div>");
    //     }
    //     if (data.status === 'CANCELED') {
    //         var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
    //             ' <a href="#" class="in-progress-link">In Progress</a>  <a href="#" class="deleted-link">Delete</a><br>'
    //             + 'Task description: ' + data.description;
    //         $('#task-list-canceled').append('<div id="'     + data.id + '">' + task + "</div>");
    //     }
    //     if (data.status === 'CLOSED') {
    //         var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
    //             ' <a href="#" class="deleted-link">Delete</a><br>'
    //             + 'Task description: ' + data.description;
    //         $('#task-list-closed').append('<div id="' + data.id + '">' + task + "</div>");
    //     }
    //     if (data.status === 'COMPLETED') {
    //         var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
    //             ' <a href="#" class="closed-link">Close</a><br>'
    //             + 'Task description: ' + data.description;
    //         $('#task-list-completed').append('<div id="' + data.id + '">' + task + "</div>");
    //     }
    //     if (data.status === 'DELETED') {
    //         var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
    //             ' <a href="#" class="del-link">Out of history (forever)!!!</a><br>'
    //             + 'Task description: ' + data.description;
    //         $('#task-list-deleted').append('<div id="' + data.id + '">' + task + "</div>");
    //     }
    //     if (data.status === 'EXPIRED') {
    //         var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
    //             ' <a href="#" class="del-link">Out of history (forever)!!!</a><br>'
    //             + 'Task description: ' + data.description;
    //         $('#task-list-expired').append('<div id="' + data.id + '">' + task + "</div>");
    //     }
    //     if (new Date() > new Date(data.date)) {
    //         if ((data.status === 'CREATED') || (data.status === 'IN_PROGRESS')) {
    //             var task = '<b>' + data.date + ' EXPIRED ' + ':</b> ' + data.name +
    //                 ' <a href="#" class="del-link">Out of history (forever)!!!</a><br>'
    //                 + 'Task description: ' + data.description;
    //             $('#task-list-expired').append('<div id="' + data.id + '">' + task + "</div>");
    //         }
    //     }
    // };

    //Get taskList
    // $.get('/tasks/', function(response){
    //     for (i in response) {
    //         appendTask(response[i]);
    //     }
    // });

    //Show adding task form
    $('#show-add-task-form').click(function () {
        $('#add-task-form').css('display', 'flex');
    });

    //Closing adding task form
    $('#add-task-form').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Create task
    $('#save-task').click(function () {
        var data = $('#add-task-form form').serialize();
        var url;
        var statData;
        if (new Date() > new Date(data.date)) {
            // data.status = 'EXPIRED';
            statData = 'EXPIRED';
            url = '/tasksExpired/';
        } else {
            // data.status = 'CREATED';
            statData = 'CREATED';
            url = '/tasks/';
        }
        $.ajax({
            method: "POST",
            url: url,
            data: data + "&status=" + statData,
            success: function () {
                setTimeout(windowReload, 1000);
            }
        });
    });

    //in progress task
    $(document).on('click', '.in-progress-link', function () {
        var id = $(this).parent().attr("id");
        var status = 'IN_PROGRESS';
        $.ajax({
            method: "PUT",
            url: "/taskInProgress/" + id + "/" + status,
            success: function() {
                setTimeout(windowReload, 1000);
            }
        });
        // var elem = document.getElementById(id).getAttribute("b").value;
        // alert("Элемент " + elem);
        // if () {
        //
        // }
        // var element = $(this).text();
        // alert(element.getText());
        // element.set
        // window.location.reload();
    });

    //canceled task
    $(document).on('click', '.canceled-link', function () {
        var id = $(this).parent().attr("id");
        var status = 'CANCELED';
        $.ajax({
            method: "PUT",
            url: "/taskCanceled/" + id + "/" + status,
            success: function() {
                setTimeout(windowReload, 1000);
            }
        });
        // window.location.reload();
    });

    //closed task
    $(document).on('click', '.closed-link', function () {
        var id = $(this).parent().attr("id");
        var status = 'CLOSED';
        $.ajax({
            method: "PUT",
            url: "/taskClosed/" + id + "/" + status,
            success: function() {
                setTimeout(windowReload, 1000);
            }
        });
        // window.location.reload();
    });

    //completed task
    $(document).on('click', '.completed-link', function () {
        var id = $(this).parent().attr("id");
        var status = 'COMPLETED';
        $.ajax({
            method: "PUT",
            url: "/taskCompleted/" + id + "/" + status,
            success: function() {
                setTimeout(windowReload, 1000);
            }
        });
        // window.location.reload();
    });

    //deleted task
    $(document).on('click', '.deleted-link', function () {
        var id = $(this).parent().attr("id");
        var status = 'DELETED';
        $.ajax({
            method: "PUT",
            url: "/taskDeleted/" + id + "/" + status,
            success: function() {
                setTimeout(windowReload, 1000);
            }
        });
        // window.location.reload();
    });

    //delete forever task
    $(document).on('click', '.del-link', function () {
        var id = $(this).parent().attr("id");
        $.ajax({
            method: "DELETE",
            url: "/tasks/" + id,
            success: function() {
                setTimeout(windowReload, 1000);
            }
        });
        // window.location.reload();
    });

    //Deleting all tasks
    $('#delete-all-tasks').click(function () {
        $.ajax({
            method: "DELETE",
            url: "/tasks/"
        });
        setTimeout(windowReload, 1000);
    });
    
    function windowReload() {
        window.location.reload();
    }
});