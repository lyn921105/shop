package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.bean.QnaDBBean;
import bookshop.bean.QnaDataBean;

public class QnaListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		List<QnaDataBean> qnaLists;
		 QnaDBBean qnaProcess = QnaDBBean.getInstance();
		 int count = qnaProcess.getArticleCount();
		 
		 if (count > 0) {
			qnaLists = qnaProcess.getArticles(count);
			request.setAttribute("qnaLists", qnaLists);
		}
		 request.setAttribute("count", new Integer(count));
		 request.setAttribute("type", new Integer(0));
		return "/mngr/qnaProcess/qnaList.jsp";
	}
	

}
