<?php
include "connect.php";

if(isset($_POST)) {
  $inPDFId = $_POST["inPDFId"];

  $jsonData = array();
  $jsonData["pdf"] = array();

  $PDFUrlSQL = "SELECT pdf_url FROM PDF WHERE pdf_id LIKE $inPDFId";
  $PDFUrlQuery = mysqli_query($conn, $PDFUrlSQL);

  if(!empty($PDFUrlQuery)) {
    if(mysqli_num_rows($PDFUrlQuery) > 0) {
      $rows = mysqli_fetch_array($PDFUrlQuery);
      $result = array(
        "message" => "success",
        "pdf_url" => $rows["pdf_url"]
      );
    } else $result = array("message" => "no data");
  } else $result = array("message" => "error");

  array_push($jsonData["pdf"], $result);
  echo json_encode($jsonData);
}
?>
