<?php
include "connect.php";

if(isset($_POST)) {
  $pdfid = $_POST["pdf_id"];

  $sql = "SELECT pdf_url FROM PDF WHERE pdf_id LIKE $pdfid";
  $query = mysqli_query($conn, $sql) or die(mysql_error());

  $jsonData = array();
  if(mysqli_num_rows($query) > 0) {
    $jsonData["pdf"] = array();

    $rows = mysqli_fetch_array($query);
    $data = array(
      "pdf_url" => $rows["pdf_url"]
    );

    array_push($jsonData["pdf"], $data);
  } else {
    $jsonData["pdf"] = array();

    $rows = mysqli_fetch_array($query);
    $data = array(
      "pdf_url" => "no data"
    );

    array_push($jsonData["pdf"], $data);
  }

  echo json_encode($jsonData);
}
?>
		