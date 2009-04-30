package ar.com.ucema.ia.chess.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que se encarga de publicar el board en xml. 
 * @author Matías Suárez
 */
public class ChessPublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 9192200798677505014L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unused")
		int i = 0;
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unused")
		int i = 0;
	}

	
	
}
