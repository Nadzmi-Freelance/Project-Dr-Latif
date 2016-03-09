<?php
include "connect.php";

if(isset($_POST["request"])) {
  $book_id = $_POST["book_id"];

  $sql = "SELECT * FROM book_table WHERE ID LIKE $book_id";
  $query = mysqli_query($conn, $sql) or die(mysql_error());

  if(!empty($query)) {
    if(mysqli_num_rows($query) > 0) {
      $data = mysqli_fetch_array($query);

      $response = array();
      $response["bookDetails"] = array(
        "book_id" => $data["ID"],
        "book_accessionno" => $data["ACCESSIONNO"],
        "book_author" => $data["AUTHOR"],
        "book_title" => $data["TITLE"],
        "book_edition" => $data["EDITION"]
      );
    }
  }
}

echo json_encode($response);
?>
