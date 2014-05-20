<%@ page import="com.careworkstech.carworks.Car" %>



<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'trim', 'error')} ">
    <label for="trim">
        <g:message code="car.trim.label" default="Trim"/>

    </label>
    <g:textField name="trim" value="${carInstance?.trim}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'make', 'error')} required">
    <label for="make">
        <g:message code="car.make.label" default="Make"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="make" required="" value="${carInstance?.make}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'model', 'error')} required">
    <label for="model">
        <g:message code="car.model.label" default="Model"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="model" required="" value="${carInstance?.model}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'year', 'error')} required">
    <label for="year">
        <g:message code="car.year.label" default="Year"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="year" type="number" value="${carInstance.year}" required=""/>

</div>

