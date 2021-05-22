function initialize(){
    let drug = JSON.parse(localStorage.getItem("drug-click"))
    $(".id").text(drug.id)
    $(".name").text(drug.name)
    $(".stock").text(drug.stock)
    $(".price").text(drug.price)
}
initialize()