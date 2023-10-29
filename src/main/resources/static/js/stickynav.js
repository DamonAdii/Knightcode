window.onscroll = function() {myFunction()};

var header = document.getElementById("myHeader");
var leftHeader = document.getElementById("myLeftNav");
/*var navimg = document.getElementById("stickyimg");*/
var aboutLeft = document.getElementById("myLeftNavAbout");
var sticky = header.offsetTop;

function myFunction() {
  if (window.pageYOffset > sticky) {
    header.classList.add("sticky");
    leftHeader.classList.add("fix-side");
    aboutLeft.classList.add("fix-side-left");
  } else {
    header.classList.remove("sticky");
    leftHeader.classList.remove("fix-side");
    aboutLeft.classList.remove("fix-side-left");
  }
}