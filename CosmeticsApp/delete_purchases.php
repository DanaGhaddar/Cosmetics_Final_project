<?php

include('db_info.php');

if(isset($_GET["name"]) && $_GET["name"] != ""){
	$name= $_GET["name"];
}else{
	die("fixx name ");
}

$query=$mysqli->prepare("DELETE FROM products_purchases WHERE username='".$name."';");
$query->execute();
$err_response=$query->error;

if($err_response!=null){
$final_value['error']=$err_response;
}
else{
$final_value['error']="";
}

$json = json_encode($final_value);
print '['.$json.']';

?>