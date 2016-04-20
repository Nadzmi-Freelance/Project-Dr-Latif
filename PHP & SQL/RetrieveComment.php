<?php
include "connect.php";

if(isset($_POST)) {
  $inBookId = $_POST["inBookId"];
  $inCommentType = $_POST["inCommentType"];

  // create array for json data
  $jsonData = array();
  $jsonData["comments"] = array();

  $retrieveCommentSQL = "SELECT student.NAME, comment.DESCRIPTION
                  FROM student, comment
                  WHERE (
                      student.ID LIKE comment.USER_ID
                      AND
                      comment.COMMENT_TYPE LIKE $inCommentType
                      AND
                      comment.BOOK_ID like $inBookId
                  )

                  UNION

                  SELECT staff.NAME, comment.DESCRIPTION
                  FROM staff, comment
                  WHERE (
                      staff.ID LIKE comment.USER_ID
                      AND
                      comment.COMMENT_TYPE LIKE $inCommentType
                      AND
                      comment.BOOK_ID like $inBookId
                  )

                  UNION

                  SELECT stafflibrary.NAME, comment.DESCRIPTION
                  FROM stafflibrary, comment
                  WHERE (
                      stafflibrary.ID LIKE comment.USER_ID
                      AND
                      comment.COMMENT_TYPE LIKE $inCommentType
                      AND
                      comment.BOOK_ID like $inBookId
                  )";
  $retrieveCommentQuery = mysqli_query($conn, $retrieveCommentSQL);

  if(!empty($retrieveCommentQuery)) {
    if(mysqli_num_rows($retrieveCommentQuery) > 0) {
      while($row = mysqli_fetch_array($retrieveCommentQuery)) {
        $result = array(
          "message" => "success",
          "user_name" => $row["NAME"],
          "user_comment" => $row["DESCRIPTION"]
        );

        array_push($jsonData["comments"], $result);
      }
    } else {
      $result = array("message" => "no_record"); // return no record in database
      array_push($jsonData["comments"], $result);
    }
  } else {
    $result = array("message" => "error"); // return error occurred
    array_push($jsonData["comments"], $result);
  }

  echo json_encode($jsonData);
}
?>
