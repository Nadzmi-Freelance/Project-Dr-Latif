<?php
include "connect.php";

if(isset($_POST)) {
  $jsonResponse = array();
  $jsonResponse["books"] = array();

  $selectSQL = "SELECT * FROM book_table";
  $selectQuery = mysqli_query($conn, $selectSQL);

  if(!empty($selectQuery)) {
    if(mysqli_num_rows($selectQuery) > 0) {
      while($data = mysqli_fetch_array($selectQuery)) {
        $result = array(
          "message" => "success",
          "book_id" => $data["ID"],
          "book_accessionno" => $data["ACCESSIONNO"],
          "book_title" => $data["TITLE"],
          "book_author" => $data["AUTHOR"],
          "book_edition" => $data["EDITION"]
        );

        array_push($jsonResponse["books"], $result);
      }
    } else {
      $result = array("message" => "error_record"); // record not found
      array_push($jsonResponse["books"], $result);
    }
  } else {
    $result = array("message" => "error"); // an error has occurred
    array_push($jsonResponse["books"], $result);
  }

  echo json_encode($jsonResponse);
}
?>
