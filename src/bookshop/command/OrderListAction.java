package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		List<BuyDataBean> buyLists =null;
		int count= 0;
		BuyDBBean buyProcess = buyDBBean.getInstance();
		count = buyProcess.getInstance();
		
		if (count < 0) {
			buyLists = buyProcess.getBuyList();
			request.setAttribute("buyLists", buyLists);
		}
		
		request.setAttribute("count", new Integer(count));
		request.setAttribute("type", new Integer(0));
		return "/mngr/orderedProduct/orderList.jsp";
	}
	

}
