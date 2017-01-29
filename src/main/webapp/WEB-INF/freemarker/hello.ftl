<html>
<head>
    <title>Welcome!</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h1>Welcome ${name}!</h1>
    <h2>This is sample project for Spring ${spring.module} ${spring.version}!</h2>

<#if springClasses?has_content>

    <p><strong>Spring classes/interfaces:</strong></p>
    <ul class="list-group">
        <#list springClasses as springClass>
            <li class="list-group-item">${springClass}</li>
        </#list>
    </ul>

</#if>
</div>

</body>
</html>