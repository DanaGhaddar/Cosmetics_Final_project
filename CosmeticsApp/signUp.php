<?php

include('db_info.php');


if(isset($_GET["name"]) && $_GET["name"] != ""){
	$name= $_GET["name"];
}else{
	die(" fix name");
}
if(isset($_GET["age"]) && $_GET["age"] != ""){
	$age= $_GET["age"];
}else{
	die(" fix age");
}
if(isset($_GET["location"]) && $_GET["location"] != ""){
	$location= $_GET["location"];
}else{
	die(" fix loc");
}
if(isset($_GET["password"]) && $_GET["password"] != ""){
	$password=hash("sha256",$_GET['password']);
}else{
	die(" fix pass");
}

$query = $mysqli->prepare("INSERT INTO users (name,age,location,password) VALUES (?, ?, ?, ?)");
$query->bind_param("ssss", $name, $age, $location,$password);
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