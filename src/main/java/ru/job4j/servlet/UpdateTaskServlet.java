package ru.job4j.servlet;

import ru.job4j.repository.HbmTaskStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/updateTask.do")
public class UpdateTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var id = Integer.valueOf(req.getParameter("taskId"));
        var done = req.getParameter("taskDone");
        var store = HbmTaskStore.getInstance();
        store.update(id, !done.equals("true"));
    }
}
