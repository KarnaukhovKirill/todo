$(document).ready(function () {
    startReload();
    loadUser();
    loadCategories();
})
$('#navBar').load("nav.html");
var showAllTaskNode = document.getElementById('showAllTask');

$('#showAllTask').on('click', function () {
    startReload();
});

$('#taskForm').submit(function (e) {
    var $inputTask = $('#inputTask');
    var $selectedCat = $('#selectCategory').val();
    var categories = '';
    for (var i = 0; i < $selectedCat.length; i++) {
        categories += $selectedCat[i] + ',';
    }
    $.ajax({
        type: 'POST',
        url: './createTask.do',
        data: jQuery.param({ description: $inputTask.val(), categories: categories}),
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        dataType: 'json'
    }).always(function () {
        startReload();
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

function loadUser() {
    $.ajax({
        type: "get",
        url: "./loadUser.do",
        dataType: "json"
    }).done(function (user) {
        var username = user.name;
        $('#firstLink').text(username + ' | Выйти');
    }).fail(function () {
        console.log('ERROR LOAD USERNAME')
    });
}

function loadCategories() {
    $.ajax({
        type: 'GET',
        url: './loadCategories.do',
        dataType: 'json'
    }).done(function (data) {
        var $selectCategory = $('#selectCategory');
        for (var i = 0; i < data.length; i++) {
            var $newTr = $('<option>');
            $newTr.val(data[i].id);
            $newTr.text(data[i].name);
            $selectCategory.append($newTr);
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
        startReload();
    }).fail(function (err) {
        console.log(err);
    })
}

function logout() {
    $.ajax({
        type: "get",
        url: "./logout.do"
    }).always(function () {
        window.location.href = './login.html';
    })
}

function createTaskHtml(done, data) {
    var $newTr = $('<tr>');
    var $descriptionTd = $('<td>');
    $descriptionTd.prepend(data.description);
    var $createdTd = $('<td>');
    $createdTd.prepend(data.created);
    var $authorTd = $('<td>');
    $authorTd.prepend(data.user.name);
    var $done = $('<td>');
    var $div = $('<div align=\"center\"></div>');
    if (done) {
        $div.prepend('<input class=\"form-check-input\" type=\"checkbox\" value=\"' + data.id + '\" onclick=\"updateTask(' + data.id + ', true' + ')\" checked/>');
    } else {
        $div.prepend('<input class=\"form-check-input\" type=\"checkbox\" value=\"' + data.id + '\" onclick=\"updateTask(' + data.id + ', false' + ')\"/>');
    }
    var $category = $('<td>');
    for (var i = 0; i < data.categories.length; i++) {
        $category.prepend(data.categories[i].name + "<br>");
    }
    $done.prepend($div);
    $newTr.append($descriptionTd);
    $newTr.append($createdTd);
    $newTr.append($authorTd);
    $newTr.append($done);
    $newTr.append($category);
    return $newTr;
}

