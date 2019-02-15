<#macro base title>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${title}</title>
    <meta charset="UTF-8">
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/materialize/css/materialize.min.css"
          media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/materialize/css/main.css">
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


</head>
<body class=" grey lighten-1">
<@bodyPage/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="/materialize/js/materialize.min.js"></script>
</body>
</html>
</#macro>
