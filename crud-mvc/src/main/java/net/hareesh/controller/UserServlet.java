package net.hareesh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.hareesh.model.User;
import net.hareesh.service.UserService;

@WebServlet(urlPatterns = { "/users" }, name = "UserServlet", description = "Return's user data in JSON format")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService service = new UserService();

	public UserServlet() {
		super();
	}

//	Tested
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getQueryString() == null) {
			try {
				List<User> userList = new ArrayList<User>();
				userList = service.getUser();
				Gson gson = new Gson();
				String userJSON = gson.toJson(userList);
				PrintWriter pw = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				pw.write(userJSON);
				response.setStatus(200);
				pw.close();
			} catch (Exception e) {
				response.setStatus(500);
			}
		} else if (request.getQueryString() != null) {
			if (request.getParameter("id") != null) {
				try {

					List<User> userList = new ArrayList<User>();
					userList = service.getUser(Integer.parseInt(request.getParameter("id")));
					Gson gson = new Gson();
					String userJSON = gson.toJson(userList);
					PrintWriter pw = response.getWriter();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					pw.write(userJSON);
					response.setStatus(200);
					pw.close();
				} catch (Exception e) {
					response.setStatus(500);
				}
			} else {
				response.setStatus(404);
				response.sendError(404, "Enter a valid parameter");
			}
		}
	}

	// Tested
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println(request.getParameterMap().get("name"));
			String name = request.getParameterMap().get("name")[0];
			String email = request.getParameterMap().get("email")[0];
			String mobile = request.getParameterMap().get("mobile")[0];
			String dob = request.getParameterMap().get("DOB")[0];
			String city = request.getParameterMap().get("city")[0];
			String gender = request.getParameterMap().get("gender")[0];
			service.addUser(new User((int) Math.floor(Math.random() * 10000), name, email, mobile, dob, city, gender));
			response.setStatus(200);
			response.sendRedirect("userInfo.html");
		} catch (Exception e) {
			response.setStatus(500);
			response.sendError(500, e.getMessage());
		}

	}

//	Tested
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameterMap().get("id")[0]);
			String name = request.getParameterMap().get("name")[0];
			String email = request.getParameterMap().get("email")[0];
			String mobile = request.getParameterMap().get("mobile")[0];
			String dob = request.getParameterMap().get("dob")[0];
			String city = request.getParameterMap().get("city")[0];
			String gender = request.getParameterMap().get("gender")[0];
			service.updateUser(new User(id, name, email, mobile, dob, city, gender));
			response.setStatus(200);
		} catch (Exception e) {
			response.setStatus(500);
			response.sendError(500, e.getMessage());
		}
	}

//	Tested
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameterMap().get("id")[0]);
		service.removeUser(id);
		response.setStatus(200);
		System.out.println("Delete Invoked");
	}
}
