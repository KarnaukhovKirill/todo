package ru.job4j.servlet;

import com.google.gson.Gson;
import ru.job4j.model.Category;
import ru.job4j.repository.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(value = "/loadCategories.do")
public class LoadCategoriesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var gson = (Gson) req.getServletContext().getAttribute("GSON");
        HibernateStore store = HibernateStore.getInstance();
        List<Category> categories = store.findAllCategories();
        resp.setContentType("aplication/json; charset=utf-8");
        var output = resp.getOutputStream();
        String json = gson.toJson(categories);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
    }
}
