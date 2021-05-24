//TODO link to orders' front end
$(document).on("click", ".purchase-box", function() {
    let date = new Date()
    let new_order = JSON.parse(localStorage.getItem("cart-item"))
    let total = 0
    for (let i = 0; i < new_order.length; i++) {
        total += parseInt(new_order[i]["amount"]) * parseInt(new_order[i]["price"])
    }
    let order = {
        id: localStorage.getItem("user") + "-" + date.getFullYear() + date.getMonth() + date.getDay() +
            "-" + date.getHours() + date.getMinutes() + date.getSeconds(),
        customer: { "username": localStorage.getItem("user") },
        purchaseTime: "",
        purchaseType: "COD",
        total: total
    }

    createOrder(order)

    setTimeout(function() {
        for (let i = 0; i < new_order.length; i++) {
            let orderDetail = {
                order: { id: order["id"] },
                drug: { id: new_order[i]["id"] },
                quantity: parseInt(new_order[i]["amount"]),
                cost: parseInt(new_order[i]["amount"]) * parseInt(new_order[i]["price"])
            }
            createOrderDetail(orderDetail)
        }
    }, 10)
})

async function createOrder(order) {
    try {
        let res = await fetch('http://localhost:8080/order', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(order)
        });
    } catch (e) {}
    return 404;
}

async function createOrderDetail(orderDetail, orderId) {
    try {
        let res = await fetch('http://localhost:8080/orderDetail', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(orderDetail)
        });
    } catch (e) {}
    return 404;
}