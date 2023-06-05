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
public class T5c2223ver03FilaAlamontagnaC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public T5c2223ver03FilaAlamontagnaC() {
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
		
		String sSelect = "select productName,buyPrice,quantityOrdered,buyPrice from orders,orderdetails,products"
				+ " where orders.customerNumber="+CustomerId+" AND orderdetails.orderNumber=orders.orderNumber AND orderdetails.productCode=products.productCode";
		
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
			String prodotto = rs.getString("productName");
			String prezzo = rs.getString("buyPrice");
			String quantita = rs.getString("quantityOrdered");
			//String costoOrdine = rs.getString("sum(buyPrice)");
			//ho dovuto commentare perchè non riusciva a trovare la tabella giusta 

			out.println(prodotto + " " + prezzo + " "+ quantita + /*+costoOrdine+"*/"<br><hr>");
		}
		out.println("<form action=t5c2223ver03FilaAlamontagnaB method='post'> <input type=submit value='Indietro' </form>");
		out.close();
		con.close();
	}catch(

	Exception e)
	{
		System.out.println(e);
	}
	}
}