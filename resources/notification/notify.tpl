<!DOCTYPE html>
<html>
<head>
  <title>Jenkins Build Details</title>
</head>
<body>

<h1>Jenkins Build Details</h1>

<table border="1">
  <thead>
    <tr>
      <th>Variable Name</th>
      <th>Value</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Build_URL</td>
      <td id="build_url"><a href="${BUILD_URL}">${BUILD_URL}</a></td>
    </tr>
    <tr>
      <td>Triggered Url</td>
      <td id="triggered_url"><a href="${JOB_DISPLAY_URL}">${JOB_DISPLAY_URL}</a></td>
    </tr>
    <tr>
      <td>PR Details</td>
      <td id="pr_details"><a href="${RUN_DISPLAY_URL}">${RUN_DISPLAY_URL}</a></td>
    </tr>
  </tbody>
</table>

</body>
</html>
