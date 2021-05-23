let drug = JSON.parse(localStorage.getItem("drug-click"))
async function initialize(){
    $(".id").text(drug.id)
    $(".name").text(drug.name)
    $(".stock").text(drug.stock)
    $(".price").text(drug.price)
    $(".preparation").text(drug.preparation)
    $(".packaging").text(drug.packaging)
    $(".dosage").text(drug.dosage)
    $(".ingredient").text(drug.ingredients)
    $(".country").text(drug.country)
    $(".group").text(drug.group)
    $(".type").text(drug.type)
    $(".producer").text(drug.producers_id)
    $(".drug-image").attr("src",returnImage(drug.name))
    await addRelatedDrug()
}
async function addRelatedDrug(){
    try{
        console.log(drug.id)
        let res = await fetch('http://localhost:8080/drug/getRelatedDrugs', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: drug.id
        });
        if (res.ok) {
            let data = await res.json();
            let result = Object.values(data);
            console.log(result)
            let wrapper = document.querySelector(".carousel-inner")
            for (let i = 0; i < 12; i++) {
                console.log(i)
                if (i == 0){
                console.log("hell")
                wrapper.innerHTML +=`<div class="carousel-item active">
                <div class="slide-wrapper">
                    <img class="slide-img" src=${returnImage(result[i].split(" -- ")[1])} width="300px" height="300px" alt="...">
                    <img class="slide-img" src=${returnImage(result[i].split(" -- ")[1])} width="300px" height="300px" alt="...">
                    <img class="slide-img" src=${returnImage(result[i].split(" -- ")[1])}  width="300px" height="300px" alt="...">
                </div>
            </div>`
            }
            else{
                console.log('123')
                wrapper.innerHTML +=`<div class="carousel-item">
                <div class="slide-wrapper">
                    <img class="slide-img" src=${returnImage(result[i].split(" -- ")[1])}  width="300px" height="300px" alt="...">
                    <img class="slide-img" src=${returnImage(result[i].split(" -- ")[1])} width="300px" height="300px" alt="...">
                    <img class="slide-img" src=${returnImage(result[i].split(" -- ")[1])}  width="300px" height="300px" alt="...">
                </div>
            </div>`
            }
            }
        }
    }
    catch(e){}

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