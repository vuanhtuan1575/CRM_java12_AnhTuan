package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.model.User;
import cybersoft.java12.crmapp.service.UserService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name = "userServlet", urlPatterns = { UrlConst.USER_DASHBOARD, UrlConst.USER_PROFILE, UrlConst.USER_ADD,
		UrlConst.USER_UPDATE, UrlConst.USER_DELETE })
public class UserServlet extends HttpServlet {
	private UserService service;

	@Override
	public void init() throws ServletException {
		super.init();
		service = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.USER_DASHBOARD:
			getUserDashboard(req, resp);
			break;
		case UrlConst.USER_PROFILE:
			getUserProfile(req, resp);
			break;
		case UrlConst.USER_ADD:
			getUserAdd(req, resp);
			break;
		case UrlConst.USER_UPDATE:
			getUserUpdate(req, resp);
			break;
		case UrlConst.USER_DELETE:
			getUserDelete(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.USER_DASHBOARD:

			break;
		case UrlConst.USER_PROFILE:

			break;
		case UrlConst.USER_ADD:
			postUserAdd(req, resp);
			break;
		case UrlConst.USER_UPDATE:

			break;
		case UrlConst.USER_DELETE:

			break;
		}
	}

	private void getUserDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		service.deleteById(id);

		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
	}

	private void getUserUpdate(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		System.out.println(id + " so id la");
		if(id == null)
			return;
		int numId= Integer.parseInt(id);
		try {
			User user = service.findById(numId);
			if(user!=null) {
				req.setAttribute("user", user);
			}
			req.getRequestDispatcher(JspConst.USER_UPDATE).forward(req, resp);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher(JspConst.USER_ADD).forward(req, resp);
	}

	private void getUserProfile(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

	private void getUserDashboard(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<User> users = service.findAll();

		if (users != null && !users.isEmpty())
			req.setAttribute("users", users);

		req.getRequestDispatcher(JspConst.USER_DASHBOARD).forward(req, resp);
	}

	private void postUserAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		try {
			UserCreateDto dto = extractDtoFromReq(req);
			boolean isSuccess = service.add(dto);

			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher(JspConst.USER_ADD).forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private UserCreateDto extractDtoFromReq(HttpServletRequest req) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int roleId = Integer.parseInt(req.getParameter("role"));
		return new UserCreateDto(email, password, name, address, phone, roleId);
	}
}
