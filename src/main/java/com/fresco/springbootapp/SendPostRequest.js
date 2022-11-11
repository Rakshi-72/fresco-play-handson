function sendJSON() {
    var data = {
        userName: "Rakshith",
        password: 123
    };

    var json = JSON.stringify(data);

    var XMLHttpRequest = require('xhr2');
    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/users");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(json);
}

sendJSON()