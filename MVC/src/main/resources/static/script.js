let itemDisplayAtATime = 10
$(document).on("click", ".name", function() {
    let data = localStorage.getItem("data")
    let index;
    for (let i = 0; i < data.split("\n").length - 1; i++) {
        if (data.split("\n")[i].split("&&")[1] === $(this).text()) {
            index = i
            break
        }
    }
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
$(document).on("click",".search-button",function(){
    let name = $("#medicine").val()
    let data = localStorage.getItem("data")
    let index;
    for (let i = 0; i < data.split("\n").length; i++) {
        if(data.split("\n")[i].split("&&")[1] == name){
            index = i
            break
        }
    }
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

});
function addForAdvanceEdit(data, index) {
    $("#advanced-id").val(data.split("\n")[index].split("&&")[0])
    $("#advanced-name").val(data.split("\n")[index].split("&&")[1])
    $("#advanced-stock").val(data.split("\n")[index].split("&&")[10])
    $("#advanced-price").val(data.split("\n")[index].split("&&")[9])
    $("#advanced-preparation").val(data.split("\n")[index].split("&&")[2])
    $("#advanced-packaging").val(data.split("\n")[index].split("&&")[3])
    $("#advanced-dosage").val(data.split("\n")[index].split("&&")[5])
    $("#advanced-ingredient").val(data.split("\n")[index].split("&&")[7])
    $("#advanced-country").val(data.split("\n")[index].split("&&")[8])
    $("#advanced-group").val(data.split("\n")[index].split("&&")[4]);
    if (data.split("\n")[index].split("&&")[6] !== "Undefined") $("#advanced-type").val(data.split("\n")[index].split("&&")[6]);
}

function checkAccount() {
    if (localStorage.getItem("accountType") === "guest") {
        $("li:nth-child(6)").css("display", "none")
        $("li:nth-child(7)").css("display", "none")
        $(".add-image").css("display", "none")
        $(".cart-nav").css("display", "none")
        $(".amount").css("display", "none")
    } else if (localStorage.getItem("accountType") === "user") {
        $("li:nth-child(6)").css("display", "none")
        $(".add-image").css("display", "none")
        $(".more-button").css("display", "none")
    } else {
        $(".amount").css("display", "none")
        $(".trash-image").css("display", "block")
        $(".cart-images").css("display", "none")
        $(".cart-nav").css("display", "none")
    }
}
$("#medicine2").on('keyup', function(e) {
    if (e.key === 'Enter' || e.keyCode === 13) {
        $(".search-button-2").click()
    }
});
$("#medicine").on('keyup', function(e) {
    if (e.key === 'Enter' || e.keyCode === 13) {
        $(".search-button").click()
    }
});

function setLocalStorage(){
    if (localStorage.getItem("Group") === null) localStorage.setItem("Group", "Both");
    if (localStorage.getItem("sort-type") === null) localStorage.setItem("sort-type", "ID");
    if (localStorage.getItem("Type") === null) localStorage.setItem("Type", "Both");
    $(".type").children("button").text(localStorage.getItem("Type"));
    if (localStorage.getItem("Group") === "Both") $(".checkbox-filter").find("img").attr("src", "./images/icons/checked.png");
    else if (localStorage.getItem("Group") === "Đông dược") $(".tan-duoc").find("img").attr("src", "./images/icons/unchecked.png");
    else if (localStorage.getItem("Group") === "Tân dược" ) $(".dong-duoc").find("img").attr("src", "./images/icons/unchecked.png");
    else $(".checkbox-filter").find("img").attr("src", "./images/icons/unchecked.png");
}

function initializes() {
    document.querySelector(".item-count").innerHTML = parseInt(0 + localStorage.getItem("cart"))
    checkAccount();
    $(".spinner").css("display", "block")
    setLocalStorage();
    $(".sort").children("button").text(localStorage.getItem("sort-type"));
    document.addEventListener('DOMContentLoaded', init, false);
    addCompany()
}
//Initialize cart-item
let listItem = localStorage.getItem('cart-item') ?
    JSON.parse(localStorage.getItem('cart-item')) :
    []

    //if add to cart btn clicked
$(document).on("click",".cart-nav",function(){
    window.location.href = "http://localhost:8080/cart.html"
})
$(document).on("click", ".cart-btn", function() {
    let count = localStorage.getItem("cart");
    document.querySelector(".item-count").innerHTML = parseInt(0 + localStorage.getItem("cart"))
    let cart = $('.cart-nav');
    // find the img of that card which button is clicked by user
    let imgtodrag = $(this).parent('li').find("img").eq(0);
    if (imgtodrag) {
        // duplicate the img
        var imgclone = imgtodrag.clone().offset({
            top: imgtodrag.offset().top,
            left: imgtodrag.offset().left
        }).css({
            'opacity': '0.8',
            'position': 'absolute',
            'height': '35px',
            'width': '35px',
            'z-index': '100'
        }).appendTo($('body')).animate({
            'top': cart.offset().top + 20,
            'left': cart.offset().left + 30,
            'width': 35,
            'height': 35
        }, 1000, 'easeInOutExpo');

        let id = $(this).parent("li").parent("ul").find(".id").text()
        let name = $(this).parent("li").parent("ul").find(".name").text()
        let amount = $(this).parent("li").parent("ul").find(".amount").text()
        let price = $(this).parent("li").parent("ul").find(".price").text()
        const drug = {
            id: id,
            name: name,
            amount: amount,
            price: price
        };
        setTimeout(function() {
            if (count === null) count = "0";
            count = (parseInt(count) + parseInt(drug.amount));
            localStorage.setItem("cart", count)
            $(".cart-nav .item-count").text(localStorage.getItem("cart"));

        }, 1500);
        let isExist = false;
        listItem.forEach(function(object) {
            if (object.id === drug.id) {
                object.amount = (parseInt(object.amount) + parseInt(drug.amount));
                isExist = true;
            }
        });
        if (!isExist) listItem.push(drug);
        const myJSON = JSON.stringify(listItem);
        localStorage.setItem("cart-item", myJSON)
        imgclone.animate({
            'width': 0,
            'height': 0
        }, function() {
            $(this).detach()
        });
    }
});

let length = document.querySelectorAll(".more-button-submenu-wrapper");
for (let i = 0; i < length.length; i++) {
    document.querySelectorAll(".more-button-submenu-wrapper")[i].setAttribute("style", "display:none")
}
// $(".more-button").click(function (e) {
//   console.log(e)
//   // if ($(this).parent("li").find(".more-button-submenu-wrapper").css("display") == "block") {
//   //   $(this).parent("li").find(".more-button-submenu-wrapper").css("display", "none")
//   // }
//   // else {
//   //   $(this).parent("li").find(".more-button-submenu-wrapper").css("display", "block")
//   // }
// });

$(document).on("click", ".more-button", function() {
    event.preventDefault()
    if ($(this).parent("li").find(".more-button-submenu-wrapper").css("display") === "block") {
        $(this).parent("li").find(".more-button-submenu-wrapper").css("display", "none")
    } else {
        $(this).parent("li").find(".more-button-submenu-wrapper").css("display", "block")
    }
});

$(document).on("click", ".more-button-submenu-item", function() {
    event.preventDefault()
    $(this).parent("ul").parent("div").css("display", "none")
});

let quickChange;
$(document).ready(function() {
    $("#add-company-modal").on("show.bs.modal", function(event) {
        // Get the button that triggered the modal
        quickChange = $(event.relatedTarget)
    });
});
$(document).on("click", ".no-add-company", function() {
    quickChange.parent().parent().remove()
})

$(document).on("click", ".confirm-add-company", function() {
    if (quickChange.parent().parent().find(".id").text() === 0 || quickChange.parent().parent().find(".add-name").text() === 0 || quickChange.parent().parent().find(".stock").text() === 0 || quickChange.parent().parent().find(".price").text() === 0) {
        alert("This must have a value")
        quickChange.parent("li").parent("ul").find(".changeAble").attr("contenteditable", "true")
        quickChange.parent("li").parent("ul").find(".changeAble").css({
            "border": "1px solid red"
        })
        quickChange.css("display", "block")
        quickChange.parent("li").find("img").css("display", "none")
        quickChange.attr("data-bs-toggle", "modal")
        quickChange.attr("data-bs-target", "#add-company-modal")
    } else {
        $(".cart-btn").parent().find("img").removeClass("add-trash-image")
        quickChange.parent().parent().find("li").removeClass("add-name")
        quickChange.parent().parent().find("li").addClass("name")
        quickChange.attr("data-bs-toggle", "modal")
        quickChange.attr("data-bs-target", "#add-company-modal")
        quickChange.css("display", "none")
        quickChange.parent("li").find("img").css("display", "inline-block")
        quickChange.parent("li").parent("ul").find(".changeAble").css({
            "border": "none"
        })
        quickChange.parent("li").parent("ul").find(".changeAble").attr("contenteditable", "false")
        quickChange.parent("li").parent("ul").find(".id").removeClass("changeAble")
        quickChange.parent("li").parent("ul").find(".name").removeClass("changeAble")
        quickChange.removeAttr("data-bs-toggle")
        quickChange.removeAttr("data-bs-target")
        let drug = {
            id: quickChange.parent().parent().find(".id").text(),
            name: quickChange.parent().parent().find(".name").text(),
            stock: quickChange.parent().parent().find(".stock").text(),
            money: quickChange.parent().parent().find(".price").text(),
            producer: {
                id: document.getElementById("company-select").value
            }
        };


        //FETCH add drug o day, co object drug r do
        addDrug(drug);




    }
});

async function addDrug(drug) {
    try {
        let res = await fetch('http://localhost:8080/drug/addDrug', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(drug)
        });
        if (res.ok) {
            let data = await res.json();
            let result = Object.values(data);
            if (result[0] !== "failed") {
                await addItem(itemDisplayAtATime)
            }
            return data;
        }
    } catch (e) {}
    return 404;
}

$(document).on("click", ".quick-change", function() {
    event.preventDefault()
    $(this).parent("ul").parent("div").parent("li").parent("ul").find(".changeAble").attr("contenteditable", "true")
    $(this).parent("ul").parent("div").parent("li").parent("ul").find(".changeAble").css({
        "border": "1px solid black"
    })
    $(this).parent("ul").parent("div").parent("li").find("img").css("display", "none")
    $(this).parent("ul").parent("div").parent("li").find(".done").css("display", "block")
});

$(document).on("click", ".done", function() {
    event.preventDefault()
    if (!($(this).parent().parent().find(".id").text() === 0 || $(this).parent().parent().find(".name").text() === 0 || $(this).parent().parent().find(".stock").text() === 0 || $(this).parent().parent().find(".price").text() === 0)) {
        if ($(this).attr("data-bs-target") !== "#add-company-modal") {
            $(this).css("display", "none")
            $(this).parent("li").find("img").css("display", "inline-block")
            $(this).parent("li").parent("ul").find(".changeAble").css({
                "border": "none"
            })
            $(this).parent("li").parent("ul").find(".changeAble").attr("contenteditable", "false")
            $(this).parent("li").parent("ul").find(".id").removeClass("changeAble")
            $(this).parent("li").parent("ul").find(".name").removeClass("changeAble")
            $(this).removeAttr("data-bs-toggle")
            $(this).removeAttr("data-bs-target")
            var drug = {
                id: $(this).parent().parent().find(".id").text(),
                stock: $(this).parent().parent().find(".stock").text(),
                money: $(this).parent().parent().find(".price").text()
            };

            //Fetch update o day, co object drug r do
            updateExistDrug(drug);
        }
    }
});

async function updateExistDrug(drug) {
    try {
        await fetch("http://localhost:8080/drug/updateDrug", {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(drug)
        });
    } catch (e) {}
}

let img;
$(document).ready(function() {
    $("#delete-confirm-modal").on("show.bs.modal", function(event) {
        // Get the button that triggered the modal
        img = $(event.relatedTarget)
    });
});

let advance;
$(document).ready(function() {
    $("#modifyMedicine").on("show.bs.modal", function(event) {
        // Get the button that triggered the modal
        advance = $(event.relatedTarget)
        let index;
        let data = localStorage.getItem("data")
        for (let i = 0; i < data.split("\n").length - 1; i++) {
            if (data.split("\n")[i].split("&&")[1] == advance.parent().parent().parent().parent().find(".name").text()) {
                index = i
                break
            }
        }
        console.log(data.split("\n")[index])
        addForAdvanceEdit(data, index);
    });
});

$(document).on("click", ".confirm-modifyMedicine", function() {
    let drug = {
        id: $("#advanced-id").val(),
        name: $("#advanced-name").val(),
        preparation: $("#advanced-preparation").val(),
        packaging: $("#advanced-packaging").val(),
        drugGroup: $("#advanced-group").val(),
        dosage: $("#advanced-dosage").val(),
        type: $("#advanced-type").val(),
        ingredients: $("#advanced-ingredient").val(),
        country: $("#advanced-country").val(),
        money: parseInt($("#advanced-price").val()),
        stock: parseInt($("#advanced-stock").val()),
        producer: {

        }
    };
    advanceUpdate(drug)
});


async function advanceUpdate(drug) {
    try {
        await fetch('http://localhost:8080/drug/advanceUpdateDrug', {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(drug)
        });
        await addItem()
    } catch (e) {}
}

$(document).on("click", ".confirm-delete", function() {
    if (img.hasClass("add-trash-image")) {
        img.parent().parent().remove()
    } else {
        img.parent().parent().remove()
        deleteDrug(img.parent().parent().find(".id").text())
    }
});

$(document).on("click", ".trash-image", function() {

    // event.preventDefault()
    // $(this).parent("li").parent("ul").remove()
    // deleteDrug($(this).parent("li").parent("ul").find(".id").text())
});

async function deleteDrug(id) {
    try {
        let res = await fetch('http://localhost:8080/drug/deleteDrug', {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: id
        });
    } catch (e) {}
}
$(document).on("click", ".add-image", function() {
    event.preventDefault()
    $(".medicines").append(`
  <ul class="medicine-item">
                        <li class="changeAble id" contenteditable="true"></li>
                        <li class="changeAble add-name" contenteditable="true"></li>
                        <li class="changeAble stock" contenteditable="true"></li>
                        <li class="changeAble amount" contenteditable="true"><div>1</div><img class = "increase-amount-image" src="./images/increase-amount-image.png" alt=""></li>
                        <li class="changeAble price" contenteditable="true"></li>
                        <li>
                            <div class="done" data-bs-toggle="modal" data-bs-target="#add-company-modal">Done</div>
                            <img src="./images/icons/more.png" class="more-image more-button" style="cursor: pointer;"">
                            <div class="more-button-submenu-wrapper" >
                                <ul class="more-button-submenu">
                                    <li class="more-button-submenu-item quick-change">
                                        Quick change
                                    </li>
                                    <li class="more-button-submenu-item more-button-submenu-item" data-bs-toggle="modal" data-bs-target="#modifyMedicine">
                                        Advance
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <img src="./images/icons/cart.png" class="cart-btn cart-images" style="cursor: pointer;">
                            <img src="./images/icons/trash.png" class="trash-image add-trash-image" alt="" data-bs-toggle="modal" data-bs-target="#delete-confirm-modal">
                        </li>
                    </ul>
  `)
    let lastChild = document.querySelectorAll(".changeAble").length
    for (let i = lastChild - 5; i < lastChild; i++) {
        document.querySelectorAll(".changeAble")[i].setAttribute("style", "border: 1px solid black")
    }

    document.querySelectorAll(".done")[document.querySelectorAll(".done").length - 1].setAttribute("style", "display:block")
    document.querySelectorAll(".more-button")[document.querySelectorAll(".more-button").length - 1].setAttribute("style", "display:none")
    checkAccount()
});

function reset() {
    window.localStorage.clear();
    document.querySelector(".item-count").innerHTML = parseInt(0 + localStorage.getItem("cart"))
}

var Pagination = {

    code: '',

    // --------------------
    // Utility
    // --------------------

    // converting initialize data
    Extend: function(data) {
        data = data || {};
        Pagination.size = data.size || 300;
        Pagination.page = data.page || 30;
        Pagination.step = data.step || 3;
    },

    // add pages by number (from [s] to [f])
    Add: function(s, f) {
        for (var i = s; i < f; i++) {
            Pagination.code += '<a class ="page">' + i + '</a>';
        }
    },

    // add last page with separator
    Last: function() {
        Pagination.code += '<i>...</i><a class = "page">' + Pagination.size + '</a>';
    },

    // add first page with separator
    First: function() {
        Pagination.code += '<a class = "page">1</a><i>...</i>';
    },



    // --------------------
    // Handlers
    // --------------------

    // change page
    Click: function() {
        Pagination.page = +this.innerHTML;
        Pagination.Start();
    },

    // previous page
    Prev: function() {
        Pagination.page--;
        if (Pagination.page < 1) {
            Pagination.page = 1;
        }
        Pagination.Start();
    },

    // next page
    Next: function() {
        Pagination.page++;
        if (Pagination.page > Pagination.size) {
            Pagination.page = Pagination.size;
        }
        Pagination.Start();
    },



    // --------------------
    // Script
    // --------------------

    // binding pages
    Bind: function() {
        var a = Pagination.e.getElementsByTagName('a');
        for (var i = 0; i < a.length; i++) {
            if (+a[i].innerHTML === Pagination.page) a[i].className = 'current';
            a[i].addEventListener('click', Pagination.Click, false);
        }
    },

    // write pagination
    Finish: function() {
        Pagination.e.innerHTML = Pagination.code;
        Pagination.code = '';
        Pagination.Bind();
    },

    // find pagination type
    Start: function() {
        if (Pagination.size < Pagination.step * 2 + 6) {
            Pagination.Add(1, Pagination.size + 1);
        } else if (Pagination.page < Pagination.step * 2 + 1) {
            Pagination.Add(1, Pagination.step * 2 + 4);
            Pagination.Last();
        } else if (Pagination.page > Pagination.size - Pagination.step * 2) {
            Pagination.First();
            Pagination.Add(Pagination.size - Pagination.step * 2 - 2, Pagination.size + 1);
        } else {
            Pagination.First();
            Pagination.Add(Pagination.page - Pagination.step, Pagination.page + Pagination.step + 1);
            Pagination.Last();
        }
        Pagination.Finish();
    },



    // --------------------
    // Initialization
    // --------------------

    // binding buttons
    Buttons: function(e) {
        var nav = e.getElementsByTagName('a');
        nav[0].addEventListener('click', Pagination.Prev, false);
        nav[1].addEventListener('click', Pagination.Next, false);
    },

    // create skeleton
    Create: function(e) {

        var html = [
            '  <a class = "page"><img src="./images/icons/arrow-left.png" alt=""></a>', // previous button
            '<span ></span>', // pagination container
            '  <a class = "page"><img src="./images/icons/arrow-right.png" alt=""></a>' // next button
        ];

        e.innerHTML = html.join('');
        Pagination.e = e.getElementsByTagName('span')[0];
        Pagination.Buttons(e);
    },

    // init
    Init: function(e, data) {
        Pagination.Extend(data);
        Pagination.Create(e);
        Pagination.Start();
    }
};

var init = async function() {
    await addItem()
    let pages = checkNumberIsFloat((localStorage.getItem("data").split("\n").length) / itemDisplayAtATime)
    Pagination.Init(document.getElementById('pagination'), {
        size: pages, // pages size
        page: 1, // selected page
        step: 2 // pages before and after current
    });
};

function formatData(result) {
    let all = ""
    for (let i = 0; i < result.length; i++) {
        autoName.push(result[i].split(" -- ")[1])
        all += result[i].split(" -- ")[0] + "&&" + result[i].split(" -- ")[1] + "&&" + result[i].split(" -- ")[2] + "&&" + result[i].split(" -- ")[3] + "&&" + result[i].split(" -- ")[4] + "&&" + result[i].split(" -- ")[5] +
            "&&" + result[i].split(" -- ")[6] + "&&" + result[i].split(" -- ")[7] + "&&" + result[i].split(" -- ")[8] + "&&" + result[i].split(" -- ")[9] + "&&" + result[i].split(" -- ")[10] + "&&" + result[i].split(" -- ")[11] + "\n";
    }
    return all
}

function removeAllItem() {
    let x = document.querySelectorAll(".medicine-item").length - 1
    for (let i = x; i > 0; i--) {
        document.querySelectorAll(".medicine-item")[i].remove()
    }
}

$(document).on("click", ".search-button-2", function() {
    let searchName = document.getElementById("medicine2").value
    document.getElementById("medicine2").value = ""
    if (searchName === 0) {
        removeAllItem()
        addItem(itemDisplayAtATime)
    } else {
        let data = localStorage.getItem("data")
        removeAllItem()
        let index = 0
        let found = false
        let lastItem = data.split("\n").length
        for (let i = 0; i < lastItem; i++) {
            if (searchName === data.split("\n")[i].split("&&")[1]) {
                index = i
                found = true
                break
            }
        }
        if (found) {
            let wrapper = document.querySelector(".medicines")
            wrapper.innerHTML += `<ul class="medicine-item">
        <li class="id">${data.split("\n")[index].split("&&")[0]}</li>
        <li class="name">${data.split("\n")[index].split("&&")[1]}</li>
        <li class="changeAble stock">${data.split("\n")[index].split("&&")[10]}</li>
        <li class="changeAble amount"><div>1</div><img class = "increase-amount-image" src="./images/icons/increase-amount-image.png" alt=""></li>
        <li class="changeAble price">${data.split("\n")[index].split("&&")[9]}</li>
        <li>
            <div class="done">Done</div>
            <img src="./images/icons/more.png" class="more-image more-button" style="cursor: pointer;">
            <div class="more-button-submenu-wrapper">
                <ul class="more-button-submenu">
                    <li class="more-button-submenu-item quick-change">
                        Quick change
                    </li>
                    <li class="more-button-submenu-item more-button-submenu-item" data-bs-toggle="modal"
                        data-bs-target="#modifyMedicine">
                        Advance
                    </li>
                </ul>
            </div>
        </li>
        <li>
            <img src="./images/icons/cart.png" class="cart-btn cart-images" style="cursor: pointer;">
            <img src="./images/icons/trash.png" class="trash-image" alt="" data-bs-toggle="modal" data-bs-target="#delete-confirm-modal">
        </li>
    </ul>`
            checkAccount()
        } else {
            console.log("Can not find drug")
        }
    }

});

initializes()


/* * * * * * * * * * * * * * * * *
 * Pagination
 * javascript page navigation
 * * * * * * * * * * * * * * * * */


function checkNumberIsFloat(x) {

    let regexPattern = /^-?[0-9]+$/;

    // check if the passed number is integer or float
    let result = regexPattern.test(x);

    if (result) {
        return x
    } else {
        return parseInt(x) + 1
    }
}



$(document).on("click", ".page", function() {
    let page = 0
    page = document.querySelector(".current").innerHTML
    let itemDisplay = itemDisplayAtATime
    removeAllItem()
    let data = localStorage.getItem("data")
    let itemIndex = (page - 1) * itemDisplay
    let wrapper = document.querySelector(".medicines")
    for (let i = itemIndex; i < itemIndex + itemDisplay; i++) {
        if (data.split("\n").length-1 === i) {
            break
        } else {
            wrapper.innerHTML += `<ul class="medicine-item">
        <li class="id">${data.split("\n")[i].split("&&")[0]}</li>
        <li class="name">${data.split("\n")[i].split("&&")[1]}</li>
        <li class="changeAble stock">${data.split("\n")[i].split("&&")[10]}</li>
        <li class="changeAble amount"><img class = "decrease-amount-image" src="images/icons/decrease-amount-image.png" alt=""><div>1</div><img class = "increase-amount-image" src="./images/icons/increase-amount-image.png" alt=""></li>
        <li class="changeAble price">${data.split("\n")[i].split("&&")[9]}</li>
        <li>
            <div class="done">Done</div>
            <img src="./images/icons/more.png" class="more-image more-button" style="cursor: pointer;">
            <div class="more-button-submenu-wrapper">
                <ul class="more-button-submenu">
                    <li class="more-button-submenu-item quick-change">
                        Quick change
                    </li>
                    <li class="more-button-submenu-item more-button-submenu-item" data-bs-toggle="modal"
                        data-bs-target="#modifyMedicine">
                        Advance
                    </li>
                </ul>
            </div>
        </li>
        <li>
            <img src="./images/icons/cart.png" class="cart-btn cart-images" style="cursor: pointer;">
            <img src="./images/icons/trash.png" class="trash-image" alt="" data-bs-toggle="modal" data-bs-target="#delete-confirm-modal">
        </li>
    </ul>`
        }
        checkAccount()
    }
});

$(document).on("click", ".decrease-amount-image", function() {
    let amount = parseInt($(this).parent("li").find("div").text())
    if (amount > 1) {
        $(this).parent("li").find("div").html(amount - 1)
    }
})

$(document).on("click", ".increase-amount-image", function() {
    let amount = parseInt($(this).parent("li").find("div").text())
    $(this).parent("li").find("div").html(amount + 1)
})

$(document).on("click", ".checkbox-filter", function() {
    if ($(this).find("img").attr("src") === "./images/icons/checked.png") {
        if (localStorage.getItem("Group") === "Both"){
            if ($(this).parent("li").find("span").text() === "Đông dược") localStorage.setItem("Group", "Tân dược");
            else localStorage.setItem("Group", "Đông dược");
        }
        else localStorage.setItem("Group", "none");
    }
    else {
        if (localStorage.getItem("Group") === "none") localStorage.setItem("Group", $(this).parent("li").find("span").text());
        else localStorage.setItem("Group", "Both");
    }
    addItem(itemDisplayAtATime)
    window.location.reload()
});

$(document).on("click", ".dropdown-item-type", function() {
    localStorage.setItem("Type", $(this).text())
    addItem()
    window.location.reload()
});

$(document).on("click", ".dropdown-item-sort", async function() {
    localStorage.setItem("sort-type", $(this).text())
    addItem(itemDisplayAtATime)
    window.location.reload()
});

function getFilter() {
    return {
        type: localStorage.getItem("Type"),
        group: localStorage.getItem("Group"),
        sortType: localStorage.getItem("sort-type")
    }
}

async function addCompany() {
    let res = await fetch('http://localhost:8080/producer/getProducers', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    if (res.ok) {
        let data = await res.json();
        let result = Object.values(data);
        for (let i = 0; i < result.length; i++) {
            document.getElementById("company-select").innerHTML += `
      <option>${result[i].split(" -- ")[0]}</option>
      `
        }
    }

}
async function addItem(item) {
    removeAllItem()
    $(".spinner").css("display", "block")
    let all = "";
    let Drug = getFilter();
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
            all = formatData(result)
            localStorage.setItem("data", all);
            $(".spinner").css("display", "none")
            let page = 1
            let itemDisplay = itemDisplayAtATime
            let data = localStorage.getItem("data")
            let itemIndex = (page - 1) * itemDisplay
            let wrapper = document.querySelector(".medicines")
            for (let i = itemIndex; i < itemIndex + itemDisplay; i++) {
                if (data.split("\n").length-1 === i) {
                    break
                } else {
                    wrapper.innerHTML += `<ul class="medicine-item">
              <li class="id">${data.split("\n")[i].split("&&")[0]}</li>
              <li class="name">${data.split("\n")[i].split("&&")[1]}</li>
              <li class="changeAble stock">${data.split("\n")[i].split("&&")[10]}</li>
              <li class="changeAble amount"><img class = "decrease-amount-image" src="images/icons/decrease-amount-image.png" alt=""><div>1</div><img class = "increase-amount-image" src="./images/icons/increase-amount-image.png" alt=""></li>
              <li class="changeAble price">${data.split("\n")[i].split("&&")[9]}</li>
              <li>
                  <div class="done">Done</div>
                  <img src="./images/icons/more.png" class="more-image more-button" style="cursor: pointer;">
                  <div class="more-button-submenu-wrapper">
                      <ul class="more-button-submenu">
                          <li class="more-button-submenu-item quick-change">
                              Quick change
                          </li>
                          <li class="more-button-submenu-item more-button-submenu-item" data-bs-toggle="modal"
                              data-bs-target="#modifyMedicine">
                              Advance
                          </li>
                      </ul>
                  </div>
              </li>
              <li>
                  <img src="./images/icons/cart.png" class="cart-btn cart-images" style="cursor: pointer;">
                  <img src="./images/icons/trash.png" class="trash-image" alt="" data-bs-toggle="modal" data-bs-target="#delete-confirm-modal">
              </li>
          </ul>`
                }
            }
        }
    } catch (e) {

    }

    checkAccount();
}
