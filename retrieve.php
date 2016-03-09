<?php
include "connect.php";

$jsonResponse = array();

if(isset($_POST)) {
  $query = mysqli_query($conn, "SELECT * FROM book_table") or die(mysql_error());

  if(!empty($query)) {
    if(mysqli_num_rows($query) > 0) {
      $jsonResponse["books"] = array();

      while($data = mysqli_fetch_array($query)) {
        $result = array(
          "book_id" => $data["ID"],
          "book_accessionno" => $data["ACCESSIONNO"],
          "book_title" => $data["TITLE"],
          "book_author" => $data["AUTHOR"],
          "book_edition" => $data["EDITION"]
        );

        array_push($jsonResponse["books"], $result);
      }

      echo json_encode($jsonResponse);
    }
  }
}

mysqli_close($conn);
?>
