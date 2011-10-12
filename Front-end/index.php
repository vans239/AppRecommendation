


<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td>Введите названия игр через "," : </td>
    </tr>
    <tr>
      <td><form action="index.php" method="get">
  
  <input name="title" type="text" size="60"/>

<label>
<select name="num">
<OPTION VALUE="1">1
<OPTION VALUE="2">2
<OPTION VALUE="3">3
<OPTION VALUE="4">4
<OPTION VALUE="5">5
<OPTION VALUE="6">6
<OPTION VALUE="7">7
<OPTION VALUE="8">8
<OPTION VALUE="9">9
<OPTION VALUE="10">10
</select>
</label>
<label>
<input type="submit" name="Submit" value="Найти" />
</label>
</form></td>
    </tr>
    <tr>
      <td>Возможно Вам будут интересны следующие игры: </td>
    </tr>
    <tr>
      <td><? 
$title = $_GET['title'];

if(!empty($title)){
$num = $_GET['num'];

/*
$arra=explode(",",$title);
for($q=0;$q<count($arra);$q++)
{
print " - $arra[$q] - <br><br>";
}*/

print "
<table width='200' border='0'>";
for($i=1;$i<=$num;$i++){
print "
  <tr>
    <td width='10'>$i</td>
    <td>$title</td>
    <td width='10'>$like</td>
  </tr>";
  $title=$title."$i";
  }

print "</table>";
}

?></td>
    </tr>
  </table>

