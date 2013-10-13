<%@ page import="randomwallpaper.Entry" %>



<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'originalUrl', 'error')} ">
	<label for="originalUrl">
		<g:message code="entry.originalUrl.label" default="Original Url" />
		
	</label>
	<g:textField name="originalUrl" value="${entryInstance?.originalUrl}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'filePath', 'error')} ">
	<label for="filePath">
		<g:message code="entry.filePath.label" default="File Path" />
		
	</label>
	<g:textField name="filePath" value="${entryInstance?.filePath}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'mime', 'error')} ">
	<label for="mime">
		<g:message code="entry.mime.label" default="Mime" />
		
	</label>
	<g:textField name="mime" value="${entryInstance?.mime}"/>
</div>

