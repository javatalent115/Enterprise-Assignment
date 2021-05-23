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
    $(".drug-image").attr("src",returnImage(drug.name))
}

initialize()

function returnImage(name){
    if (name.includes("Panadol") ){
        return "./images/others/panadol.png"
    }
    else if(name.includes("EtonciB1")){
        return "./images/others/EtonciB1.png"
    }
    else if(name.includes("Berberin")){
        return "./images/others/Berberin.png"
    }
    else if(name.includes("Condova")){
        return "./images/others/Condova.png"
    }
    else if(name.includes("Diazepam")){
        return "./images/others/Diazepam.png"
    }
    else if(name.includes("Dodevifort")){
        return "./images/others/Dodevifort.png"
    }
    else if(name.includes("Felodipin Stada")){
        return "./images/others/Felodipin Stada.png"
    }
    else if(name.includes("Fluopas")){
        return "./images/others/Fluopas.png"
    }
    else if(name.includes("Mifenan")){
        return "./images/others/Mifenan.png"
    }
    else if(name.includes("Paracetamol")){
        return "./images/others/Paracetamol.png"
    }
    else if(name.includes("Prednison")){
        return "./images/others/Prednison.png"
    }
    else if(name.includes("Nicpostinew")){
        return "./images/others/Nicpostinew.png"
    }
    else{
        return "./images/blank-image.png"
    }
}