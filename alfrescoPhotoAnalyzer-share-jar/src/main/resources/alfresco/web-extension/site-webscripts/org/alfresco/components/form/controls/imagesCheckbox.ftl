<script>
    function changeValue(item, value, actualValue){
        var result = actualValue;

        if (item.checked){
            result = result + "," + value;
        } else {
            result = result.replace("," + value, "");
        }
        return result;
    }
</script>


<div class="form-field">
    <label for="${fieldHtmlId}-entry">${field.label?html}:<#if field.mandatory><span class="mandatory-indicator">${msg("form.required.fields.marker")}</span></#if></label>
    <input id="${fieldHtmlId}" type="hidden" name="${field.name}" value="${fieldValue?string}" />
    <#list field.control.params.options?split("#alf#") as nameValue>
        <#assign value=nameValue?split("|")[0]>
        <div class="item" style="display:inline">
            <input id="${fieldHtmlId}" type="hidden" name="${value}" value="${value}" />
            <input class="formsCheckBox" id="${fieldHtmlId}-${value}-entry" type="checkbox" tabindex="0" name="-"
                   onchange='javascript:YAHOO.util.Dom.get("${fieldHtmlId}").value=changeValue(this, "${value}", YAHOO.util.Dom.get("${fieldHtmlId}").value);' />
            <label for="${fieldHtmlId}-${value}-entry" class="checkbox" style="display:inline"><img src="${url.context}/res/images/${value}.png" style="width:4%; height:4%"/></label>
            <@formLib.renderFieldHelp field=field />
        </div>
    </#list>
</div>