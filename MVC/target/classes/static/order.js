//TODO link to orders' front end
$(document).on("click", ".cart-nav", function() {
    let new_order = JSON.parse(localStorage.getItem("cart-item"))
    let date = new Date()
    let order = {
        id: date.getTime(),
        customer: { "username": "user123" },
        purchaseTime: "",
        purchaseType: "COD",
        total: 0
    }

    createOrder(order)
    for (let i = 0; i < new_order.length; i++) {
        let orderDetail = {
            order: { id: "9999" },
            drug: { id: new_order[i]["id"] },
            quantity: parseInt(new_order[i]["amount"]),
            cost: parseInt(new_order[i]["amount"]) * parseInt(new_order[i]["price"])
        }
        createOrderDetail(orderDetail)
    }
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