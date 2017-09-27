<#assign fieldValue=field.value>

<div id="${fieldHtmlId}_emotionContainer"  class="form-field">
    <label for="${fieldHtmlId}">${field.label?html}:
    <#if fieldValue??>
        <span class="viewmode-value">${fieldValue?html}</span>
        <img src="${url.context}/res/components/form/images/${fieldValue?html}.png" />
    <#else>
        <span class="viewmode-value">NO HAY EMOCION</span>
    </#if>
</div>