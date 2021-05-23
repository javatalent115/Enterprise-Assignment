async function homeInitialize(){
    $(".information-medicine-container").css("display","none")
    $(".spinner").css("display","block")
    let all = "";
    let Drug = {
        type: "Both",
        group: "Both",
        sortType: "ID"
    }
    try {
        let res = await fetch('http://localhost:8080/drug/getDrugsByFilter', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(Drug)
        });
        if (res.ok) {
            let datas = await res.json();
            let result = Object.values(datas);
            for (let i = 0; i < result.length; i++) {
                all += result[i].split(" -- ")[0] + "&&" + result[i].split(" -- ")[1] + "&&" + result[i].split(" -- ")[2] + "&&" + result[i].split(" -- ")[3] + "&&" + result[i].split(" -- ")[4] + "&&" + result[i].split(" -- ")[5] +
                    "&&" + result[i].split(" -- ")[6] + "&&" + result[i].split(" -- ")[7] + "&&" + result[i].split(" -- ")[8] + "&&" + result[i].split(" -- ")[9] + "&&" + result[i].split(" -- ")[10] + "&&" + result[i].split(" -- ")[11] + "\n";
            }
            localStorage.setItem("data",all)
        }
    }
        catch(e){}
    $(".spinner").css("display","none")
    $(".information-medicine-container").css("display","block")
    
}
$(document).on("click",".news-item",function(){
    if ($(this).find("p").text() == "Covid 19 News?"){
        window.location.href = "http://localhost:8080/news-1.html"
    }
    else if ($(this).find("p").text() == "Cancer can cure?"){
        window.location.href = "http://localhost:8080/news-2.html"
    }    
    else if ($(this).find("p").text() == "Did you eat enough vegetable?"){
        window.location.href = "http://localhost:8080/news-3.html"
    }
})
homeInitialize()