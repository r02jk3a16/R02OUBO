

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/Kadai4")
public class Kadai4Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kadai4Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String driverName = "oracle.jdbc.driver.OracleDriver";
		final String url ="jdbc:oracle:thin:@192.168.54.226:1521/orcl";
		final String id = "OUBO";
		final String pass = "TOUSEN";
		
		try {

		Class.forName(driverName);
		Connection connection=DriverManager.getConnection(url,id,pass);
		PreparedStatement st = 
				connection.prepareStatement(
						"SELECT SUBSTR(email,1,(INSTR(email,'@'))-1) as emailuser FROM OUBO JOIN TOUSEN ON OUBO.numa = TOUSEN.numa and OUBO.numb = TOUSEN.numb"
						
					);
		ResultSet rs = st.executeQuery();
		
		List<String[]> list = new ArrayList<String[]>();
		
		while(rs.next() != false) {
			String[] s = new String[1];
			s[0]= rs.getString("emailuser");
			
			list.add(s);
		}
		request.setAttribute("result", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Kadai4.jsp");
		rd.forward(request, response);
		
		
		
		}catch (ClassNotFoundException  e) {e.printStackTrace(response.getWriter());e.printStackTrace();}
		 catch (SQLException e){e.printStackTrace(response.getWriter());e.printStackTrace();}
	}



}
