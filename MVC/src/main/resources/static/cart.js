function initialize(){
    let data;
    if (localStorage.getItem("cart-item")){
        data =JSON.parse(localStorage.getItem("cart-item"))
    }
    else{
        data = {}
    }
    let wrapper = document.querySelector(".medicines")
    let totalMedicinePrice =0 ;
    for (let i = 0; i < data.length ; i++) {
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
    wrapper.innerHTML+=`<ul class="medicine-item">
    <li></li>
    <li></li>
    <li class = "text-nowrap totalMedicinePrice">Total price(${data.length} products)</li>
    <li></li>
    <li class = "totalMedicinePrice">${totalMedicinePrice}$</li>
</ul>`
}
$(document).ready(initialize())
$(document).ready(function() {
    $("#confirm-purchase-modal").on("show.bs.modal", function(event) {
        localStorage.setItem("cart-item","")
        localStorage.setItem("cart",0)
        setTimeout(() => {
            window.location.href = "http://localhost:8080/home-page.html"
        }, 1000);
    });
});