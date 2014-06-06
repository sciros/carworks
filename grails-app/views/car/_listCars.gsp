<%@ page import="com.careworkstech.carworks.Car" %>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'car.label', default: 'Car')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<g:link class="btn btn-default" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link>

<h3><g:message code="default.list.label" args="[entityName]"/></h3>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<table class="table table-bordered table-responsive">
    <thead>
    <tr>
        <g:sortableColumn property="make" title="${message(code: 'car.make.label', default: 'Make')}"/>
        <g:sortableColumn property="model" title="${message(code: 'car.model.label', default: 'Model')}"/>
        <g:sortableColumn property="trim" title="${message(code: 'car.trim.label', default: 'Trim')}"/>
        <g:sortableColumn property="year" title="${message(code: 'car.year.label', default: 'Year')}"/>
    </tr>
    </thead>
    <tbody>
    <g:each in="${carInstanceList}" status="i" var="carInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td><g:link action="show"
                        id="${carInstance.id}">${fieldValue(bean: carInstance, field: "make")}</g:link></td>
            <td>${fieldValue(bean: carInstance, field: "model")}</td>
            <td>${fieldValue(bean: carInstance, field: "trim")}</td>
            <td>${fieldValue(bean: carInstance, field: "year")}</td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>