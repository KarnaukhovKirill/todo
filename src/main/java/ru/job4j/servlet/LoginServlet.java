package ru.job4j.servlet;

import com.google.gson.Gson;
import ru.job4j.model.User;
import ru.job4j.repository.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/login.do")
public class LoginServlet  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var gson = (Gson) req.getServletContext().getAttribute("GSON");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        var user = HibernateStore.getInstance().findUserByEmail(email);
        if (user != null && password.equals(user.getPassword())) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
        } else {
            resp.setContentType("application/json; charset=utf-8");
            OutputStream outputStream = resp.getOutputStream();
            String json = gson.toJson("не верный email или пароль");
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();

        }
    }
}
