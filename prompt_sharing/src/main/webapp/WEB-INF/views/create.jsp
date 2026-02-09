<%@ page contentType="text/html; charset=UTF-8" %>

<%
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Create Prompt</title>

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
            background: #2563eb; color: white; cursor: pointer; text-decoration: none;
            font-size: 14px;
        }
        .btn:hover { background: #1d4ed8; }
        .btn-secondary { background: #6b7280; }
        .btn-secondary:hover { background: #4b5563; }
    </style>
</head>

<body>

<h2>Create Prompt</h2>

<div class="card">
    <form method="post" action="<%= ctx %>/prompts">
        <input type="hidden" name="action" value="create" />

        <label>Title</label>
        <input type="text" name="title" placeholder="Enter title" required />

        <label>Category</label>
        <input type="text" name="category" placeholder="Eg: Java, SQL, AI..." required />

        <label>Content</label>
        <textarea name="content" placeholder="Enter prompt content..." required></textarea>

        <div class="actions">
            <button class="btn" type="submit">Create</button>
            <a class="btn btn-secondary" href="<%= ctx %>/prompts">Cancel</a>
        </div>
    </form>
</div>

</body>
</html>