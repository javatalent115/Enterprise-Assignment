function invalid_user() {
    if (localStorage["accountType"] !== "admin") {
        window.location.href = "404.html"
    }
}

invalid_user()

function view_customer_history(element) {
    localStorage.setItem("user", element)
}

function removeAllCustomer() {
    let x = document.querySelectorAll(".customer-item").length - 1
    for (let i = x; i > 0; i--) {
        document.querySelectorAll(".customer-item")[i].remove()
    }
}

function searchCustomerByUsername() {
    let keyword = document.querySelector("#customer2").value
    if (keyword.trim() === "") return
    getCustomerByUsername(keyword)
}

async function getCustomerByUsername(keyword) {
    removeAllCustomer()
    $(".spinner").css("display", "block")
    let wrapper = document.querySelector(".customers")
    try {
        let res = await fetch('http://localhost:8080/customer', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        });
        if (res.ok) {
            let data = await res.json();
            let result = Object.values(data);
            for (let i = 0; i < result.length; i++) {
                let username = result[i]["username"]
                if (username.includes(keyword)) username = username.replace(keyword, "<b>" + keyword + "</b>")
                else continue
                wrapper.innerHTML +=
                    `<ul class="customer-item">
            <li class="username" style="flex: 20%;"><a href="../history.html" id="${result[i]["username"]}" onclick="view_customer_history(this.id)">${username}</a></li>
            <li class="name" style="flex: 20%;">${result[i]["firstname"] + ", " + result[i]["lastname"]}</li>
            <li class="email" style="flex: 30%;">${result[i]["email"]}</li>
            <li class="last-login" style="flex: 30%;">${result[i]["lastLogin"]}</li>`
            }
        }
    } catch (e) {}
    $(".spinner").css("display", "none")
}

async function getAllCustomer() {
    removeAllCustomer()
    $(".spinner").css("display", "block")
    let wrapper = document.querySelector(".customers")
    try {
        let res = await fetch('http://localhost:8080/customer', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        });
        if (res.ok) {
            let data = await res.json();
            let result = Object.values(data);
            for (let i = 0; i < result.length; i++) {
                wrapper.innerHTML +=
                    `<ul class="customer-item">
            <li class="username" style="flex: 20%;"><a href="history.html" id="${result[i]["username"]}" onclick="view_customer_history(this.id)">${result[i]["username"]}</a></li>
            <li class="name" style="flex: 20%;">${result[i]["firstname"] + ", " + result[i]["lastname"]}</li>
            <li class="email" style="flex: 30%;">${result[i]["email"]}</li>
            <li class="last-login" style="flex: 30%;">${result[i]["lastLogin"]}</li>`
            }
        }
    } catch (e) {}
    $(".spinner").css("display", "none")
}

getAllCustomer()