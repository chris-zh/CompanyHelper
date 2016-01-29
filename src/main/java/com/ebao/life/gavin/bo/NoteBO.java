package com.ebao.life.gavin.bo;

import java.util.List;

import com.ebao.life.gavin.dao.NoteDAO;
import com.ebao.life.gavin.vo.NoteVO;

public class NoteBO {
	private NoteDAO dao = new NoteDAO();
	
	public int saveNote(NoteVO vo) throws Exception {
		int rowCnt = dao.updateNote(vo);
		if(rowCnt == 0){
			rowCnt = dao.insertNote(vo);
		}
		return rowCnt;
	}
	
	public int deleteNoteByID(String noteId) throws Exception {
		return dao.deleteNote(noteId);
	}
	
	public NoteVO getNoteByID(String noteId) throws Exception {
		return dao.getNoteByID(noteId);
	}
	
	public List<NoteVO> getNotesByIP(String saverIp) throws Exception {
		return dao.getNotesByIP(saverIp);
	}
	

	public static void main(String[] args) throws Exception {
		NoteBO bo = new NoteBO();
		NoteVO vo = new NoteVO();
		vo.setNoteId("1232323311");
		vo.setSaverIp("325421");
		vo.setNoteTitle("title");
		vo.setNoteContent("adsfafsdasdffffffffffffff÷–Œƒ≤‚ ‘fffffffweabbbbbbbbbbbwerhernersnearnreearejjrtsjdfgjkmrstjnznerass");
		bo.saveNote(vo);
	}
}

