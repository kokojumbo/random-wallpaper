<%@ page import="randomwallpaper.Entry"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'entry.label', default: 'Entry')}" />

<title>Random Wallpaper :-)</title>
</head>
<body>
	<g:if test="${flash.message}">
		<div class="message" role="status">
			${flash.message}
		</div>
	</g:if>
	<div>

		<g:if test="${select}">
			<img
				src="${createLinkTo(dir: 'images/pictures/', file: select.fileName)}"
				alt="${select.fileName}"
				title="${select.fileName}" />

			<p>
				<g:link controller="installer" action="index" id="${select.id}">get installer</g:link>
			</p>
		</g:if>
	</div>

	<div>
		<g:uploadForm controller="entry" action="mainSave" method="post">
			<fieldset class="form">
				<g:render template="form" />
			</fieldset>
			<g:submitButton name="create"
				value="${message(code: 'default.button.create.label', default: 'Create')}" />
		</g:uploadForm>

	</div>

	<div>

		<g:each in="${entryInstanceList}" status="i" var="entryInstance">

			<img
				src="${createLinkTo(dir: 'images/pictures/', file: entryInstance.fileName)}"
				alt="${entryInstance.fileName}" title="${entryInstance.fileName}" />

<p><g:link controller="main" params="[select: entryInstance.id]">select picture</g:link></p>
			<p>
				<g:formatDate date="${entryInstance.dateCreated}" />
			</p>

		</g:each>

	</div>
<div class="pagination">
				<g:paginate total="${entryInstanceCount ?: 0}" />
			</div>
</body>
</html>
