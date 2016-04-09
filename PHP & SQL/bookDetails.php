<?php
include "connect.php";

if(isset($_POST["book_id"])) {
  $bookID = $_POST["book_id"];

  $sql = "SELECT * FROM book_table WHERE ID LIKE $bookID";
  $query = mysqli_query($conn, $sql) or die(mysql_error());

  $jsonData = array();
  if(mysqli_num_rows($query) > 0) {
    $jsonData["book_details"] = array();

    while($data = mysqli_fetch_array($query)) {
      $datas = array(
        "pdf_id" => $data["PDF_ID"],
        "book_accessionno" => $data["ACCESSIONNO"],
        "book_author" => $data["AUTHOR"],
        "book_title" => $data["TITLE"]
      );

      array_push($jsonData["book_details"], $datas);
    }

    echo json_encode($jsonData);
  }
}
?>
	