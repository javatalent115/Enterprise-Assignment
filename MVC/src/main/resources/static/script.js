let count = 0;
//if add to cart btn clicked
$('.cart-btn').on('click', function () {
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
      $(".cart-nav .item-count").text(count);
    }, 1500);

    imgclone.animate({
      'width': 0,
      'height': 0
    }, function () {
      $(this).detach()
    });
  }
});

localStorage.setItem("more-button-submenu-visible", false)
var length = document.querySelectorAll(".more-button-submenu-wrapper")
for (let i = 0; i < length.length; i++) {
  document.querySelectorAll(".more-button-submenu-item")[i].setAttribute("style", "display:none")
}
$(".more-button").click(function () {
  if (localStorage.getItem("more-button-submenu-visible") == "true") {
    localStorage.setItem("more-button-submenu-visible", false)
    $(this).parent("li").children("div").children("div").css("display", "none")
  }
  else {
    localStorage.setItem("more-button-submenu-visible", true)
    $(this).parent("li").children("div").children("div").css("display", "block")

  }
});

$(".more-button-submenu-item").click(function (){
  

});