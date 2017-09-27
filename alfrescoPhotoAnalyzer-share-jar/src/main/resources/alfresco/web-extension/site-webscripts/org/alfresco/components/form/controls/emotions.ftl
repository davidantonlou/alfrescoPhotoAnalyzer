<#assign fieldValue=field.value>

<#if fieldValue??>
    <img src="${url.context}/res/images/${fieldValue?html}.png" style="width:6%; height:6%"/>
</#if>
