package com.ebao.life.gavin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ebao.life.bean.pub.common.Tools;
import com.ebao.life.gavin.util.DBManager;
import com.ebao.life.gavin.util.EnvEnum;
import com.ebao.life.gavin.util.QueryCallback;
import com.ebao.life.gavin.vo.NoteVO;

public class NoteDAO {

	public NoteVO getNoteByID(String noteId) throws Exception {

		StringBuffer sb = new StringBuffer(300);
		sb.append(" SELECT T.NOTE_ID,               ");
		sb.append("        T.SAVER_IP,              ");
		sb.append("        T.NOTE_TITLE,            ");
		sb.append("        T.NOTE_CONTENT,          ");
		sb.append("        to_char(T.INSERT_TIME,'YYYY-MM-DD') INSERT_TIME,        ");
		sb.append("        to_char(T.UPDATE_TIME,'YYYY-MM-DD') UPDATE_TIME,        ");
		sb.append("        T.IS_DELETED             ");
		sb.append("   FROM T_NOTEPAD T              ");
		sb.append("  WHERE T.NOTE_ID = ?            ");
		// sb.append("    AND T.SAVER_IP = ?	        ");
		sb.append("    AND T.IS_DELETED = 'N'       ");
		sb.append("  ORDER BY T.UPDATE_TIME DESC    ");

		List<Object> paraList = new ArrayList<Object>();
		paraList.add(noteId);
		// paraList.add(saverIp);
		NoteVO vo = (NoteVO)DBManager.executeQuery(EnvEnum.tpdev, sb.toString(), paraList, new QueryCallback() {
			@Override
			public Object disposeResultSet(ResultSet rs) throws SQLException {
				NoteVO vo = new NoteVO();
				if (rs.next()) {
					vo.setNoteId(rs.getString("NOTE_ID"));
					vo.setSaverIp(rs.getString("SAVER_IP"));
					vo.setNoteTitle(rs.getString("NOTE_TITLE"));
					// 读取clob
					oracle.sql.CLOB clob = (oracle.sql.CLOB) rs.getClob("NOTE_CONTENT");
					String noteContent = clob.getSubString(1, (int) clob.length());
					vo.setNoteContent(noteContent);
					vo.setInsertTime(rs.getString("INSERT_TIME"));
					vo.setUpdateTime(rs.getString("UPDATE_TIME"));
					vo.setIsDeleted(rs.getString("IS_DELETED"));
				}
				// voList = BeanUtil.getBeans(NoteVO.class, rs);
				return vo;
			}
		});

		return vo;
	}

	public List<NoteVO> getNotesByIP(String saverIp) throws Exception {

		StringBuffer sb = new StringBuffer(300);
		sb.append(" SELECT T.NOTE_ID,               ");
		sb.append("        T.SAVER_IP,              ");
		sb.append("        T.NOTE_TITLE,            ");
		// sb.append("        T.NOTE_CONTENT,          ");
		sb.append("        to_char(T.INSERT_TIME,'YYYY-MM-DD') INSERT_TIME,        ");
		sb.append("        to_char(T.UPDATE_TIME,'YYYY-MM-DD') UPDATE_TIME,        ");
		sb.append("        T.IS_DELETED             ");
		sb.append("   FROM T_NOTEPAD T              ");
		sb.append("  WHERE T.SAVER_IP = ?	        ");
		sb.append("    AND T.IS_DELETED = 'N'       ");
		sb.append("  ORDER BY T.NOTE_ID    ");

		List<Object> paraList = new ArrayList<Object>();
		paraList.add(saverIp);
		@SuppressWarnings("unchecked")
		List<NoteVO> listVO = (List<NoteVO>)DBManager.executeQuery(EnvEnum.tpdev, sb.toString(), paraList, NoteVO.class);

		return listVO;
	}

	public int updateNote(NoteVO vo) throws Exception {

		// if(exist then update)

		StringBuffer sb = new StringBuffer(300);
		sb.append(" update T_NOTEPAD  set                             	");
		// sb.append("   NOTE_ID = ?,                                 	");
		// sb.append("    SAVER_IP = ?,                               	");
		sb.append("    NOTE_TITLE = ?,                                	");
		sb.append("    NOTE_CONTENT = empty_clob(),                   	");
		// sb.append("    INSERT_TIME,                                	");
		sb.append("    UPDATE_TIME = SYSDATE                        	");
		// sb.append("    IS_DELETED )                                	");
		sb.append(" where                                             	");
		sb.append("    NOTE_ID = ?	  									");

		List<Object> paraList = new ArrayList<Object>();
		paraList.add(vo.getNoteTitle());
		paraList.add(vo.getNoteId());

		int rowCnt = DBManager.executeUpdate(EnvEnum.tpdev, sb.toString(), paraList);
		
		if (rowCnt > 0) {
			// Clob 要单独保存
			Tools.writeClob(vo.getNoteContent().toCharArray(), vo.getNoteContent().length(), "T_NOTEPAD", "NOTE_CONTENT", "NOTE_ID = '" + vo.getNoteId() + "'");
		}

		return rowCnt;
	}

	public int deleteNote(String noteId) throws Exception {

		// if(exist then update)

		StringBuffer sb = new StringBuffer(300);
		sb.append(" update T_NOTEPAD  set                             ");
		// sb.append("   NOTE_ID = ?,                                 ");
		// sb.append("    SAVER_IP = ?,                               ");
		// sb.append("    NOTE_TITLE = ?,                             ");
		// sb.append("    NOTE_CONTENT = empty_clob(),                ");
		// sb.append("    INSERT_TIME,                                ");
		sb.append("    UPDATE_TIME = SYSDATE,                         ");
		sb.append("    IS_DELETED = 'Y'                               ");
		sb.append(" where                                             ");
		sb.append("    NOTE_ID = ?	  ");

		List<Object> paraList = new ArrayList<Object>();
		paraList.add(noteId);
		// paraList.add(vo.getSaverIp());
		// paraList.add(vo.getNoteTitle());
		// paraList.add(vo.getNoteContent());

		int rowCnt = DBManager.executeUpdate(EnvEnum.tpdev, sb.toString(), paraList);

		return rowCnt;
	}

	public int insertNote(NoteVO vo) throws Exception {

		// if(exist then update)

		StringBuffer sb = new StringBuffer(300);
		sb.append(" INSERT INTO T_NOTEPAD                               ");
		sb.append("   (NOTE_ID,                                           ");
		sb.append("    SAVER_IP,                                          ");
		sb.append("    NOTE_TITLE,                                        ");
		sb.append("    NOTE_CONTENT,                                      ");
		sb.append("    INSERT_TIME,                                       ");
		sb.append("    UPDATE_TIME,                                       ");
		sb.append("    IS_DELETED)                                        ");
		sb.append(" VALUES                                                ");
		sb.append("   (?, ?, ?, empty_clob(), SYSDATE, SYSDATE, 'N')	   ");
 
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(vo.getNoteId());
		paraList.add(vo.getSaverIp());
		paraList.add(vo.getNoteTitle());
		// paraList.add(vo.getNoteContent());

		int rowCnt = DBManager.executeUpdate(EnvEnum.tpdev, sb.toString(), paraList);

		if (rowCnt > 0) {
			// Clob 要单独保存
			Tools.writeClob(vo.getNoteContent().toCharArray(), vo.getNoteContent().length(), "T_NOTEPAD", "NOTE_CONTENT", "NOTE_ID = '" + vo.getNoteId() + "'");
		}

		return rowCnt;
	}
}
