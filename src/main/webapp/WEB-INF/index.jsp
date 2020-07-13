<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ninja Gold</title>

<style>

#result{
	margin-bottom: 20px;
}

#heading{
	display: inline;
}

#heading_box{
	display: inline-block;
	
}

button{
    margin-top: 10px;
    margin-right: 20px;
    margin-left: 60px;
}

h3{
    padding-left: 60px;
}

form{
     display: inline-block;
     width: 200px;
     height: 200px;
     padding: 60px;
     border: solid;
     align-content: center;
     margin-right: 20px;
}

textarea{
     color: green;
}

#winCondition{
color: blue;
padding-right: 50px;
}

#creator{
margin-left: 690px;
}

span{
color: black;
}

#buttom_right_gif{
height: 150px;
width: 200px;
float: right;
padding-top: 35px;
}

form{
background-color: cyan;
}

textarea{
background-color: yellow;
color: blue;
}

body{
background-image: url(data:image/jpeg;base64,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);
height: 680px;
}

a{
float: right;
}

img{
	width: 50px;
	height: 50px;
	display: inline-block;
	vertical-align: bottom;
	padding-bottom: 20px;
}

#box_coin{
display: inline-block;
}

#title{
 font-family: Algerian;
 display: inline-block;
 font-size: 40px;
padding-left: 300px;
}

form{
width: 180px;
}

#dino{
width: 150px;
height: 150px;
float: right;
display: inline-block;
}

button{
background-color: red;
color: white;
}

#scores_content{
background-color: green;
color: white;
padding-left: 35px;
}

#scores{
width: 150px;
height: 330px;
display: inline-block;
margin-left: 15px;
vertical-align: bottom;
}

#reset{
display: inline-block;
}

h2{
color: blue;
}

h3{
color: black;
}

#main_content{
display: inline-block;
}

</style>

<script>
    function playSound() {
         var sound = document.getElementById("audio");
         sound.play();
      }
</script>

</head>
<body>
    <audio id="audio" src="https://www.soundjay.com/misc/sounds/coin-drop-4.wav" autoplay="false"></audio>
	
    <h1 id="heading">Your Gold:</h1>
    <input id="heading_box" type="text" name="total" value=<c:out value="${total}"/>>
    <img alt="" src="https://images-platform.99static.com/7qa3U7A2HyhjidneoU9AlNJEObI=/500x500/top/smart/99designs-contests-attachments/49/49121/attachment_49121352">
	<h1 id="title">Ninja Gold</h1>
	<a href="/reset">RESTART</a>
    <img id="dino" alt="" src="https://cdna.artstation.com/p/assets/images/images/018/232/756/original/ingo-strohmeier-webp-net-gifmaker.gif?1558649250">
    <h3 id="winCondition"><span>Win Condition:</span> Find at least 100 gold to win!</h3>

	<div id="main_content">   
	    <form action="/controller" method="post">
	     <% for(int i = 0; i < (int) session.getAttribute("farm_remaining_attempts"); i++) { %>
				<img alt="" src="https://images-platform.99static.com/7qa3U7A2HyhjidneoU9AlNJEObI=/500x500/top/smart/99designs-contests-attachments/49/49121/attachment_49121352">
	       <% } %>
	        <h3>Farm</h3>
	        <input type="text" name="farm" value=<c:out value="${farm}"/>>
	         <input type="hidden" name="place" value="farm"/>
	        (earns 10-20 golds) <button type="submit" onclick="playSound();">Find Gold</button>
	    </form>
	    <form action="/controller" method="post">
	         <% for(int i = 0; i < (int) session.getAttribute("cave_remaining_attempts"); i++) { %>
				<img alt="" src="https://images-platform.99static.com/7qa3U7A2HyhjidneoU9AlNJEObI=/500x500/top/smart/99designs-contests-attachments/49/49121/attachment_49121352">
	       <% } %>
	        <h3>Cave</h3>
	        <input type="text" name="cave" value=<c:out value="${cave}"/>>
	         <input type="hidden" name="place" value="cave"/>
	        (earns 5-10 golds) <button type="submit" onclick="playSound();">Find Gold</button>
	    </form>
	    <form action="/controller" method="post">
	             <% for(int i = 0; i < (int) session.getAttribute("house_remaining_attempts"); i++) { %>
				<img alt="" src="https://images-platform.99static.com/7qa3U7A2HyhjidneoU9AlNJEObI=/500x500/top/smart/99designs-contests-attachments/49/49121/attachment_49121352">
	       <% } %>
	        <h3>House</h3>
	        <input type="text" name="house" value=<c:out value="${house}"/>>
	         <input type="hidden" name="place" value="house"/>
	        (earns 2-5 golds) <button type="submit" onclick="playSound();">Find Gold</button>
	    </form>
	    <form action="/controller" method="post">
	        <% for(int i = 0; i < (int) session.getAttribute("casino_remaining_attempts"); i++) { %>
				<img alt="" src="https://images-platform.99static.com/7qa3U7A2HyhjidneoU9AlNJEObI=/500x500/top/smart/99designs-contests-attachments/49/49121/attachment_49121352">
	       <% } %>
	        <h3>Casino</h3>
	        <input type="text" name="casino" value=<c:out value="${casino}"/>>
	         <input type="hidden" name="place" value="casino"/>
	        (earns/loses 0-50 golds) <button type="submit" onclick="playSound();">Find Gold</button>
	    </form>
	       
	    <div id="scores">
		    <h2>Score History</h2>
		    <textarea name="scores" id="scores_content" cols="15" rows="17">
		        <c:out value="${score_history}"/>
		    </textarea>
		 </div>
	</div>
	     
    <h4>Activities</h4>
    <textarea name="activities" id="" cols="177" rows="8">
        <c:out value="${activities}"/>
    </textarea>
    <img id="buttom_right_gif" alt="" src="https://img1.picmix.com/output/stamp/normal/4/1/1/6/1456114_d103c.gif" >
    <p id="creator">Created by Vardges Gharibyan</p>
</body>
</html>