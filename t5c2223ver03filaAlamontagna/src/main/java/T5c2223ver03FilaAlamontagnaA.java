

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class T5c2223ver03FilaAlamontagnaA
 */
public class T5c2223ver03FilaAlamontagnaA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public T5c2223ver03FilaAlamontagnaA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");		} 
		catch (ClassNotFoundException e) 
		{
			out.println("Eccezione sul server " + request.getContextPath());
			out.println("Eccezione dovuta a ClassForName");
			e.printStackTrace();
		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://10.130.105.130/classicmodels?", "marco2", "marco2password");
		} 
		catch (SQLException e) 
		{
			out.println("Eccezione sul server " + request.getContextPath());
			out.println("Eccezione dovuta a getConnection() ");
			e.printStackTrace();
		}
		
		String sSelect = "select * from customers";
		
		Statement statement = con.createStatement();

		ResultSet rs = null;
		
		try {
			rs = statement.executeQuery(sSelect);
		} catch (SQLException e) {
			out.println("Eccezione sul server " + request.getContextPath());
			out.println("Eccezione dovuta a executeQuery()");
			e.printStackTrace();
		}
		
		while (rs.next()) {
			int CustomerId = rs.getInt("customerNumber");
			String NickCliente = rs.getString("customerName");
			String CustomerLastName = rs.getString("contactLastName");
			String CustomerFirstName = rs.getString("contactFirstName");
			out.println("<form action=T5c2223ver03FilaAlamontagnaB method='post'> "
					+ "<input type='hidden' name='CustomerId' id='CustomerId'  value="+CustomerId+">"
							+ " <input type=submit value="+CustomerId+"></form>"+ ""
									+ " " + NickCliente + " ->" + CustomerLastName + " "+ CustomerFirstName + "<br><hr>");
		}
		out.println("<form action=t5c2223ver03FilaAlamontagnaindex.html method='post'> <input type=submit value='Indietro' </form>");
		out.close();
		con.close();
	}catch(

	Exception e)
	{
		System.out.println(e);
	}
	}

}
