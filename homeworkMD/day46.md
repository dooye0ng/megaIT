# Day 46 Homework

## Homework 01

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="script.js"></script>
  </head>

  <body>
    <div><img id="img" src="https://picsum.photos/600/400" alt="" /></div>
    <h1 id="text"></h1>
  </body>
</html>
```

```javascript
$(document).ready(function () {
  $("#img").hover(
    function () {
      $(this).animate({ width: "+=20px", height: "+=20px", opacity: "0.3" });
      $("#text").text("Hello, World!").fadeIn(300);
    },
    function () {
      $(this).animate({ width: "-=20px", height: "-=20px", opacity: "1" });
      $("#text").fadeOut(300);
    }
  );
});
```

## Homework 02
```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="script.js"></script>
    <style>
      #circle {
        border-radius: 50%;
        width: 50px;
        height: 50px;
        position: absolute;
        background-color: red;
        left: 50%;
        top: 50%;
      }
    </style>
  </head>
  <body>
    <div id="container">
      <div id="circle"></div>
    </div>
  </body>
</html>
```
```javascript
$(document).ready(function () {
  var posT = $("#circle").position()["top"];
  var posL = $("#circle").position()["left"];
  var x = 0;

  setInterval(function () {
    x += 0.03;
    posT += Math.sin(x) * 2;
    posL += Math.cos(x) * 2;
    $("#circle").css({ top: posT, left: posL });
  }, 1);
});
```