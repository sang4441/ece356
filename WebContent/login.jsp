<html>
    <head>
    </head>
    <body>
        <%
        String error = (String)request.getAttribute("ErrorMessage");
        if(error != null) {
        	out.println(String.format("<span class='note error'>Error: %s</span>", error));
        }
        %>
        <form id="login" action="/ece356/Login" method="post" >
            <input type="text" id="username" name="username" placeholder="username" />
            <input type="password" id="password" name="password" placeholder="password"/>
            <button type="submit">login</button>
        </form>
    </body>
</html>