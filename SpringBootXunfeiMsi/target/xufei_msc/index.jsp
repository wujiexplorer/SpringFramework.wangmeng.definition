<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script src="iview/vue.min.js"></script>
<link rel="stylesheet" href="iview/iview.css">
<script src="iview/iview.min.js"></script>
<style type="text/css">
[v-cloak] {
		  display: none;
		}
body{background:url(image/ChMkJlqYtymIb9saAAd25E40JYgAAlEMgEfzWkAB3b8371.jpg)no-repeat fixed top;text-align:center;}
div{width:778px;margin:0 auto}
.container {
     max-width: 32rem;
     margin-left: auto;
     margin-right: auto;
}
.aplayer .aplayer-info .aplayer-music .aplayer-author{
	color: #fff;
}
</style>
<script>
	$(function () {	
		var vm = new Vue({
			el: '#app',
			data:{
				title:'讯飞语音合成',
				message: '享受科技，热爱生活'
			},
			methods: {
				convert: function () {
					if(vm.message===""){vm.open("请输入文字");return}
					$.ajax({
						url : "xunfeiConvert",
						async : false,
						type : 'post',
						data : {'time' : (new Date()).toString(),'message':vm.message},
						success : function(result) {
						    $("#audio").attr("src",result);
						    document.getElementById("audio").play();
						},
						error : function() {
							alert("网络异常");
						}
					});
				},open: function (nodesc) {
	                this.$Notice.open({
	                    title: '温馨提示',
	                    desc: nodesc
	                });
	            }
			}
		});						
	});
</script>
</head>
<body>
<div id="app" v-cloak>
	<div>
		<h1 v-text="title"></h1>
		<i-input v-model="message" type="textarea" :autosize="{minRows: 8,maxRows: 20}" placeholder="请输入..."></i-input>
	</div>
	<div style="margin-top:10px;">
	   <i-button @click="convert" type="primary">合成播放</i-button>
	</div>
	<div style="display: none">
	   <audio id="audio" controls="controls" ></audio>
	</div>
</div>
</body>
</html>
