<?php
include "connect.php";

if(isset($_POST)) {
  $userType = trim($_POST["inUserType"]);
  $commentType = trim($_POST["inCommentType"]);
  $userID = trim($_POST["inUserID"]);
  $bookID = trim($_POST["inBookID"]);
  $description = trim($_POST["inDescription"]);

  // json variables
  $jsonResponse = array();
  $jsonResponse["result_registercomment"] = array();

  $commentRegisterSQL = "INSERT INTO comment (DESCRIPTION, COMMENT_TYPE, USER_TYPE, USER_ID, BOOK_ID)
                        VALUES('$description', $commentType, '$userType', $userID, $bookID)";
  $commentRegisterQuery = mysqli_query($conn, $commentRegisterSQL);

  if($commentRegisterQuery) {
    if(!empty($commentRegisterQuery)) {
      $result = array("message" => "success");
    } else $result = array("message" => "error");
  } else $result = array("message" => "error");

  array_push($jsonResponse["result_registercomment"], $result);
  echo json_encode($jsonResponse);
}
?>
