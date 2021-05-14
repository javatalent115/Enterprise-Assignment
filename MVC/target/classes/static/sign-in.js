if(localStorage.getItem("accountType")){
    localStorage.setItem("accountType",localStorage.getItem("accountType"))
}
else{
    localStorage.setItem("accountType","guest")
}
if(localStorage.getItem("accountType") == "guest"){
    document.getElementById("sign-in").setAttribute("style","display:flex;")
    document.getElementById("sign-out").setAttribute("style","display:none;")
}
else{
    document.getElementById("sign-in").setAttribute("style","display:none;")
    document.getElementById("sign-out").setAttribute("style","display:flex;")
}

$(".sign").click(function(){
    if ($("#sign-out").attr("style") == "display:flex;"){
        document.getElementById("sign-in").setAttribute("style","display:flex;")
        document.getElementById("sign-out").setAttribute("style","display:none;")
        localStorage.setItem("accountType","guest")
    }
});


