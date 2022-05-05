<?php

include('db_info.php');


$query=$mysqli->prepare("SELECT name,age,location FROM users");
$query->execute();
$response = $query->get_result();

$final_value;
while($data = $response->fetch_assoc()){
	$final_value[]=$data;       
}


$json = json_encode($final_value);

print $json;
?>