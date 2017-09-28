<#assign fieldValue=field.value>

<#if fieldValue?has_content>
    <div class="viewmode-field">
        <span for="${fieldHtmlId}" class="viewmode-label">${field.label?html}:</span>
        <#list fieldValue?split(r'\s*,\s*', 'r') as item>
            <img alt="${item}" src="${url.context}/res/images/${item}.png" style="width:6%; height:6%"/>
        </#list>
    </div>
</#if>
