package ar.com.ucema.ia.chess.web.servlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import ar.com.ucema.ia.chess.model.ChessPlay;
import ar.com.ucema.ia.chess.web.services.ChessXMLService;
import ar.com.ucema.ia.chess.web.services.impl.ChessXMLServiceImpl;

/**
 * Servlet implementation class ChessPublisherServlet
 */
public class ChessPublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChessXMLService service = new ChessXMLServiceImpl();
		String from = (String) request.getParameter("from");
		String to = (String) request.getParameter("to");

		ChessPlay play = new ChessPlay();
		play.setColumnFrom(getColumnFrom(from));
		play.setColumnTo(getColumnFrom(to));
		play.setRowFrom(new Integer(getRowFrom(from)));
		play.setRowTo(new Integer(getRowFrom(to)));
		
		response.setContentType("text/xml");

		Document doc = service.playWhite(play);
		Writer out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(out, format);
		
		try {
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String getColumnFrom(String from) {
		return from.charAt(0) + "";
	}
	
	private Integer getRowFrom(String move) {
		return Integer.parseInt(move.charAt(1) + "");
	}

}
