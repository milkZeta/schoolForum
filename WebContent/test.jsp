<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<style>
 *{margin: 0;padding:0; }
 ul{list-style: none;}
 .banner{width: 600px;height: 300px;border: 2px solid #ccc;margin: 100px auto;position: relative;overflow: hidden;z-index: 1;}
 .img{position: absolute;top: 0;left: 0;}
 .des{position: absolute;bottom: 0;left: 0;z-index: -2;background: #ccc}
 .des li{float: left;width: 600px;}
 .img li{float: left;}
 .num{position: absolute;bottom: 10px;width: 100%;text-align: center;font-size: 0;}
 .num li{width: 10px;height: 10px;background:rgba(0,0,0,0.5);display: block;border-radius: 100%;display: inline-block;margin: 0 5px;cursor: pointer;}
 .btn{display: none;}
 .btn span{display: block;width: 50px;height: 100px;background: rgba(0,0,0,0.6);color: #fff;font-size: 40px;line-height: 100px;text-align: center;cursor:pointer;}
 .btn .prev{position: absolute;left: 0;top: 50%;margin-top: -50px;}
 .btn .next{position: absolute;right: 0;top: 50%;margin-top: -50px;}
 .num .active{background-color: #fff;}
 .hide{display: none;}
 </style>
</head>

<body>
 <div class="banner">
 <ul class="img">
 <li><a href="#"><img width="600" height="300" src="i../image/title.png" alt="第1张图片"></a></li>
 <li><a href="#"><img width="600" height="300" src="../image/title1.png" alt="第2张图片"></a></li>
 <li><a href="#"><img width="600" height="300" src="../image/title2.png" alt="第3张图片"></a></li>
 <li><a href="#"><img width="600" height="300" src="../image/snow.jpg" alt="第4张图片"></a></li>
 <li><a href="#"><img width="600" height="300" src="../image/square.jpg" alt="第5张图片"></a></li>
 </ul>
 <ul class="num"></ul>
 <ul class="des">
 <li>第一个</li>
 <li>第二个</li>
 <li>第三个</li>
 <li>第四个</li>
 <li>第五个</li>
 <li>第一个</li>
 </ul>
 <div class="btn">
 <span class="prev"><</span>
 <span class="next">></span>
 </div>
  
</div>
  
<script>
  
 $(function(){
  
 var i=0;
 var timer=null;
 for (var j = 0; j < $('.img li').length; j++) { //创建圆点
  $('.num').append('<li></li>')
 }
 $('.num li').first().addClass('active'); //给第一个圆点添加样式
  
 var firstimg=$('.img li').first().clone(); //复制第一张图片
 $('.img').append(firstimg).width($('.img li').length*($('.img img').width())); 
 //第一张图片放到最后一张图片后，设置ul的宽度为图片张数*图片宽度
 $('.des').width($('.img li').length*($('.img img').width()));
  
  
 // 下一个按钮
 $('.next').click(function(){
  i++;
  if (i==$('.img li').length) {
  i=1; //这里不是i=0
  $('.img').css({left:0}); //保证无缝轮播，设置left值
  };
   
  $('.img').stop().animate({left:-i*600},300);
  if (i==$('.img li').length-1) { //设置小圆点指示
  $('.num li').eq(0).addClass('active').siblings().removeClass('active');
  $('.des li').eq(0).removeClass('hide').siblings().addClass('hide');
  }else{
  $('.num li').eq(i).addClass('active').siblings().removeClass('active');
  $('.des li').eq(i).removeClass('hide').siblings().addClass('hide');
  }
   
 })
  
 // 上一个按钮
 $('.prev').click(function(){
  i--;
  if (i==-1) {
  i=$('.img li').length-2;
  $('.img').css({left:-($('.img li').length-1)*600});
  }
  $('.img').stop().animate({left:-i*600},300);
  $('.num li').eq(i).addClass('active').siblings().removeClass('active');
  $('.des li').eq(i).removeClass('hide').siblings().addClass('hide');
 })
  
 //设置按钮的显示和隐藏
 $('.banner').hover(function(){
  $('.btn').show();
 },function(){
  $('.btn').hide();
 })
  
 //鼠标划入圆点
 $('.num li').mouseover(function(){
  var _index=$(this).index();
  $('.img').stop().animate({left:-_index*600},150);
  $('.num li').eq(_index).addClass('active').siblings().removeClass('active');
  $('.des li').eq(_index).removeClass('hide').siblings().addClass('hide');
 })
  
 //定时器自动播放
 timer=setInterval(function(){
  i++;
  if (i==$('.img li').length) {
  i=1;
  $('.img').css({left:0});
  };
  
  $('.img').stop().animate({left:-i*600},300);
  if (i==$('.img li').length-1) { 
  $('.num li').eq(0).addClass('active').siblings().removeClass('active');
  $('.des li').eq(0).removeClass('hide').siblings().addClass('hide');
  }else{
  $('.num li').eq(i).addClass('active').siblings().removeClass('active');
  $('.des li').eq(i).removeClass('hide').siblings().addClass('hide');
  }
 },1000)
  
 //鼠标移入，暂停自动播放，移出，开始自动播放
 $('.banner').hover(function(){ 
  clearInterval(timer);
 },function(){
  timer=setInterval(function(){
  i++;
  if (i==$('.img li').length) {
  i=1;
  $('.img').css({left:0});
  };
  
  $('.img').stop().animate({left:-i*600},300);
  if (i==$('.img li').length-1) { 
  $('.num li').eq(0).addClass('active').siblings().removeClass('active');
  $('.des li').eq(0).removeClass('hide').siblings().addClass('hide');
  }else{
  $('.num li').eq(i).addClass('active').siblings().removeClass('active');
  $('.des li').eq(i).removeClass('hide').siblings().addClass('hide');
  }
 },1000)
 })
  
 })
</script>
 
</body>
</html>
