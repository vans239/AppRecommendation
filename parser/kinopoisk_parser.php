<?
$id= $_GET['id'];
$col= $_GET['col'];
$filename=$id.'-'.($id+$col).'.txt';
print $filename;
$fp = fopen($filename, 'w');
set_time_limit(2000);
srand(time());

for($i=$id;$i<$id+$col;$i++){

$url = "http://m.kinopoisk.ru/movie/$i/";
print $url;

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url); // Указываем откуда затащить содержимое в наш объект.
curl_setopt($ch, CURLOPT_HEADER, 0);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_COOKIEFILE, "cookies.txt"); 
curl_setopt($ch, CURLOPT_COOKIEJAR,  "cookies.txt");
curl_setopt($ch, CURLOPT_USERAGENT, "Opera/9.80 (Windows NT 5.1; U; ru) Presto/2.9.168 Version/11.50"); // Прикинемся каким-нибудь популярным браузером.
$html = curl_exec($ch); // Получаем объект-страницу в виде длинной символьной строки.
//print $html;
curl_close($ch);

//////////////////

$n=strpos($html,'<p class="title">');
$n2_sub=substr($html, $n, 10000);
$n2=strpos($n2_sub,'</p>');
$title=substr($n2_sub, 0, $n2+4);
print '<br> n2sub= '.$title.'<br>';


$n=strpos($html,'<div class="block film">');
$n2_sub=substr($html, $n, 10000);
$n2=strpos($n2_sub,'</div>');
$body=substr($n2_sub, 0, $n2+6);
print '<br> n2sub= '.$body.'<br>';


$test = fwrite($fp, '!%%%%%!');
$test = fwrite($fp, $i);
$test = fwrite($fp, '%%%%%');
$test = fwrite($fp, $title);
$test = fwrite($fp, '%%%%%');
$test = fwrite($fp, $body);
$test = fwrite($fp, '!%%%%%!');
$sl=rand()%2/2;
print '<br><br><b>'.$sl.'</b><br><br>';
sleep($sl);

}
fclose($fp);
?>
<head>
<meta http-equiv="content-type" content="text/html; charset=windows-1251" />
    <title>Парсер</title>
</head>
<body>



</body>




