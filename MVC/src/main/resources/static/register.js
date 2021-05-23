function checkConfirm() {
    let password = document.querySelector("#password").value
    let confirm = document.querySelector("#confirm").value
    if (confirm !== password) {
        document.querySelector("#unmatched").innerHTML = "Confirm password does not match"
    } else {
        document.querySelector("#unmatched").innerHTML = ""
    }
}

function flash(element) {
    setTimeout(function() {
        element.classList.add("flash")
    }, 1)
    element.classList.remove("flash")
}

function check() {
    let notOk = false
    let input_list = document.querySelectorAll("input[type=text], input[type=email], input[type=password]")
    for (let i = 0; i < input_list.length; i++) {
        if (input_list[i].value === "") {
            notOk = true
            flash(document.querySelector("#" + input_list[i].id + "_t"))
        }
    }

    if (document.querySelector("#address").value === "") {
        notOk = true
        flash(document.querySelector("#address_t"))
    }
    if (document.querySelector("#term").checked == false) {
        notOk = true
        flash(document.querySelector("#text_term"))
    }
    return notOk
}

function register() {
    if (!check()) {
        let username = document.querySelector("#username").value
        let password = document.querySelector("#password").value
        let firstname = document.querySelector("#firstname").value
        let lastname = document.querySelector("#lastname").value
        let email = document.querySelector("#email").value
        let address = document.querySelector("#adress").value

        let customer = {
            username: username,
            password: password,
            firstname: firstname,
            lastname: lastname,
            email: email,
            address: address,
            last_login: ""
        }
        register_account(customer)
    }
}

async function register_account(customer) {
    try {
        let res = await fetch('http://localhost:8080/drug/getDrugsByFilter', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(customer)
        });
    } catch (e) {}
}