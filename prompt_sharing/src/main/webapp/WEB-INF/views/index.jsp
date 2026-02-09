<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.build.prompt_storing.model.Prompt" %>

<%
    String ctx = request.getContextPath();
    List<Prompt> prompts = (List<Prompt>) request.getAttribute("prompts");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Prompt Storing - Prompts</title>

    <style>
        body { font-family: Arial, sans-serif; margin: 24px; }
        .header { display: flex; align-items: center; justify-content: space-between; gap: 16px; }
        .btn {
            display: inline-block; padding: 10px 14px; border: none;
            background: #2563eb; color: white; text-decoration: none; border-radius: 6px;
            font-size: 14px;
        }
        .btn:hover { background: #1d4ed8; }
        table { width: 100%; border-collapse: collapse; margin-top: 16px; }
        th, td { border: 1px solid #ddd; padding: 10px; vertical-align: top; }
        th { background: #f3f4f6; text-align: left; }
        .actions { display: flex; gap: 8px; flex-wrap: wrap; }
        .btn-secondary { background: #6b7280; }
        .btn-secondary:hover { background: #4b5563; }
        .btn-danger { background: #dc2626; }
        .btn-danger:hover { background: #b91c1c; }
        .muted { color: #6b7280; }
        .content-box { white-space: pre-wrap; }
    </style>
</head>

<body>

<div class="header">
    <h2>Welcome to Prompt Storing</h2>
    <a class="btn" href="<%= ctx %>/prompts?action=create">+ Create Prompt</a>
</div>

<!-- <p class="muted">URL: <b><%= ctx %>/prompts</b></p> -->

<% if (prompts == null || prompts.isEmpty()) { %>
    <p>No prompts found. Click <b>Create Prompt</b> to add one.</p>
<% } else { %>

    <table>
        <thead>
        <tr>
            <th style="width:70px;">ID</th>
            <th style="width:180px;">Title</th>
            <th style="width:140px;">Category</th>
            <th>Content</th>
            <th style="width:190px;">Actions</th>
        </tr>
        </thead>

        <tbody>
        <% for (Prompt p : prompts) { %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getTitle() %></td>
                <td><%= p.getCategory() %></td>
                <td class="content-box"><%= p.getContent() %></td>
                <td>
                    <div class="actions">

                        <a class="btn btn-secondary"
                           href="<%= ctx %>/prompts?action=edit&id=<%= p.getId() %>">Edit</a>

                        <form method="post" action="<%= ctx %>/prompts" style="margin:0;"
                              onsubmit="return confirm('Delete this prompt?');">
                            <input type="hidden" name="action" value="delete" />
                            <input type="hidden" name="id" value="<%= p.getId() %>" />
                            <button class="btn btn-danger" type="submit">Delete</button>
                        </form>

                    </div>
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>

<% } %>

</body>
</html>