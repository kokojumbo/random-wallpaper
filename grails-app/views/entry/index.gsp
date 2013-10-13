
<%@ page import="randomwallpaper.Entry" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'entry.label', default: 'Entry')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-entry" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-entry" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="originalUrl" title="${message(code: 'entry.originalUrl.label', default: 'Original Url')}" />
					
						<g:sortableColumn property="filePath" title="${message(code: 'entry.filePath.label', default: 'File Path')}" />
					
						<g:sortableColumn property="mime" title="${message(code: 'entry.mime.label', default: 'Mime')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'entry.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${entryInstanceList}" status="i" var="entryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "originalUrl")}</g:link></td>
					
						<td>${fieldValue(bean: entryInstance, field: "filePath")}</td>
					
						<td>${fieldValue(bean: entryInstance, field: "mime")}</td>
					
						<td><g:formatDate date="${entryInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${entryInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
