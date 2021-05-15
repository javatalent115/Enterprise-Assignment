

function checkAccount() {
  if (localStorage.getItem("accountType") == "guest") {
    $("li:nth-child(6)").css("display", "none")
    $("li:nth-child(7)").css("display", "none")
    $(".add-image").css("display", "none")
    $(".cart-nav").css("display", "none")
    $(".amount").css("display", "none")
  }
  else if (localStorage.getItem("accountType") == "user") {
    $("li:nth-child(6)").css("display", "none")
    $(".add-image").css("display", "none")
    $(".more-button").css("display", "none")
  }
  else {
    $(".amount").css("display", "none")
    $(".trash-image").css("display", "block")
    $(".cart-images").css("display", "none")
    $(".cart-nav").css("display", "none")
  }
}
$("#medicine2").on('keyup', function (e) {
  if (e.key === 'Enter' || e.keyCode === 13) {
    console.log("hello")
    $(".search-button-2").click()
  }
});


function intinialize() {
  document.querySelector(".item-count").innerHTML = parseInt(0 + localStorage.getItem("cart"))
  checkAccount();
  // checkPagination()
}
intinialize()

//Initialize cart-item
let listItem = localStorage.getItem('cart-item')
  ? JSON.parse(localStorage.getItem('cart-item'))
  : []
//if add to cart btn clicked
$(document).on("click", ".cart-btn", function () {
  console.log("hello")
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

    setTimeout(function () {
      count++;
      localStorage.setItem("cart", count)
      $(".cart-nav .item-count").text(localStorage.getItem("cart"));

    }, 1500);
    let id = $(this).parent("li").parent("ul").find(".id").text()
    let name = $(this).parent("li").parent("ul").find(".name").text()
    let amount = $(this).parent("li").parent("ul").find(".amount").text()
    let price = $(this).parent("li").parent("ul").find(".price").text()
    var obj = {
      id: id,
      name: name,
      amount: amount,
      price: price
    }
    listItem.push(obj)
    var myJSON = JSON.stringify(listItem)
    localStorage.setItem("cart-item", myJSON)
    imgclone.animate({
      'width': 0,
      'height': 0
    }, function () {
      $(this).detach()
    });
  }
});

var length = document.querySelectorAll(".more-button-submenu-wrapper")
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

$(document).on("click", ".more-button", function () {
  event.preventDefault()
  if ($(this).parent("li").find(".more-button-submenu-wrapper").css("display") == "block") {
    $(this).parent("li").find(".more-button-submenu-wrapper").css("display", "none")
  }
  else {
    $(this).parent("li").find(".more-button-submenu-wrapper").css("display", "block")
  }
});

$(document).on("click", ".more-button-submenu-item", function () {
  event.preventDefault()
  $(this).parent("ul").parent("div").css("display", "none")
});

$(document).on("click", ".quick-change", function () {
  event.preventDefault()
  $(this).parent("ul").parent("div").parent("li").parent("ul").find(".changeAble").attr("contenteditable", "true")
  $(this).parent("ul").parent("div").parent("li").parent("ul").find(".changeAble").css({
    "border": "1px solid black"
  }
  )
  $(this).parent("ul").parent("div").parent("li").find("img").css("display", "none")
  $(this).parent("ul").parent("div").parent("li").find(".done").css("display", "block")
});

$(document).on("click", ".done", function () {
  event.preventDefault()
  $(this).css("display", "none")
  $(this).parent("li").find("img").css("display", "inline-block")
  $(this).parent("li").parent("ul").find(".changeAble").css({
    "border": "none"
  })
  $(this).parent("li").parent("ul").find(".changeAble").attr("contenteditable", "false")
  $(this).parent("li").parent("ul").find(".changeAble")
  let id = $(this).parent("li").parent("ul").find(".id").text()
  let name = $(this).parent("li").parent("ul").find(".name").text()
  let stock = $(this).parent("li").parent("ul").find(".stock").text()
  let price = $(this).parent("li").parent("ul").find(".price").text()
  // console.log(id)
  // console.log(name)
  // console.log(stock)
  // console.log(price)
  // var obj = {
  //   id: id,
  //   name: name,
  //   stock: stock,
  //   price: price
  // }
  // var myJSON = JSON.stringify(obj)
  // localStorage.setItem("json", myJSON)
});

$(document).on("click", ".trash-image", function () {
  event.preventDefault()
  $(this).parent("li").parent("ul").remove()
});

$(".add-image").click(function () {
  event.preventDefault()
  $(".medicines").append(`
  
  <ul class="medicine-item">
                        <li class="changeAble id" contenteditable="true"></li>
                        <li class="changeAble name" contenteditable="true"></li>
                        <li class="changeAble stock" contenteditable="true"></li>
                        <li class="changeAble amount" contenteditable="true"><div>1</div><img class = "increase-amount-image" src="./images/increase-amount-image.png" alt=""></li>
                        <li class="changeAble price" contenteditable="true"></li>
                        <li>
                            <div class="done">Done</div>
                            <img src="./images/more.png" class="more-image more-button" style="cursor: pointer;"">
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
                            <img src="./images/cart.png" class="cart-btn cart-images" style="cursor: pointer;">
                            <img src="./images/trash.png" class="trash-image" alt="">
                        </li>
                    </ul>
  `)
  let lastChild = document.querySelectorAll(".changeAble").length
  console.log(lastChild)
  console.log(document.querySelectorAll(".changeAble").length - 4)
  for (let i = lastChild - 5; i < lastChild; i++) {
    document.querySelectorAll(".changeAble")[i].setAttribute("style", "border: 1px solid black")
  }
  console.log(document.querySelectorAll(".done").length)
  document.querySelectorAll(".done")[document.querySelectorAll(".done").length - 1].setAttribute("style", "display:block")
  document.querySelectorAll(".more-button")[document.querySelectorAll(".more-button").length - 1].setAttribute("style", "display:none")
  checkAccount()
});
function reset() {
  window.localStorage.clear();
  document.querySelector(".item-count").innerHTML = parseInt(0 + localStorage.getItem("cart"))
}


async function addAllItem() {
  let res = await fetch('http://localhost:8080/api/getDrugs', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  });
  let all = "";
  if (res.ok) {
    let data = await res.json();
    let result = Object.values(data);
    for (let i = 0; i < result.length; i++) {
      name.push(result[i].split(" -- ")[1]);
      all += result[i].split(" -- ")[0] + "&&" + result[i].split(" -- ")[1] + "&&" + result[i].split(" -- ")[2] + "&&" + result[i].split(" -- ")[3] + "&&" + result[i].split(" -- ")[4] + "&&" + result[i].split(" -- ")[5]
        + "&&" + result[i].split(" -- ")[6] + "&&" + result[i].split(" -- ")[7] + "&&" + result[i].split(" -- ")[8] + "&&" + result[i].split(" -- ")[9] + "&&" + result[i].split(" -- ")[10] + "&&" + result[i].split(" -- ")[11] + "\n";
    }
  }
  localStorage.setItem("data", all);
  let wrapper = document.querySelector(".medicines")
  let data = localStorage.getItem("data")
  for (let i = 0; i < 10; i++) {
    wrapper.innerHTML += `<ul class="medicine-item">
    <li class="changeAble id">${data.split("\n")[i].split("&&")[0]}</li>
    <li class="changeAble name">${data.split("\n")[i].split("&&")[1]}</li>
    <li class="changeAble stock">${data.split("\n")[i].split("&&")[9]}</li>
    <li class="changeAble amount"><div>4</div><img class = "increase-amount-image" src="./images/increase-amount-image.png" alt=""></li>
    <li class="changeAble price">${data.split("\n")[i].split("&&")[10]}</li>
    <li>
        <div class="done">Done</div>
        <img src="./images/more.png" class="more-image more-button" style="cursor: pointer;">
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
        <img src="./images/cart.png" class="cart-btn cart-images" style="cursor: pointer;">
        <img src="./images/trash.png" class="trash-image" alt="">
    </li>
</ul>`
  }
  checkAccount();
}

addAllItem()

function removeAllItem() {
  let x = document.querySelectorAll(".medicine-item").length - 1
  for (let i = x; i > 0; i--) {
    document.querySelectorAll(".medicine-item")[i].remove()
  }
}

$(document).on("click", ".search-button-2", function () {
  let searchName = document.getElementById("medicine2").value
  document.getElementById("medicine2").value = ""
  if (searchName == 0) {
    removeAllItem()
    addAllItem()
  }
  else {
    let data = localStorage.getItem("data")
    removeAllItem()
    let index = 0
    let found = false
    let lastItem = data.split("\n").length
    for (let i = 0; i < lastItem; i++) {
      if (searchName == data.split("\n")[i].split("&&")[1]) {
        index = i
        found = true
        break
      }
    }
    if (found) {
      let wrapper = document.querySelector(".medicines")
      wrapper.innerHTML += `<ul class="medicine-item">
        <li class="changeAble id">${data.split("\n")[index].split("&&")[0]}</li>
        <li class="changeAble name">${data.split("\n")[index].split("&&")[1]}</li>
        <li class="changeAble stock">${data.split("\n")[index].split("&&")[9]}</li>
        <li class="changeAble amount"><div>1</div><img class = "increase-amount-image" src="./images/increase-amount-image.png" alt=""></li>
        <li class="changeAble price">${data.split("\n")[index].split("&&")[10]}</li>
        <li>
            <div class="done">Done</div>
            <img src="./images/more.png" class="more-image more-button" style="cursor: pointer;">
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
            <img src="./images/cart.png" class="cart-btn cart-images" style="cursor: pointer;">
            <img src="./images/trash.png" class="trash-image" alt="">
        </li>
    </ul>`
      checkAccount()
    }
    else {
      console.log("Can not find drug")
    }
  }
});



/* * * * * * * * * * * * * * * * *
 * Pagination
 * javascript page navigation
 * * * * * * * * * * * * * * * * */

var Pagination = {

  code: '',

  // --------------------
  // Utility
  // --------------------

  // converting initialize data
  Extend: function (data) {
    data = data || {};
    Pagination.size = data.size || 300;
    Pagination.page = data.page || 30;
    Pagination.step = data.step || 3;
  },

  // add pages by number (from [s] to [f])
  Add: function (s, f) {
    for (var i = s; i < f; i++) {
      Pagination.code += '<a class ="page">' + i + '</a>';
    }
  },

  // add last page with separator
  Last: function () {
    Pagination.code += '<i>...</i><a class = "page">' + Pagination.size + '</a>';
  },

  // add first page with separator
  First: function () {
    Pagination.code += '<a class = "page">1</a><i>...</i>';
  },



  // --------------------
  // Handlers
  // --------------------

  // change page
  Click: function () {
    Pagination.page = +this.innerHTML;
    Pagination.Start();
  },

  // previous page
  Prev: function () {
    Pagination.page--;
    if (Pagination.page < 1) {
      Pagination.page = 1;
    }
    Pagination.Start();
  },

  // next page
  Next: function () {
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
  Bind: function () {
    var a = Pagination.e.getElementsByTagName('a');
    for (var i = 0; i < a.length; i++) {
      if (+a[i].innerHTML === Pagination.page) a[i].className = 'current';
      a[i].addEventListener('click', Pagination.Click, false);
    }
  },

  // write pagination
  Finish: function () {
    Pagination.e.innerHTML = Pagination.code;
    Pagination.code = '';
    Pagination.Bind();
  },

  // find pagination type
  Start: function () {
    if (Pagination.size < Pagination.step * 2 + 6) {
      Pagination.Add(1, Pagination.size + 1);
    }
    else if (Pagination.page < Pagination.step * 2 + 1) {
      Pagination.Add(1, Pagination.step * 2 + 4);
      Pagination.Last();
    }
    else if (Pagination.page > Pagination.size - Pagination.step * 2) {
      Pagination.First();
      Pagination.Add(Pagination.size - Pagination.step * 2 - 2, Pagination.size + 1);
    }
    else {
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
  Buttons: function (e) {
    var nav = e.getElementsByTagName('a');
    nav[0].addEventListener('click', Pagination.Prev, false);
    nav[1].addEventListener('click', Pagination.Next, false);
  },

  // create skeleton
  Create: function (e) {

    var html = [
      '  <a class = "page"><img src="./images/arrow-left.png" alt=""></a>', // previous button
      '<span ></span>',  // pagination container
      '  <a class = "page"><img src="./images/arrow-right.png" alt=""></a>'  // next button
    ];

    e.innerHTML = html.join('');
    Pagination.e = e.getElementsByTagName('span')[0];
    Pagination.Buttons(e);
  },

  // init
  Init: function (e, data) {
    Pagination.Extend(data);
    Pagination.Create(e);
    Pagination.Start();
  }
};

var init = function () {
  Pagination.Init(document.getElementById('pagination'), {
    size: 160, // pages size
    page: 1,  // selected page
    step: 2   // pages before and after current
  });
};

document.addEventListener('DOMContentLoaded', init, false);
let page = 0

$(document).on("click", ".page", function () {
  page = document.querySelector(".current").innerHTML
  console.log(page)

  let itemDisplayAtATime = 10
  let data = localStorage.getItem("data")
  let itemIndex = (page - 1) * itemDisplayAtATime
  let wrapper = document.querySelector(".medicines")
  for (let i = itemIndex; i < itemIndex + itemDisplayAtATime; i++) {
    if (data.split("\n")[i].split("&&")[0] == 0) {
      break
    }
    else {
      wrapper.innerHTML += `<ul class="medicine-item">
        <li class="changeAble id">${data.split("\n")[i].split("&&")[0]}</li>
        <li class="changeAble name">${data.split("\n")[i].split("&&")[1]}</li>
        <li class="changeAble stock">${data.split("\n")[i].split("&&")[9]}</li>
        <li class="changeAble amount">1 pack</li>
        <li class="changeAble price">${data.split("\n")[i].split("&&")[10]}</li>
        <li>
            <div class="done">Done</div>
            <img src="./images/more.png" class="more-image more-button" style="cursor: pointer;">
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
            <img src="./images/cart.png" class="cart-btn cart-images" style="cursor: pointer;">
            <img src="./images/trash.png" class="trash-image" alt="">
        </li>
    </ul>`
    }
    checkAccount()
  }
});

$(document).on("click",".increase-amount-image",function(){
  let amount = parseInt($(this).parent("li").find("div").text())
  $(this).parent("li").find("div").html(amount+1)
  console.log($(this).parent("li").find("div").text())
})





