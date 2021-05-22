function initialize(){
    let drug = JSON.parse(localStorage.getItem("drug-click"))
    $(".id").text(drug.id)
    $(".name").text(drug.name)
    $(".stock").text(drug.stock)
    $(".price").text(drug.price)
    $(".preperation").text(drug.preperation)
    $(".packaging").text(drug.packaging)
    $(".dosage").text(drug.dosage)
    $(".ingredient").text(drug.ingredient)
    $(".country").text(drug.country)
    $(".group").text(drug.group)
    $(".type").text(drug.type)
    $(".drug-image").attr("src","./images/blank-image.png")
}

initialize()