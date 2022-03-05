<?php
 require "init.php";
 $name = $_GET["name"];
 $user_name = $_GET["user_name"];
 $user_password = $_GET["user_password"];
 $user_email = $_GET["user_email"];
 $user_phone = $_GET["user_phone"];

 $sql = "select * from login_info where user_name = '$user_name'";

 $result = mysqli_query($con,$sql);

 if(mysqli_num_rows($result)>0)
 {
     $status = "exit";
 }

 else
 {
     $sql = "insert into login_info(name,user_name,user_password,user_email,user_phone) values('$name','$user_name','$user_password','$user_email','$user_phone');";

        if(mysqli_query($con,$sql))
        {
            $status = "ok";
        }
        else
        {
            $status = "error";
        }
 }

 echo json_encode(array("response"=>$status));

 mysqli_close($con);

?> 