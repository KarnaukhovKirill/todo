$(startReload());
var showAllTaskNode = document.getElementById('showAllTask');

$('#showAllTask').on('click', function () {
    startReload();
});
$('#taskForm').submit(function (e) {
    var $inputTask = $('#inputTask');
    alert("Handler for .submit called");

    alert($inputTask.val());
    $.ajax({
        type: 'POST',
        url: './createTask.do',
        data: jQuery.param({ description: $inputTask.val()}),
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        dataType: 'json'
    }).done(function (data) {
        var $taskTable = $('#taskTableBody');
        var taskHtml = createTaskHtml(data.done, data);
        $taskTable.prepend(taskHtml);

    })
    $inputTask.val('');
    e.preventDefault();
});

function startReload() {
    var $taskTable = $('#taskTableBody');
    $taskTable.html('');
    $.ajax({
        type: 'GET',
        url: './loadTasks.do',
        dataType: 'json'
    }).done(function (data) {

        for (var i = 0; i < data.length; i++) {
            var done = data[i].done;
            if (done && !showAllTaskNode.checked) {
                continue;
            }
            var taskHtml = createTaskHtml(done, data[i]);
            $taskTable.append(taskHtml);
        }
    }).fail(function (err) {
        console.log(err);
    })
}

function updateTask(id, done) {
    alert(id);
    $.ajax({
        type: "POST",
        url: "./updateTask.do",
        data: jQuery.param({ taskId: id, taskDone : done}),
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
    }).done(function (data) {
        alert("update done");
        startReload();
    }).fail(function (err) {
        console.log(err);
    })
}

function createTaskHtml(done, data) {
    var $newTr = $('<tr>');
    var $firstTd = $('<td>');
    $firstTd.prepend(data.description);
    var $secondTD = $('<td>');
    $secondTD.prepend(data.created);
    var $thirdTd = $('<td>');
    var $div = $('<div align=\"center\"></div>');
    if (done) {
        $div.prepend('<input class=\"form-check-input\" type=\"checkbox\" value=\"' + data.id + '\" onclick=\"updateTask(' + data.id + ', 1' + ')\" checked/>');
    } else {
        $div.prepend('<input class=\"form-check-input\" type=\"checkbox\" value=\"' + data.id + '\" onclick=\"updateTask(' + data.id + ', 0' + ')\"/>');
    }
    $thirdTd.prepend($div);
    $newTr.append($firstTd);
    $newTr.append($secondTD);
    $newTr.append($thirdTd);
    return $newTr;
}

