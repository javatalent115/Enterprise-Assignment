function showOrderDetail(element) {
    if (element.classList.contains("collapse-rotate")) {
        element.classList.remove("collapse-rotate")
        element.classList.add("expand-rotate")
    } else if (element.classList.contains("expand-rotate")) {
        element.classList.remove("expand-rotate")
        element.classList.add("collapse-rotate")
    } else element.classList.add("expand-rotate")
}

function removeAllOrder() {
    let x = document.querySelectorAll(".order-item").length - 1
    for (let i = x; i > 0; i--) {
        document.querySelectorAll(".order-item")[i].remove()
    }
}

async function getAllOrderDetail(orderId) {
    let wrapper = document.querySelector("#" + orderId)
    try {
        let res = await fetch('http://localhost:8080/orderDetail/' + orderId, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
        if (res.ok) {
            let data = await res.json();
            let result = Object.values(data);
            console.log(result)
            for (let i = 0; i < result.length; i++) {
                wrapper.innerHTML += `
                <ul class="order-detail-item">
                    <li class="id" style="flex: 15%">${result[i]["drug"]["id"]}</li>
                    <li class="drug" style="flex: 40%">${result[i]["drug"]["name"]}</li>
                    <li class="quantity" style="flex: 15%">${result[i]["quantity"]}</li>
                    <li class="cost-each" style="flex: 15%">${result[i]["drug"]["money"]}</li>
                    <li class="cost-all" style="flex: 15%">${result[i]["cost"]}</li>
                </ul>`
            }
        }
    } catch (e) {}
}

async function getAllOrder() {
    removeAllOrder()
    $(".spinner").css("display", "block")
    let wrapper = document.querySelector(".orders")
    try {
        let res = await fetch('http://localhost:8080/order/' + localStorage["user"], {
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
                    `<ul class="order-item">
                <li class="id" style="flex: 20%;">${result[i]["id"]}</li>
                <li class="purchase-time" style="flex: 40%;">${result[i]["purchaseTime"]}</li>
                <li class="purchase-type" style="flex: 10%;">${result[i]["purchaseType"]}</li>
                <li class="total" style="flex: 20%;">${result[i]["total"]}</li>
                <li style="flex: 10%;"><a href="#${result[i]["id"]}" data-bs-toggle="collapse"><div class="collapse-arrow" onclick="showOrderDetail(this)"></div></a></li></ul>
                <ul id="${result[i]["id"]}" class="collapse" style="border: 1px solid black">
                    <ul class="order-detail-item">
                        <li class="id" style="flex: 15%">Drug ID</li>
                        <li class="drug" style="flex: 40%">Drug</li>
                        <li class="quantity" style="flex: 15%">Quantity</li>
                        <li class="cost-each" style="flex: 15%">Cost(1)</li>
                        <li class="cost-all" style="flex: 15%">Cost(all)</li>
                    </ul>
                </ul>`
            }

            for (let i = 0; i < result.length; i++) {
                getAllOrderDetail(result[i]["id"])
            }
        }
    } catch (e) {}
    $(".spinner").css("display", "none")
}

function info_label() {
    document.querySelector("#info-label").innerHTML = "Information of " + localStorage["user"] + "'s order"
}

info_label()
getAllOrder()