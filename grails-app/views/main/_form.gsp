<%@ page import="randomwallpaper.Entry" %>



<div class="fieldcontain ${hasErrors(bean: entry, field: 'originalUrl', 'error')} ">
	<label for="originalUrl">
		<g:message code="entry.originalUrl.label" default="Picture Url" />
	</label>
	<g:textField name="originalUrl" value="${entry?.originalUrl}"/>

</div>
Or
<div class="fieldcontain">

<label for="picture">
		Picture from disc
</label>
<input type='file' name="picture"/>
</div>