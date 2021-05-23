function invalid_user() {
    if (localStorage["accountType"] === "admin") {
        window.location.href = "404.html"
    }
}

invalid_user()

function initialize() {
    let data;
    if (localStorage.getItem("cart-item")) {
        data = JSON.parse(localStorage.getItem("cart-item"))
    } else {
        data = {}
    }
    let wrapper = document.querySelector(".medicines")
    let totalMedicinePrice = 0;
    for (let i = 0; i < data.length; i++) {
        let totalPrice = data[i].price * data[i].amount
        wrapper.innerHTML += `
        <ul class="medicine-item">
            <li class="id">${data[i].id}</li>
            <li class="name">${data[i].name}</li>
            <li class="unitPrice">${data[i].price}$</li>
            <li class="amount">${data[i].amount}</li>
            <li class="totalPrice">${totalPrice}$</li>
        </ul>`
        totalMedicinePrice += totalPrice
    }
    wrapper.innerHTML += `<ul class="medicine-item">
    <li></li>
    <li></li>
    <li class = "text-nowrap totalMedicinePrice">Total price (${data.length} products)</li>
    <li></li>
    <li class = "totalMedicinePrice">${totalMedicinePrice}$</li>
</ul>`
}

$(document).ready(initialize())

$(document).ready(function() {
    $("#confirm-purchase-modal").on("show.bs.modal", function(event) {
        let data = JSON.parse(localStorage.getItem("cart-item"));
        let drug = {
            id: "",
            amount: ""
        };
        for (let i = 0; i < data.length; i++) {
            drug.id = data[i].id;
            drug.amount = data[i].amount;
            reduceStock(drug);
        }
        localStorage.setItem("cart-item", "")
        localStorage.setItem("cart", 0)
        setTimeout(() => {
            window.location.href = "http://localhost:8080/home-page.html"
        }, 1000);
    });
});

async function reduceStock(drug) {
    try {
        await fetch("http://localhost:8080/drug/reduceStock", {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(drug)
        });
    } catch (e) {}
}