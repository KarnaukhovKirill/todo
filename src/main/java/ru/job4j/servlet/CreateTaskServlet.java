package ru.job4j.servlet;

import com.google.gson.Gson;
import ru.job4j.model.User;
import ru.job4j.repository.HibernateStore;
import ru.job4j.model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

@WebServlet(value = "/createTask.do")
public class CreateTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var description = req.getParameter("description");
        var categories = req.getParameter("categories");
        String[] catArray = categories.split(",");
        var user = (User) req.getSession().getAttribute("user");
        var store = HibernateStore.getInstance();
        store.create(
                new Task(description, new Timestamp(System.currentTimeMillis()), false, user),
                catArray
        );
    }
}
