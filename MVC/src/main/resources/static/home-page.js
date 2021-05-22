$(document).on("click",".news-item",function(){
    if ($(this).find("p").text() == "News discover about vaccine to Covid 19"){
        window.location.href = "http://localhost:8080/news-1.html"
    }
    else if ($(this).find("p").text() == "Cancer can cure?"){
        window.location.href = "http://localhost:8080/news-2.html"
    }    
    else if ($(this).find("p").text() == "Did you eat enough vegetable"){
        window.location.href = "http://localhost:8080/news-3.html"
    }
})