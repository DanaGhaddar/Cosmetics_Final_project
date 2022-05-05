<?php

include('db_info.php');

if(isset($_GET["username"]) && $_GET["username"] != ""){
	$username= $_GET["username"];
}else{
	die(" fix name ");
}
if(isset($_GET["productname"]) && $_GET["productname"] != ""){
	$productname= $_GET["productname"];
}else{
	die(" fix productname ");
}
if(isset($_GET["quantity"]) && $_GET["quantity"] != ""){
	$quantity= $_GET["quantity"];
}else{
	die(" fix quantity ");
}
if(isset($_GET["price"]) && $_GET["price"] != ""){
	$price= $_GET["price"];
}else{
	die(" fix price ");
}


$query = $mysqli->prepare("INSERT INTO products_purchases (username,productname,quantity,price) VALUES(?,?,?,?)");
$query->bind_param("ssss", $username,$productname,$quantity,$price);
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
