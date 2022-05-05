<?php


include("db_info.php");



$query = $mysqli->prepare("SELECT * FROM products");

	$query->execute();
    $results = $query->get_result();
    $response=[];
   while($data = $results->fetch_assoc()){
       $response[]=$data;       
   }

$json = json_encode($response);

print $json;

?>