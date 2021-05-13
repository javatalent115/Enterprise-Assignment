document.querySelector(".item-count").innerHTML =parseInt(0+localStorage.getItem("cart"))
//if add to cart btn clicked
$('.cart-btn').on('click', function () {
  let count = localStorage.getItem("cart");
  document.querySelector(".item-count").innerHTML =parseInt(0+localStorage.getItem("cart"))
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
      localStorage.setItem("cart",count)
      $(".cart-nav .item-count").text(localStorage.getItem("cart"));
    }, 1500);

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
$(".more-button").click(function () {
  if ($(this).parent("li").find(".more-button-submenu-wrapper").css("display") == "block") {
    $(this).parent("li").find(".more-button-submenu-wrapper").css("display", "none")
  }
  else {
    $(this).parent("li").find(".more-button-submenu-wrapper").css("display", "block")
  }
});

$(".more-button-submenu-item").click(function (){
    $(this).parent("ul").parent("div").css("display", "none")
});

$(".more-button-submenu-item").click(function (){
  localStorage.setItem("more-button-submenu-visible", false)
  $(this).parent("ul").parent("div").css("display", "none")
});

$(".quick-change").click(function (){
  $(this).parent("ul").parent("div").parent("li").parent("ul").find(".changeAble").attr("contenteditable","true")
  $(this).parent("ul").parent("div").parent("li").parent("ul").find(".changeAble").css({
    "border": "1px solid black"
  }
    )
    $(this).parent("ul").parent("div").parent("li").find("img").css("display","none")
    $(this).parent("ul").parent("div").parent("li").find(".done").css("display","block")
});

$(".done").click(function(){
  $(this).css("display","none")
  $(this).parent("li").find("img").css("display","inline-block")
  $(this).parent("li").parent("ul").find(".changeAble").css({
    "border":"none"
  })
  $(this).parent("li").parent("ul").find(".changeAble").attr("contenteditable","false")
});

function reset(){
  window.localStorage.clear();
  document.querySelector(".item-count").innerHTML =parseInt(0+localStorage.getItem("cart"))
}