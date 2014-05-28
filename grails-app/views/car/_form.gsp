<%@ page import="com.careworkstech.carworks.Car" %>


<table class="table table-bordered">
    <tr>
        <td>
            <label for="make">
                <g:message code="car.make.label" default="Make"/>
                <span class="required-indicator">*</span>
            </label>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: carInstance, field: 'make', 'error')} required">
                <g:textField name="make" required="" value="${carInstance?.make}"/>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <label for="model">
                <g:message code="car.model.label" default="Model"/>
                <span class="required-indicator">*</span>
            </label>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: carInstance, field: 'model', 'error')} required">
                <g:textField name="model" required="" value="${carInstance?.model}"/>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <label for="trim">
                <g:message code="car.trim.label" default="Trim"/>
            </label>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: carInstance, field: 'trim', 'error')} ">
                <g:textField name="trim" value="${carInstance?.trim}"/>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <label for="year">
                <g:message code="car.year.label" default="Year"/>
                <span class="required-indicator">*</span>
            </label>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: carInstance, field: 'year', 'error')} required">
                <g:field name="year" type="number" value="${carInstance.year}" required=""/>
            </div>
        </td>
    </tr>
</table>




