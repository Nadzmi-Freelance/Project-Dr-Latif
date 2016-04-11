<?php
include "connect.php";

if(isset($_POST)) {
  // get all details for register process
  $userType = trim($_POST["inUserType"]);
  $inName = trim($_POST["inName"]);
  $inEmail = trim($_POST["inEmail"]);
  $inUsername = trim($_POST["inUsername"]);
  $inPassword = trim($_POST["inPassword"]);

  if($userType!=null && $inName!=null && $inEmail!=null && $inUsername!=null && $inPassword!=null) {
    // create array for json data
    $jsonResponse = array();
    $jsonResponse["register"] = array();

    switch($userType) {
      case "student":
        $checkSql = "SELECT * FROM student WHERE ((USERNAME LIKE '$inUsername') AND (PASSWORD LIKE '$inPassword'))";
        break;
      case "staff":
        $checkSql = "SELECT * FROM staff WHERE ((USERNAME LIKE '$inUsername') AND (PASSWORD LIKE '$inPassword'))";
        break;
      case "stafflibrary":
        $checkSql = "SELECT * FROM stafflibrary WHERE ((USERNAME LIKE '$inUsername') AND (PASSWORD LIKE '$inPassword'))";
        break;
      default:
        $checkSql = "SELECT * FROM student WHERE ((USERNAME LIKE '$inUsername') AND (PASSWORD LIKE '$inPassword'))";
        break;
    }
    $checkQuery = mysqli_query($conn, $checkSql) or die(mysql_error());

    if(!empty($checkQuery)) {
      if(mysqli_num_rows($checkQuery) <= 0) { // check record in database
        // record does not exist, can insert the new record
        switch($userType) {
          case "student":
            $insertSql = "INSERT INTO student (NAME, EMAIL, USERNAME, PASSWORD) VALUES ('$inName', '$inEmail', '$inUsername', '$inPassword')";
            break;
          case "staff":
            $insertSql = "INSERT INTO staff (NAME, EMAIL, USERNAME, PASSWORD) VALUES ('$inName', '$inEmail', '$inUsername', '$inPassword')";
            break;
          case "stafflibrary":
            $insertSql = "INSERT INTO stafflibrary (NAME, EMAIL, USERNAME, PASSWORD) VALUES ('$inName', '$inEmail', '$inUsername', '$inPassword')";
            break;
          default:
            $insertSql = "SELECT * FROM student WHERE ((USERNAME LIKE '$inUsername') AND (PASSWORD LIKE '$inPassword'))";
            break;
        }
        $insertQuery = mysqli_query($conn, $insertSql);

        if($insertQuery)
          $result = array("register_result" => 0); // record insert successful
        else
          $result = array("register_result" => 2); // record insert has error
      } else $result = array("register_result" => 1); // record exist, cannot insert the new record
    } else $result = array("register_result" => 4); // error occured during record checking

    array_push($jsonResponse["register"], $result); // push an array of result into the jsonResponse array
    echo json_encode($jsonResponse); // encode jsonResponse array into JSON data format
  } else echo "{\"register\":[{\"register_result\":3}]}";
}
?>
