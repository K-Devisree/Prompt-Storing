<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.build.prompt_storing.model.Prompt" %>

<%
    String ctx = request.getContextPath();
    Prompt prompt = (Prompt) request.getAttribute("prompt");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Edit Prompt</title>

    <style>
        body { font-family: Arial, sans-serif; margin: 24px; }
        .card { max-width: 720px; padding: 18px; border: 1px solid #e5e7eb; border-radius: 10px; }
        label { display:block; font-weight: 600; margin-top: 12px; }
        input, textarea {
            width: 100%; padding: 10px; margin-top: 6px;
            border: 1px solid #d1d5db; border-radius: 8px; box-sizing: border-box;
        }
        textarea { min-height: 140px; resize: vertical; }
        .actions { display:flex; gap: 10px; margin-top: 16px; }
        .btn {
            padding: 10px 14px; border: none; border-radius: 8px;
            background: #16a34a; color: white; cursor: pointer; text-decoration: none;
            font-size: 14px;
        }
        .btn:hover { background: #15803d; }
        .btn-secondary { background: #6b7280; }
        .btn-secondary:hover { background: #4b5563; }
        .error { color: #dc2626; font-weight: 700; }
    </style>
</head>

<body>

<h2>Edit Prompt</h2>

<% if (prompt == null) { %>
    <p class="error">Prompt not found. Please go back and try again.</p>
    <a class="btn btn-secondary" href="<%= ctx %>/prompts">Back</a>
<% } else { %>

<div class="card">
    <form method="post" action="<%= ctx %>/prompts">
        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="id" value="<%= prompt.getId() %>" />

        <label>Title</label>
        <input type="text" name="title" value="<%= prompt.getTitle() %>" required />

        <label>Category</label>
        <input type="text" name="category" value="<%= prompt.getCategory() %>" required />

        <label>Content</label>
        <textarea name="content" required><%= prompt.getContent() %></textarea>

        <div class="actions">
            <button class="btn" type="submit">Update</button>
            <a class="btn btn-secondary" href="<%= ctx %>/prompts">Cancel</a>
        </div>
    </form>
</div>

<% } %>

</body>
</html>