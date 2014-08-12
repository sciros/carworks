<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
</head>
<body>
    <g:form class="form-group" action="search">
        <fieldset class="form">
            <label for="make"><g:message code="car.make.label" default="Make"/></label>
            <g:field type="text" name="make"/>
            <br/>
            <label for="model"><g:message code="car.model.label" default="Model"/></label>
            <g:field type="text" name="model"/>
            <br/>
            <label for="trim"><g:message code="car.trim.label" default="Trim"/></label>
            <g:field type="text" name="trim"/>
            <br/>
            <label for="year"><g:message code="car.year.label" default="Year"/></label>
            <g:field type="number" name="year"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="search" class="btn btn-primary"
                            value="${message(code: 'button.search.label', default: 'Search')}"/>
        </fieldset>
    </g:form>
</body>
</html>