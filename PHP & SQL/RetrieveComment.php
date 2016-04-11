<?php
include "connect.php";

if(isset($_POST)) {
  $book_id = $_POST["book_id"];
  $comment_type = $_POST["comment_type"];

  // create array for json data
  $jsonData = array();
  $jsonData["comments"] = array();

  $retrieveSQL = "SELECT student.NAME, comment.DESCRIPTION
                  FROM student, comment
                  WHERE (
                      student.ID LIKE comment.USER_ID
                      AND
                      comment.COMMENT_TYPE LIKE $comment_type
                      AND
                      comment.BOOK_ID like $book_id
                  )

                  UNION

                  SELECT staff.NAME, comment.DESCRIPTION
                  FROM staff, comment
                  WHERE (
                      staff.ID LIKE comment.USER_ID
                      AND
                      comment.COMMENT_TYPE LIKE $comment_type
                      AND
                      comment.BOOK_ID like $book_id
                  )

                  UNION

                  SELECT stafflibrary.NAME, comment.DESCRIPTION
                  FROM stafflibrary, comment
                  WHERE (
                      stafflibrary.ID LIKE comment.USER_ID
                      AND
                      comment.COMMENT_TYPE LIKE $comment_type
                      AND
                      comment.BOOK_ID like $book_id
                  )";
  $retrieveQuery = mysqli_query($conn, $retrieveSQL);

  if(!empty($retrieveQuery)) {
    if(mysqli_num_rows($retrieveQuery) > 0) {
      while($row = mysqli_fetch_array($retrieveQuery)) {
        $result = array(
          "user_name" => $row["NAME"],
          "user_comment" => $row["DESCRIPTION"]
        );

        array_push($jsonData["comments"], $result);
      }
    } else $result = array("message" => "no_record"); // return no record in database
  } else $result = array("message" => "error"); // return error occurred

  echo json_encode($jsonData);
}
?>
