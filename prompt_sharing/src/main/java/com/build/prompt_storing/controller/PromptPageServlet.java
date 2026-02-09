package com.build.prompt_storing.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.build.prompt_storing.dao.PromptDao;
import com.build.prompt_storing.dao.PromptDaoJdbc;
import com.build.prompt_storing.model.Prompt;

@WebServlet("/prompts")
public class PromptPageServlet extends HttpServlet {

    private final PromptDao dao = new PromptDaoJdbc();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        // Create page
        if ("create".equalsIgnoreCase(action)) {
            req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
            return;
        }

        //Edit page
        if ("edit".equalsIgnoreCase(action)) {

            String idParam = req.getParameter("id");

            // If id missing -> go back to list
            if (idParam == null || idParam.isBlank()) {
                resp.sendRedirect(req.getContextPath() + "/prompts");
                return;
            }

            try {
                int id = Integer.parseInt(idParam);
                Prompt p = dao.findById(id).orElse(null);

                // If prompt doesn't exist - go back to list
                if (p == null) {
                    resp.sendRedirect(req.getContextPath() + "/prompts");
                    return;
                }

                req.setAttribute("prompt", p);
                req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
                return;

            } catch (NumberFormatException ex) {
                // If id is not number  go back to list
                resp.sendRedirect(req.getContextPath() + "/prompts");
                return;
            }
        }

        // Default: List page
        req.setAttribute("prompts", dao.findAll());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String action = req.getParameter("action");

        // Create new prompt
        if ("create".equalsIgnoreCase(action)) {
            Prompt p = new Prompt();
            p.setTitle(req.getParameter("title"));
            p.setContent(req.getParameter("content"));
            p.setCategory(req.getParameter("category"));

            dao.create(p);
            resp.sendRedirect(req.getContextPath() + "/prompts");
            return;
        }

        // Update prompt
        if ("update".equalsIgnoreCase(action)) {

            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isBlank()) {
                resp.sendRedirect(req.getContextPath() + "/prompts");
                return;
            }

            try {
                int id = Integer.parseInt(idParam);

                Prompt p = new Prompt();
                p.setTitle(req.getParameter("title"));
                p.setContent(req.getParameter("content"));
                p.setCategory(req.getParameter("category"));

                dao.update(id, p);
                resp.sendRedirect(req.getContextPath() + "/prompts");
                return;

            } catch (NumberFormatException ex) {
                resp.sendRedirect(req.getContextPath() + "/prompts");
                return;
            }
        }

        // Delete prompt
        if ("delete".equalsIgnoreCase(action)) {

            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isBlank()) {
                resp.sendRedirect(req.getContextPath() + "/prompts");
                return;
            }

            try {
                int id = Integer.parseInt(idParam);
                dao.delete(id);
            } catch (NumberFormatException ignored) {
                // ignore invalid id
            }

            resp.sendRedirect(req.getContextPath() + "/prompts");
        }
    }
}