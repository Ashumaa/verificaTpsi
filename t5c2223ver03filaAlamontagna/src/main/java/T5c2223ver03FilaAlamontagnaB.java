

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
 * Servlet implementation class T5c2223ver03FilaAlamntagnaB
 */
public class T5c2223ver03FilaAlamontagnaB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public T5c2223ver03FilaAlamontagnaB() {
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
		String sCustomerId = request.getParameter("CustomerId"); 
		int CustomerId = Integer.parseInt(sCustomerId);
		
		String sSelect = "select * from customers where customerNumber="+CustomerId;
		
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
			String cognome = rs.getString("contactLastName");
			String nome = rs.getString("contactFirstName");
			String telefono = rs.getString("phone");
			String indirizzo1 = rs.getString("addressLine1");
			String indirizzo2 = rs.getString("addressLine2");
			String citta = rs.getString("city");
			String stato = rs.getString("country");

			out.println(cognome + " " + nome + " "+ telefono + " "+telefono+" "+indirizzo1+" "+indirizzo2+" "+citta+" "+stato+"<br><hr>");
		}
		//da commentare una form che insieme non funzionano
		//out.println("<form action=T5c2223ver03FilaAlamontagnaA method='post' id='form1'> <input type=submit value='Indietro' </form><br><br>");
		out.println("<form action=T5c2223ver03FilaAlamontagnaC method='post' id='form2'> <input type='hidden' name='CustomerId' id='CustomerId'  value="+CustomerId+"> <input type=submit value='Ordini' </form>");
		out.close();
		con.close();
	}catch(

	Exception e)
	{
		System.out.println(e);
	}
	}
}
