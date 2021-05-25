if (localStorage.getItem("accountType")) {
    localStorage.setItem("accountType", localStorage.getItem("accountType"))
} else {
    localStorage.setItem("accountType", "guest")
}
if (localStorage.getItem("accountType") === "guest") {
    document.querySelector("#sign-in").setAttribute("style", "display:flex;")
    document.querySelector("#sign-out").setAttribute("style", "display:none;")
    document.querySelector(".nav-item-cart").setAttribute("style", "display:none;")
    document.querySelector(".nav-item-customer").setAttribute("style", "display:none;")
} else {
    if (localStorage.getItem("accountType") === "user") {
    document.querySelector(".nav-item-customer").setAttribute("style", "display:none;")
    document.querySelector(".nav-item-cart").setAttribute("style", "display:flex;")
    } else {
    document.querySelector(".nav-item-customer").setAttribute("style", "display:flex;")
    document.querySelector(".nav-item-cart").setAttribute("style", "display:none;")
    }
    document.querySelector("#sign-in").setAttribute("style", "display:none;")
    document.querySelector("#sign-out").setAttribute("style", "display:flex;")
}

$(".sign").click(function() {
    if ($("#sign-out").attr("style") === "display:flex;") {
        localStorage.removeItem("cart")
        localStorage.removeItem("cart-item")
        document.getElementById("sign-in").setAttribute("style", "display:flex;")
        document.getElementById("sign-out").setAttribute("style", "display:none;")
        localStorage.setItem("accountType", "guest")
    }
});