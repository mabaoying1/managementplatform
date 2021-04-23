<body style="scroll: no">
<form method="post" id="form1" name="form1">
<div style="width: 200px; height: 443px; float: left; overflow: hidden;">
<div id="leftdiv" style="width: 220px; height: 423px; overflow-y: auto; overflow-x: hidden;"
onscroll="funcsrcoll1()">
<div id="divMenu" style="position: fixed; margin-top: 0px; width: 200px; height: 423px;">
<table cellpadding="0" cellspacing="1" width="100%" border="0" class="Table_List">
<tr class="ListTableHeader">
<td width="110px" nowrap="nowrap" align="center">
部门
</td>
<td width="90px" nowrap="nowrap" align="center">
下属部门
</td>
</tr>
</table>
</div>
<table cellpadding="0" cellspacing="1" width="100%" border="0" class="Table_List">
</table>
</div>
</div>
<div id="menudiv" style="width: 80%; height: 23px;margin-left:100px; margin-top: 0px; overflow: hidden;">
<div id="Divtop" style="width: 100%; height: 43px; overflow-x: auto; overflow-y: hidden;"
onscroll="funcsrcoll3()">
<script type="text/javascript">
window.onload = function() {
var width = $("#menudiv").width();
$("#menudiv").width(width - 20);
$("#Divtop").width(width);
}
</script>
<div id="divMain" style="margin-left:0px; width: 100%; height: 100%;" >
<table cellpadding="0" cellspacing="1" width="150%" border="0" class="Table_List">
<tr class="ListTableHeader">
<td width="10%" nowrap="nowrap" align="center">
有效
</td>
<td width="10%" nowrap="nowrap" align="center">
暂缓
</td>
<td width="10%" nowrap="nowrap" align="center">
未知
</td>
<td width="8%" nowrap="nowrap" align="center">
我租
</td>
<td width="8%" nowrap="nowrap" align="center">
他租
</td>
<td width="8%" nowrap="nowrap" align="center">
我售
</td>
<td width="8%" nowrap="nowrap" align="center">
他售
</td>
<td width="8%" nowrap="nowrap" align="center">
撤单
</td>
<td width="8%" nowrap="nowrap" align="center">
电话错误
</td>
<td width="8%" nowrap="nowrap" align="center">
待确认
</td>
<td width="10%" nowrap="nowrap" align="center">
已驳回
</td>
</tr>
</table>
</div>
</div>
</div>
<div id="rightdiv" style="width: 80%; height: 420px; float: left; overflow: scroll"
onscroll="funcsrcoll2()">
<div id="divinner" style="float:left; width: 100%;">
<script type="text/javascript">
window.onload = function() {
var width = $("#menudiv").width();
$("#rightdiv").width(width + 20);
$("#divinner").width(width);
}
</script>
<table cellpadding="0" cellspacing="1" width="150%" border="0" class="Table_List">
</table>
</div>
</div>
</form>
</body>
</html>