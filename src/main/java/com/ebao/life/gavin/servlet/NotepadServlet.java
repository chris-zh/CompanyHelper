package com.ebao.life.gavin.servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebao.life.gavin.bo.NoteBO;
import com.ebao.life.gavin.util.ToolUtil;
import com.ebao.life.gavin.vo.NoteVO;

@WebServlet("/notepad")
public class NotepadServlet extends RootServlet {
	private static final long serialVersionUID = 1L;
	private NoteBO noteBO = new NoteBO();

	public void actionRequest(HttpServletRequest request, HttpServletResponse response, String action) throws Exception {
		PrintWriter out = null;
		
		if ("saveNote".equals(action)) {
			String noteId = request.getParameter("noteId");
			String noteTitle = request.getParameter("noteTitle");
			String noteContent = request.getParameter("noteContent");
			//logger.debug(noteId + "-----" + noteTitle + "-----" + noteContent);
			String userIP = disposal.getUserIP(request);
			String resXML = disposal.saveNote(noteId, noteTitle, noteContent, userIP);
			response.setContentType("text/plain;charset=UTF-8");
			out = response.getWriter();
			out.write(resXML); 
		} 
		if ("noteContent".equals(action)) {
			String noteId = request.getParameter("noteId");
			NoteVO noteVO =  noteBO.getNoteByID(noteId);
			String noteContent = "";
			if(!(noteVO == null) && !ToolUtil.isEmpty(noteVO.getNoteContent())){
				noteContent = noteVO.getNoteContent();
			}
			response.setContentType("text/plain;charset=UTF-8");
			out = response.getWriter();
			out.write(noteContent); 
		} 
		if ("noteDelete".equals(action)) {
			String noteId = request.getParameter("noteId");
			int rowCnt =  noteBO.deleteNoteByID(noteId);
			String returnMsg = "N";
			if(rowCnt > 0){
				returnMsg = "Y";
			}
			response.setContentType("text/plain;charset=UTF-8");
			out = response.getWriter();
			out.write(returnMsg); 
		} 
		if (out != null) {
			out.flush();
			out.close();  
		} 
	}

	public void pageRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userIP = disposal.getUserIP(request);
		List<NoteVO> noteList = noteBO.getNotesByIP(userIP);

		request.setAttribute("noteList", noteList);
	}
	
}
