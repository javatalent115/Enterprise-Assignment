if (localStorage.getItem("accountType") === "user" || localStorage.getItem("accountType") === "admin") {
    location.href = "http://localhost:8080/home-page.html";
}

async function to_home() {
    let user = {
        username: document.querySelector("#username").value,
        password: document.querySelector("#password").value
    };
    try {
        let res = await fetch('http://localhost:8080/auth', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        if (res.ok) {
            let data = await res.text();
            if (data === "invalid") {
                document.querySelector(".invalid-password").setAttribute("style", "display: block")
            } else {
                localStorage.removeItem("cart")
                localStorage.removeItem("cart-item")
                localStorage.setItem("accountType", data);
                localStorage.setItem("user", user["username"])
                    // console.log(localStorage.getItem("currentPage"))
                if (localStorage.getItem("currentPage") == "homePage") {
                    location.href = "http://localhost:8080/home-page.html"
                } else if (localStorage.getItem("currentPage") == "medicinePage") {
                    console.log("hello")
                    location.href = "http://localhost:8080/medicine.html"
                } else if (localStorage.getItem("currentPage") == "cartPage") {
                    location.href = "http://localhost:8080/cart.html"
                }
            }
            return data;
        }
    } catch (e) {}
    return 404;
}