<?php
include "connect.php";

if(isset($_POST)) {
  $inUsername = trim($_POST["inUsername"]);
  $inPassword = trim($_POST["inPassword"]);
  $inUserType = trim($_POST["inUserType"]);

  // create array for json data
  $jsonData = array();
  $jsonData["login"] = array();

  // get user details
  switch($inUserType) {
    case "student":
      $loginSql = "SELECT * FROM student WHERE ((USERNAME LIKE '$inUsername') AND (PASSWORD LIKE '$inPassword'))";
      break;
    case "staff":
      $loginSql = "SELECT * FROM staff WHERE ((USERNAME LIKE '$inUsername') AND (PASSWORD LIKE '$inPassword'))";
      break;
    case "stafflibrary":
      $loginSql = "SELECT * FROM stafflibrary WHERE ((USERNAME LIKE '$inUsername') AND (PASSWORD LIKE '$inPassword'))";
      break;
  }
  $loginQuery = mysqli_query($conn, $loginSql);

  if(!empty($loginQuery)) {
    if(mysqli_num_rows($loginQuery) > 0) {
      while($row = mysqli_fetch_array($loginQuery)) {
        $result = array(
          "user_id" => $row["ID"],
          "user_name" => $row["NAME"],
          "user_address" => $row["ADDRESS"],
          "user_email" => $row["EMAIL"],
          "user_phoneno" => $row["PHONE_NO"],
          "user_position" => $row["POSITION"],
          "user_department" => $row["DEPARTMENT"],
          "user_username" => $row["USERNAME"],
          "user_password" => $row["PASSWORD"]
        ); // return student id (successful)
      }
    } else $result = array("user_id" => -1); // return no record found
  } else $result = array("user_id" => -2); // return error

  array_push($jsonData["login"], $result);
  echo json_encode($jsonData);
}
?>
