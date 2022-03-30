function open_box() {
    $('#mypost').show()
}

function close_box() {
    $('#mypost').hide()
}

function isValidContents(content) {
    if (content == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    if (content.trim().length > 140) {
        alert('공백 포함 140자 이하로 입력해주세요');
        return false;
    }
    return true;
}

$(document).ready(function () {
    getMessages();
    close_box();
});

// 게시글 목록 조회
function getMessages() {
    // 1. 기존 게시글 내용을 지우기
    // $('#review-list').empty();
    // 2. 게시글 목록을 불러와서 HTML 로 붙이기
    $.ajax({
        type: 'GET',
        url: '/api/blogs',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let contentList = response[i];
                let id = contentList['id'];
                let username = contentList['username'];
                let title = contentList['title'];
                let content = contentList['content'];
                let modifiedAt = contentList['modifiedAt'];
                addHTML(id, username, title, content, modifiedAt);
            }
        }
    })
}


function addHTML(id, username, title, content, modifiedAt) {
    let tempHtml = `<tr onclick="detail('${id}')" style="cursor: pointer">
                                <th class="id">${id}</th>
                                <td>${title}</td>
                                <td class="username">${username}</td>
                                <td class="modifiedAt">${modifiedAt}</td>
                            </tr>`
    $('#tableList').append(tempHtml);
}

function detail(id){
    window.location.href = `/api/blogs/${id}`
}

// 게시글 저장
function save_review() {
    let id = $('#id').val()
    // let username = $('#username').val()
    let title = $('#title').val()
    let content = $('#content').val()
    if (isValidContents(content) == false) {
        return;
    }
    let data = {'id':id, 'title': title, 'content': content};
    $.ajax({
        type: 'POST',
        url: '/api/blogs',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('저장 성공!');
            window.location.reload();
        }
    })
}