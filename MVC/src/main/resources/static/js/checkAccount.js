if (localStorage.getItem("accountType") == "guest") {
    $(".nav-item-cart").css("display", "none")
} else if (localStorage.getItem("accountType") == "user") {

} else {

}