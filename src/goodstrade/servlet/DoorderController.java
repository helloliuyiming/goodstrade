package goodstrade.servlet;

import goodstrade.entity.Goods;
import goodstrade.entity.User;
import goodstrade.service.CollectService;
import goodstrade.service.GoodsService;
import goodstrade.service.impl.CollectServiceImpl;
import goodstrade.service.impl.GoodsServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Servlet implementation class DoorderContruller
 */
@WebServlet("/order/*")
public class DoorderController extends BaseServlet {
    private static final long serialVersionUID = 1L;
    GoodsService goodsService = null;
    CollectService collectService = null;

    public DoorderController() {
        super();
        goodsService = new GoodsServiceImpl();
        collectService = new CollectServiceImpl();
    }

    //	跳转至订单界面
    public void toOrder(HttpServletRequest request, HttpServletResponse response) {
        //	获取订单商品id
        User user = (User) session.getAttribute("user");
        int uid = user.getUserId();
        String ordergid = request.getParameter("ordergid");
        String[] gidSplit = ordergid.split(",");
        ArrayList<Integer> gidList = new ArrayList<Integer>();
        for (int i = 0; i < gidSplit.length; i++) {
            gidList.add(Integer.valueOf(gidSplit[i]));
        }
        //		获取订单商品
        ArrayList<Goods> orderGoods = new ArrayList<Goods>();
        orderGoods = goodsService.getOrderGoods(gidList);
        session.setAttribute("orderGoods", orderGoods);
        //	取消收藏，修改商品状态
        boolean removeCollect = collectService.removeCollect(uid, gidList);
        boolean result = goodsService.modifyStatus(gidList);
        if (result && removeCollect) {
            ArrayList<Goods> hotGoodsList = goodsService.hotSellingGoods();
            session.removeAttribute("hotGoodsList");
            session.setAttribute("hotGoodsList", hotGoodsList);
            //			获取新品
            ArrayList<Goods> newGoodsList = goodsService.selectNewGoods();
            session.removeAttribute("newGoodsList");
            session.setAttribute("newGoodsList", newGoodsList);
            //	获取猜你喜欢--价格升序
            ArrayList<Goods> guessLikeList = goodsService.guessLikeGoods();
            session.removeAttribute("guessLikeList");
            session.setAttribute("guessLikeList", guessLikeList);
            //	获取订单商品信息
            ArrayList<Integer> collectGid = collectService.getCollectGid(user.getUserId());
            if (collectGid.size() != 0 && collectGid != null) {
                session.setAttribute("collectGid", collectGid);
                //	获取用户收藏的商品信息
                ArrayList<Goods> collectGoods = collectService.getCollectGoods(user.getUserId());
                session.setAttribute("collectGoods", collectGoods);
            } else {
                session.removeAttribute("collectGid");
                session.removeAttribute("collectGoods");
            }
        }
        try {
            response.sendRedirect(basePath + "do-order.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //	跳转至支付界面
    public void toPayment(HttpServletRequest request, HttpServletResponse response) {
        //	获取支付方式和支付金额
        String payMethod = request.getParameter("payMethod");
        String allprice = request.getParameter("allprice");
        String allfreight = request.getParameter("allfreight");
        session.setAttribute("allprice", allprice);
        session.setAttribute("allfreight", allfreight);
        session.setAttribute("allpay", Integer.valueOf(allprice) + Integer.valueOf(allfreight));
    }
}
