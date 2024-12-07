<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Project</title>
</head>
<body>
    <h1>Add Project</h1>
    <form action="ProjectController" method="POST">
        <label>Project Name:</label>
        <input type="text" name="projectName" required><br>
        <label>Duration (months):</label>
        <input type="number" name="duration" required><br>
        <label>Budget:</label>
        <input type="number" name="budget" step="0.01" required><br>
        <label>Team Lead:</label>
        <input type="text" name="teamLead" required><br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
