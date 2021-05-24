let drug = JSON.parse(localStorage.getItem("drug-click"))
async function initialize() {
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
    $(".drug-image").attr("src", returnImage(drug.name))
    await addRelatedDrug()
    await addRelatedCompany(drug.producers_id)
}
async function addRelatedCompany(id){
    try {
        let res = await fetch('http://localhost:8080/getProducerData', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: id
        });

    if (res.ok) {
        let data = await res.json();
        let result = Object.values(data);
        console.log(result)
        document.querySelector(".producer").innerHTML +=`<span class="dropdown">
        <img src="./images/icons/infor-icon.png" alt="" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
        <div class="dropdown-menu dropdown-menu-wrapper">
            <div class="dropdown-menu-name row justify-content-center">
                <div class="col fw-bold">${result[0]}</div>
            </div>
            <div class="text-center fw-bold text-secondary">Related Drugs</div>
            <div class="related-drug">
            </div>
        </div>
    </span>`
    document.querySelector(".related-drug").innerHTML+= `
    <div class="row justify-content-end">
        <div class="col-5 fw-bold">ID</div>
        <div class="col-1">|</div>
        <div class="col-6 text-center fw-bold">Name</div>
    </div>
    `
    for (let i = 0; i < result[2].split(",").length; i++) {
        document.querySelector(".related-drug").innerHTML+= `
        <div class="row">
            <div class="col-5 ">${result[2].split(",")[i].split("---")[0]}</div>
            <div class="col-1">-</div>
            <div class="col-6 text-center">${result[2].split(",")[i].split("---")[1]}</div>
        </div>
        `
    }
}
    }
    catch(e){}
}
async function addRelatedDrug() {
    try {
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
            for (let i = 0; i < result.length / 3; i += 3) {
                console.log(i)
                if (i == 0) {
                    console.log("hell")
                    wrapper.innerHTML += `<div class="carousel-item active">
                <div class="slide-wrapper">
                    <div class="medicine-card">
                        <img class="slide-img" src=${returnImage(result[i].split(" -- ")[1])} height="300px" alt="...">
                        <div class="medicine-card-name">${result[i].split(" -- ")[1]}</div>
                    </div>
                    <div class="medicine-card">
                        <img class="slide-img" src=${returnImage(result[i+1].split(" -- ")[1])} width="300px" height="300px" alt="...">
                        <div class="medicine-card-name">${result[i+1].split(" -- ")[1]}</div>
                    </div>
                    <div class="medicine-card">
                        <img class="slide-img" src=${returnImage(result[i+2].split(" -- ")[1])} width="300px" height="300px" alt="...">
                        <div class="medicine-card-name">${result[i+2].split(" -- ")[1]}</div>
                    </div>
                </div>
            </div>
        </div>`
                } else {
                    console.log('123')
                    wrapper.innerHTML += `<div class="carousel-item">
                <div class="slide-wrapper">
                    <div class="medicine-card">
                        <img class="slide-img" src=${returnImage(result[i].split(" -- ")[1])} height="300px" alt="...">
                        <div class="medicine-card-name">${result[i].split(" -- ")[1]}</div>
                    </div>
                    <div class="medicine-card">
                        <img class="slide-img" src=${returnImage(result[i+1].split(" -- ")[1])} width="300px" height="300px" alt="...">
                        <div class="medicine-card-name">${result[i+1].split(" -- ")[1]}</div>
                    </div>
                    <div class="medicine-card">
                        <img class="slide-img" src=${returnImage(result[i+2].split(" -- ")[1])} width="300px" height="300px" alt="...">
                        <div class="medicine-card-name">${result[i+2].split(" -- ")[1]}</div>
                    </div>
                </div>
            </div>
        </div>`
                }
            }
        }
    } catch (e) {}

}
initialize()

function returnImage(name) {
    if (name.includes("Panadol")) {
        return "./images/others/panadol.png"
    } else if (name.includes("EtonciB1")) {
        return "./images/others/EtonciB1.png"
    } else if (name.includes("Berberin")) {
        return "./images/others/Berberin.png"
    } else if (name.includes("Condova")) {
        return "./images/others/Condova.png"
    } else if (name.includes("Diazepam")) {
        return "./images/others/Diazepam.png"
    } else if (name.includes("Dodevifort")) {
        return "./images/others/Dodevifort.png"
    } else if (name.includes("Felodipin Stada")) {
        return "./images/others/Felodipin Stada.png"
    } else if (name.includes("Fluopas")) {
        return "./images/others/Fluopas.png"
    } else if (name.includes("Mifenan")) {
        return "./images/others/Mifenan.png"
    } else if (name.includes("Paracetamol")) {
        return "./images/others/Paracetamol.png"
    } else if (name.includes("Prednison")) {
        return "./images/others/Prednison.png"
    } else if (name.includes("Nicpostinew")) {
        return "./images/others/Nicpostinew.png"
    } else {
        return "./images/others/blank-image.png"
    }
}