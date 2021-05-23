$(document).on("click",".slide-img",function(){
    let img = ($(this).attr("src")).split("/")[3].split(".")[0]
    let imgFound = false;
    let data = localStorage.getItem("data")
    let index;
    for (let i = 0; i < data.split("\n").length ; i++) {
        // console.log(img.includes(data.split("\n")[i].split("&&")[1]))
        if (data.split("\n")[i].split("&&")[1].includes(img)) {
            index = i
            imgFound = true
            break
        }
    }
    // if (!imgFound){
    //     for (let i = 0; i < data.split("\n").length ; i++) {
    //         // console.log(img.includes(data.split("\n")[i].split("&&")[1]))
    //         if (data.split("\n")[i].split("&&")[1] == ) {
    //             index = i
    //             break
    //         }
    //     }
    // }
    let drug = {
        id: data.split("\n")[index].split("&&")[0],
        name: data.split("\n")[index].split("&&")[1],
        stock: data.split("\n")[index].split("&&")[10],
        price: data.split("\n")[index].split("&&")[9],
        preparation:data.split("\n")[index].split("&&")[2],
        packaging:data.split("\n")[index].split("&&")[3],
        dosage:data.split("\n")[index].split("&&")[5],
        ingredients:data.split("\n")[index].split("&&")[7],
        country:data.split("\n")[index].split("&&")[8],
        group:data.split("\n")[index].split("&&")[4],
        type: data.split("\n")[index].split("&&")[6],
        producers_id: data.split("\n")[index].split("&&")[11]
    }
    localStorage.setItem("drug-click", JSON.stringify(drug))
    window.location.href = "http://localhost:8080/drug-infomation.html"
})