<!DOCTYPE html>
<html>
<head>
  <title>Jenkins Build Details</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }

    h1 {
      background-color: #0074d9;
      color: #ffffff;
      padding: 20px;
      margin: 0;
    }

    table {
      width: 80%;
      margin: 20px auto;
      border-collapse: collapse;
    }

    th, td {
      padding: 10px;
      text-align: left;
      border: 1px solid #ddd;
    }

    th {
      background-color: #0074d9;
      color: #ffffff;
    }

    a {
      text-decoration: none;
      color: #0074d9;
    }

    a:hover {
      text-decoration: underline;
    }

    .status {
      font-weight: bold;
      color: ${currentBuild.result == 'SUCCESS' ? 'green' : 'red'};
    }
  </style>
</head>
<body>

<h1>Jenkins Build Details</h1>

<table>
  <thead>
    <tr>
      <th>Name</th>
      <th>Value</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Build Number</td>
      <td id="build_number">${BUILD_NUMBER}</td>
    </tr>
    <tr>
      <td>Job Name</td>
      <td id="job_name">${JOB_NAME}</td>
    </tr>
    <tr>
      <td>Build Status</td>
      <td id="build_status" class="status">${currentBuild.result}</td>
    </tr>
    <tr>
      <td>Build URL</td>
      <td id="build_url"><a href="${BUILD_URL}" target="_blank">${BUILD_URL}</a></td>
    </tr>
    <tr>
      <td>Branch URL</td>
      <td id="branch_url"><a href="${JOB_DISPLAY_URL}" target="_blank">${JOB_DISPLAY_URL}</a></td>
    </tr>
    <tr>
      <td>Build Details</td>
      <td id="build_details"><a href="${RUN_DISPLAY_URL}" target="_blank">${RUN_DISPLAY_URL}</a></td>
    </tr>
  </tbody>
</table>

</body>
</html>
