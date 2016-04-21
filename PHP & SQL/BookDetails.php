<?php
include "connect.php";

if(isset($_POST)) {
  $inBookID = $_POST["inBookId"];

  $jsonData = array();
  $jsonData["book_details"] = array();

  $bookDetailSQL = "SELECT * FROM book_table WHERE ID LIKE $inBookID";
  $bookDetailQuery = mysqli_query($conn, $bookDetailSQL) or die(mysql_error());

  if(!empty($bookDetailQuery)) {
    if(mysqli_num_rows($bookDetailQuery) > 0) {
      while($row = mysqli_fetch_array($bookDetailQuery)) {
        $result = array(
          "message" => "success",
          "pdf_id" => $row["PDF_ID"],
          "book_accessionno" => $row["ACCESSIONNO"],
          "book_author" => $row["AUTHOR"],
          "book_title" => $row["TITLE"]
        );

        array_push($jsonData["book_details"], $result);
      }
    } else $result = array("message" => "The book cannot be found in the database.");
  } else $result = array("message" => "An error has occurred");

  echo json_encode($jsonData);
}
?>
