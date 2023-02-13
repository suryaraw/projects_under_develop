package com.voilet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");

		System.out.println(mail + " " + pass);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin1", "root",
					"$ury@301299lMY");
			PreparedStatement ps = con.prepareStatement("select * from emp3 where email=? and psw = ?");
			ps.setString(1, mail);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			while (!rs.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("InvalidLogin.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("actualWebsite.jsp");
		rd.forward(request, response);
	}

}
