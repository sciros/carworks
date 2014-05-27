<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><g:layoutTitle default="Carworks"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <r:require modules="bootstrap"/>
        <g:layoutHead/>
        <r:layoutResources/>
    </head>

    <body>
        <div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a></div>

        <nav class="navbar navbar-default" role="navigation">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">User <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <sec:ifLoggedIn>
                            <li><a href="user/show/${sec.loggedInUserInfo(field: 'id')}">My Account</a></li>
                            <li><g:link controller="logout">Logout</g:link></li>
                        </sec:ifLoggedIn>
                        <sec:ifNotLoggedIn>
                            <li><a href="login">Login</a></li>
                            <li><g:link controller="user" action="create">Register</g:link></li>
                        </sec:ifNotLoggedIn>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cars <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <sec:ifLoggedIn>
                            <li><a href="car/showForUser/${sec.loggedInUserInfo(field: 'id')}">My Cars</a></li>
                        </sec:ifLoggedIn>
                        <li><a href="car">All Cars</a></li>
                    </ul>
                </li>
            </ul>
        </nav>

        <g:layoutBody/>
        <r:layoutResources/>
    </body>
</html>
