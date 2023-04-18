package gr.aueb.cf.schoolapp.controller;


import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolapp.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schoolapp/users/update")
public class UpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IUserDAO userDAO = new UserDAOImpl();
	private final IUserService userService = new UserServiceImpl(userDAO);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schoolapp/static/templates/userupdate.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDTO userDTO = new UserDTO();
		userDTO.setId(id);
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		request.setAttribute("insertedUser", userDTO);
		
		try {
			// impl later
//			String error = Validator.validate(userDTO);
//			if (!error.equals("")) {
//				request.setAttribute("error", error);
//				request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
//						.forward(request, response);
//			}

			if (Validator.userExists(username)) {
				request.setAttribute("error", "User already exists");
				request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp").forward(request, response);
				response.sendRedirect("/schoolapp/static/templates/usersmenu.jsp");
				return;
			}
			userService.updateUser(userDTO);
			request.setAttribute("user", userDTO);
			request.getRequestDispatcher("/schoolapp/static/templates/userupdated.jsp")
					.forward(request, response);
		} catch (UserNotFoundException | UserDAOException e) {
			String message = e.getMessage();
			request.setAttribute("isError", true);
			request.setAttribute("user", userDTO);
			request.getRequestDispatcher("/schoolapp/static/templates/userupdated.jsp")
					.forward(request, response);

		}
	}
}
