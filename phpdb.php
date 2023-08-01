<?php
$server_name = "localhost";
$username = "root";
$password = "";
$database_name = "product";

// Create connection
$conn = new mysqli($server_name, $username, $password, $database_name);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$fromdate = isset($_POST['fromDate']) ? $conn->real_escape_string($_POST['fromDate']) : "";
$todate = isset($_POST['toDate']) ? $conn->real_escape_string($_POST['toDate']) : "";
$fromtime = isset($_POST['fromTime']) ? $conn->real_escape_string($_POST['fromTime']) : "";
$totime = isset($_POST['toTime']) ? $conn->real_escape_string($_POST['toTime']) : "";
$spinn = isset($_POST['spin']) ? $conn->real_escape_string($_POST['spin']) : "";

if (!empty($fromdate) && !empty($todate) || empty($fromtime) && empty($totime) && empty($spinn)) {
    $result = $conn->query("SELECT * FROM `aps` WHERE Date_of_Pouring BETWEEN '$fromdate' AND '$todate'");

    if ($result) {
        $jsonresult = [];
        while ($row = $result->fetch_assoc()) {
            $jsonresult[] = $row;
        }
        header('Content-Type: application/json');
        echo json_encode(['data' => $jsonresult]);
    } else {
        echo "Error: " . $conn->error;
    }
} elseif (!empty($fromdate) && !empty($todate) && !empty($fromtime) && !empty($totime) || empty($spinn)) {
    $result = $conn->query("SELECT * FROM `aps` WHERE Date_of_Pouring BETWEEN '$fromdate' AND '$todate' AND (Time BETWEEN '$fromtime' AND '$totime')");

    if ($result) {
        $jsonresult = [];
        while ($row = $result->fetch_assoc()) {
            $jsonresult[] = $row;
        }
        header('Content-Type: application/json');
        echo json_encode(['data' => $jsonresult]);
    } else {
        echo "Error: " . $conn->error;
    }
} elseif (!empty($fromdate) && !empty($todate) && !empty($fromtime) && !empty($totime) && !empty($spinn)) {
    if ($spinn == "ALL") {
        $result = $conn->query("SELECT * FROM `aps` WHERE Date_of_Pouring BETWEEN '$fromdate' AND '$todate' AND (Time BETWEEN '$fromtime' AND '$totime')");
    } else {
        $result = $conn->query("SELECT * FROM `aps` WHERE Date_of_Pouring BETWEEN '$fromdate' AND '$todate' AND (Time BETWEEN '$fromtime' AND '$totime' AND (Model_name ='$spinn'))");
    }

    if ($result) {
        $jsonresult = [];
        while ($row = $result->fetch_assoc()) {
            $jsonresult[] = $row;
        }
        header('Content-Type: application/json');
        echo json_encode(['data' => $jsonresult]);
    } else {
        echo "Error: " . $conn->error;
    }
} elseif (!empty($fromdate) && !empty($todate) && !empty($spinn) || empty($fromtime) || empty($totime)) {
    if ($spinn == 'ALL') {
        $result = $conn->query("SELECT * FROM `aps` WHERE Date_of_Pouring BETWEEN '$fromdate' AND '$todate'");
} else {
$result = $conn->query("SELECT * FROM `aps` WHERE Date_of_Pouring BETWEEN '$fromdate' AND '$todate' AND (Model_name ='$spinn')");
}
if ($result) {
    $jsonresult = [];
    while ($row = $result->fetch_assoc()) {
        $jsonresult[] = $row;
    }
    header('Content-Type: application/json');
    echo json_encode(['data' => $jsonresult]);
} else {
    echo "Error: " . $conn->error;
}
}
?>



