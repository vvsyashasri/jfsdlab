<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Aggregates</title>
</head>
<body>
    <h1>Aggregate Data</h1>
    <%
        // Mock example of values; these should come from the back-end servlet
        int count = 5;
        double maxBudget = 500000;
        double minBudget = 100000;
        double totalBudget = 1500000;
        double avgBudget = 300000;
    %>
    <p>Total Projects: <%= count %></p>
    <p>Maximum Budget: <%= maxBudget %></p>
    <p>Minimum Budget: <%= minBudget %></p>
    <p>Total Budget: <%= totalBudget %></p>
    <p>Average Budget: <%= avgBudget %></p>
</body>
</html>
