<g:render template="listCars"/>

<div class="pagination">
    <g:paginate total="${carInstanceCount ?: 0}"/>
</div>