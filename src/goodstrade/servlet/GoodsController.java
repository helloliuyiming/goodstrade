package goodstrade.servlet;

import goodstrade.entity.Goods;
import goodstrade.entity.User;
import goodstrade.service.CollectService;
import goodstrade.service.GoodsService;
import goodstrade.service.UserService;
import goodstrade.service.impl.CollectServiceImpl;
import goodstrade.service.impl.GoodsServiceImpl;
import goodstrade.service.impl.UserServiceImpl;
import goodstrade.utils.DateUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Servlet implementation class GoodsContruller
 */
@WebServlet("/goods/*")
public class GoodsController extends BaseServlet {
    private static final long serialVersionUID = 1L;
    private static final String Goods = null;
    CollectService collectService = null;
    UserService userService = null;
    GoodsService goodsService = new GoodsServiceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsController() {
        super();
        collectService = new CollectServiceImpl();
        userService = new UserServiceImpl();
    }

    //	初始化主页数据(爆款，新品，猜你喜欢)
    public void getAllGoods(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Goods> hotGoodsList = (ArrayList<Goods>) session.getAttribute("hotGoodsList");
        if (hotGoodsList == null || hotGoodsList.size() == 0) {
            hotGoodsList = goodsService.hotSellingGoods();
            session.setAttribute("hotGoodsList", hotGoodsList);
            //			获取新品
            ArrayList<Goods> newGoodsList = goodsService.selectNewGoods();
            session.setAttribute("newGoodsList", newGoodsList);
            //	获取猜你喜欢--价格升序
            ArrayList<Goods> guessLikeList = goodsService.guessLikeGoods();
            session.setAttribute("guessLikeList", guessLikeList);
            //	获取用户收藏的商品id
            User user = (User) session.getAttribute("user");
            if (user != null) {
                int uid = user.getUserId();
                ArrayList<Integer> collectGid = collectService.getCollectGid(uid);
                session.setAttribute("collectGid", collectGid);
            }
        }
        try {
            response.sendRedirect(basePath + "index.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //	通过一种或多种类别获取商品
    public void search(HttpServletRequest request, HttpServletResponse response) {
        String sort = request.getParameter("sort");
        ArrayList<Goods> listBySort = null;
        listBySort = goodsService.selectBySorts(sort);
        //	存入所搜素的类别
        session.removeAttribute("goodsName");
        session.setAttribute("sort", sort);
        //	存入通过类别搜索的商品集合
        session.removeAttribute("listByName");
        session.setAttribute("listBySort", listBySort);
        try {
            response.sendRedirect(basePath + "search.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //	通过一种或多种类别获取商品
    public void searchByName(HttpServletRequest request, HttpServletResponse response) {
        String goodsName = request.getParameter("goodsName");
        String history1 = (String) session.getAttribute("history1");
        String history2 = (String) session.getAttribute("history2");
        String history3 = (String) session.getAttribute("history3");
        if (!goodsName.equals(history1) && !goodsName.equals(history2) && !goodsName.equals(history3)) {
            if (history1 == null) {
                session.setAttribute("history1", goodsName);
            } else if (history2 == null) {
                session.setAttribute("history2", goodsName);
            } else if (history3 == null) {
                session.setAttribute("history3", goodsName);
            } else {
                session.setAttribute("history3", history2);
                session.setAttribute("history2", history1);
                session.setAttribute("history1", goodsName);
            }
        }
        ArrayList<Goods> listByName = null;
        listByName = goodsService.selectByName(goodsName);
        //	存入所搜素的类别
        session.removeAttribute("sort");
        session.setAttribute("goodsName", goodsName);
        //	存入通过类别搜索的商品集合
        session.removeAttribute("listBySort");
        session.setAttribute("listByName", listByName);
        try {
            response.sendRedirect(basePath + "search.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //	获取爆款商品
    public void baokuanGoods(HttpServletRequest request, HttpServletResponse response) {
        String sort = request.getParameter("sort");
        ArrayList<Goods> listBySort = null;
        if (sort == null) {
            listBySort = goodsService.hotSellingGoods();
        } else {
            listBySort = goodsService.hotSellingGoods(sort);
        }
        session.removeAttribute("goodsName");
        session.removeAttribute("listByName");
        session.setAttribute("sort", sort);
        session.setAttribute("listBySort", listBySort);
        try {
            response.sendRedirect(basePath + "search.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //	获取新品
    public void xinpinGoods(HttpServletRequest request, HttpServletResponse response) {
        String sort = request.getParameter("sort");
        ArrayList<Goods> listBySort = null;
        if (sort == null) {
            listBySort = goodsService.selectNewGoods();
        } else {
            listBySort = goodsService.selectNewGoods(sort);
        }
        session.removeAttribute("goodsName");
        session.removeAttribute("listByName");
        session.setAttribute("sort", sort);
        session.setAttribute("listBySort", listBySort);
        try {
            response.sendRedirect(basePath + "search.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //	获取猜你喜欢
    public void guesslikeGoods(HttpServletRequest request, HttpServletResponse response) {
        String sort = request.getParameter("sort");
        ArrayList<Goods> listBySort = null;
        if (sort == null) {
            listBySort = goodsService.guessLikeGoods();
        } else {
            listBySort = goodsService.guessLikeGoods(sort);
        }
        session.removeAttribute("goodsName");
        session.removeAttribute("listByName");
        session.setAttribute("sort", sort);
        session.setAttribute("listBySort", listBySort);
        try {
            response.sendRedirect(basePath + "search.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //	获取爆款商品
    public void baokuanGoodsByName(HttpServletRequest request, HttpServletResponse response) {
        String goodsName = request.getParameter("goodsName");
        ArrayList<Goods> listByName = null;
        if (goodsName == null) {
            listByName = goodsService.hotSellingGoods();
        } else {
            listByName = goodsService.hotSellingGoodsByName(goodsName);
        }
        session.removeAttribute("sort");
        session.removeAttribute("listBySort");
        session.setAttribute("goodsName", goodsName);
        session.setAttribute("listByName", listByName);
        try {
            response.sendRedirect(basePath + "search.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //		获取新品
    public void xinpinGoodsByName(HttpServletRequest request, HttpServletResponse response) {
        String goodsName = request.getParameter("goodsName");
        ArrayList<Goods> listByName = null;
        if (goodsName == null) {
            listByName = goodsService.selectNewGoods();
        } else {
            listByName = goodsService.selectNewGoodsByName(goodsName);
        }
        session.removeAttribute("sort");
        session.removeAttribute("listBySort");
        session.setAttribute("goodsName", goodsName);
        session.setAttribute("listByName", listByName);
        try {
            response.sendRedirect(basePath + "search.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //		获取猜你喜欢
    public void guesslikeGoodsByName(HttpServletRequest request, HttpServletResponse response) {
        String goodsName = request.getParameter("goodsName");
        ArrayList<Goods> listByName = null;
        if (goodsName == null) {
            listByName = goodsService.guessLikeGoods();
        } else {
            listByName = goodsService.guessLikeGoodsByName(goodsName);
        }
        session.removeAttribute("sort");
        session.removeAttribute("listBySort");
        session.setAttribute("goodsName", goodsName);
        session.setAttribute("listByName", listByName);
        try {
            response.sendRedirect(basePath + "search.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //	筛选名称获取的商品集合
    public void screenByName(HttpServletRequest request, HttpServletResponse response) {
        String goodsName = (String) session.getAttribute("goodsName");
        int columnNumber = Integer.valueOf(request.getParameter("columnNumber"));
        int orderNumber = Integer.valueOf(request.getParameter("orderNumber"));
        ArrayList<Goods> listByName = goodsService.screenByName(goodsName, columnNumber, orderNumber);
        session.setAttribute("columnNumber", columnNumber);
        session.setAttribute("orderNumber", orderNumber);
        session.setAttribute("listByName", listByName);
        try {
            response.sendRedirect(basePath + "search.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //	筛选商品
    public void screen(HttpServletRequest request, HttpServletResponse response) {
        String sort = (String) session.getAttribute("sort");
        int columnNumber = Integer.valueOf(request.getParameter("columnNumber"));
        int orderNumber = Integer.valueOf(request.getParameter("orderNumber"));
        ArrayList<Goods> listBySort = goodsService.screen(sort, columnNumber, orderNumber);
        session.setAttribute("columnNumber", columnNumber);
        session.setAttribute("orderNumber", orderNumber);
        session.setAttribute("listBySort", listBySort);
        try {
            response.sendRedirect(basePath + "search.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //	收藏商品商品收藏量+1
    public void addCollect(HttpServletRequest request, HttpServletResponse response) {
        //	商品信息中收藏量加一
        int gid = Integer.valueOf(request.getParameter("gid"));
        boolean addVisitor = goodsService.addVisitor(Integer.valueOf(gid));
        //	用户商品收藏表插入收藏记录
        User user = (User) session.getAttribute("user");
        int uid = user.getUserId();
        boolean collectResult = collectService.insertCollect(uid, gid);
        if (addVisitor && collectResult) {
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
            //	获取用户收藏的商品id
            ArrayList<Integer> collectGid = collectService.getCollectGid(uid);
            session.removeAttribute("collectGid");
            session.setAttribute("collectGid", collectGid);
            //	获取用户收藏的商品信息
            ArrayList<Goods> collectGoods = collectService.getCollectGoods(uid);
            session.removeAttribute("collectGoods");
            session.setAttribute("collectGoods", collectGoods);
            writer.write("收藏成功");
        } else {
            writer.write("收藏失败");
        }
    }

    //	取消收藏商品收藏量-1
    public void deleteCollect(HttpServletRequest request, HttpServletResponse response) {
        //		商品信息中收藏量减一
        int gid = Integer.valueOf(request.getParameter("gid"));
        boolean removeVisitor = goodsService.removeVisitor(Integer.valueOf(gid));
        //	用户商品收藏表删除收藏记录
        User user = (User) session.getAttribute("user");
        int uid = user.getUserId();
        boolean deleteResult = collectService.deleteCollect(uid, gid);
        if (removeVisitor && deleteResult) {
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
            //	获取用户收藏的商品id
            ArrayList<Integer> collectGid = collectService.getCollectGid(uid);
            if (collectGid.size() != 0 && collectGid != null) {
                session.setAttribute("collectGid", collectGid);
                //	获取用户收藏的商品信息
                ArrayList<Goods> collectGoods = collectService.getCollectGoods(uid);
                session.setAttribute("collectGoods", collectGoods);
            } else {
                session.removeAttribute("collectGid");
                session.removeAttribute("collectGoods");
            }
            writer.write("取消收藏成功!");
        } else {
            writer.write("取消收藏失败!");
        }
    }

    //	同时取消多个收藏
    public void deletemoreCollect(HttpServletRequest request, HttpServletResponse response) {
        //	获取用户id
        User user = (User) session.getAttribute("user");
        int uid = user.getUserId();
        //	获取收藏商品的商品id
        String gids = request.getParameter("gid");
        String[] gidSplit = gids.split(",");
        int count = 0;
        for (int i = 0; i < gidSplit.length; i++) {
            int gid = Integer.valueOf(gidSplit[i]);
            boolean removeVisitor = goodsService.removeVisitor(gid);
            boolean deleteResult = collectService.deleteCollect(uid, gid);
            if (removeVisitor && deleteResult) {
                count++;
            }
        }
        if (count == gidSplit.length) {
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
            //	获取用户收藏的商品id
            ArrayList<Integer> collectGid = collectService.getCollectGid(uid);
            if (collectGid.size() != 0 && collectGid != null) {
                session.setAttribute("collectGid", collectGid);
                //	获取用户收藏的商品信息
                ArrayList<Goods> collectGoods = collectService.getCollectGoods(uid);
                session.setAttribute("collectGoods", collectGoods);
            } else {
                session.removeAttribute("collectGid");
                session.removeAttribute("collectGoods");
            }
            writer.write("清除成功!");
        } else {
            writer.write("清除失败!");
        }
    }

    //	进入商品详情页添加浏览历史
    public void addHistory(HttpServletRequest request, HttpServletResponse response) {

    }

    //	清除浏览历史
    public void deleteHistory(HttpServletRequest request, HttpServletResponse response) {

    }


    //	商品详情
//    public void goodsdetail(HttpServletRequest request, HttpServletResponse response) {
//        int gid = Integer.valueOf(request.getParameter("gid"));
//        Goods goods = goodsService.selectGoods(gid);
//        User user = (User) session.getAttribute("user");
//        if (user != null) {
//            boolean insertHistory = collectService.insertHistory(user.getUserId(), gid);
//            ArrayList<Goods> hotGoodsList = goodsService.hotSellingGoods();
//            session.removeAttribute("hotGoodsList");
//            session.setAttribute("hotGoodsList", hotGoodsList);
//            //			获取新品
//            ArrayList<Goods> newGoodsList = goodsService.selectNewGoods();
//            session.removeAttribute("newGoodsList");
//            session.setAttribute("newGoodsList", newGoodsList);
//            //	获取猜你喜欢--价格升序
//            ArrayList<Goods> guessLikeList = goodsService.guessLikeGoods();
//            session.removeAttribute("guessLikeList");
//            session.setAttribute("guessLikeList", guessLikeList);
//        }
//        String[] imageNames = goods.getGoodsImage().split(",");
//        ArrayList<String> imageList = new ArrayList<String>();
//        for (int i = 0; i < imageNames.length; i++) {
//            imageList.add(imageNames[i]);
//        }
//        session.setAttribute("imageList", imageList);
//        User goodsFrom = userService.getUser(goods.getGoodsUid());
//        session.setAttribute("goodsFrom", goodsFrom);
//        session.setAttribute("goods", goods);
//        try {
//            response.sendRedirect(basePath + "goodsdetail.jsp");
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    //	发布商品
//    public void addGoods(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            request.setCharacterEncoding("utf-8");
//            response.setContentType("text/html;charset=utf-8");
//            FileItemFactory factory = new DiskFileItemFactory();
//            // 文件上传处理器
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            // 解析请求信息
//            List items = null;
//            try {
//                items = upload.parseRequest(request);
//            } catch (FileUploadException e) {
//                e.printStackTrace();
//            }
//            String uploadPath = request.getServletContext().getRealPath("image");
//            if (!new File(uploadPath).exists()) {    //	文件不存在就创建
//                new File(uploadPath).mkdirs();
//            }
//            // 对请求信息进行判断
//            Iterator iter = items.iterator();
//            String imageName = "";
//            String sort = "";
//            Map<String, String> goodsInfoMap = new HashMap<String, String>();
//            while (iter.hasNext()) {
//                FileItem item = (FileItem) iter.next();
//                // 信息为普通的格式
//                if (item.isFormField()) {
//                    String fieldName = item.getFieldName();
//                    String value = item.getString("utf-8");
//                    if (fieldName.equals("sort")) {
//                        sort = sort + value + ",";
//                    } else {
//                        goodsInfoMap.put(fieldName, value);
//                    }
//                } else {
//                    // 信息为文件格式
//                    //获得上传图片的名称
//                    String fileName = item.getName();
//                    if (fileName != "") {
//                        int index = fileName.lastIndexOf("\\");
//                        fileName = fileName.substring(index + 1);
//                        imageName = imageName + fileName + ",";
//                        // String basePath = request.getSession().getServletContext().getRealPath("/images");
//                        //打印当前位置
//                        //	System.out.println(basePath);
//                        File file = new File(uploadPath, fileName);
//                        try {
//                            item.write(file);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//            User user = (User) session.getAttribute("user");
//            int uid = user.getUserId();
//            //	添加商品
//            Goods goods = new Goods(goodsInfoMap.get("name"),
//                    Float.valueOf(goodsInfoMap.get("price")),
//                    Float.valueOf(goodsInfoMap.get("freight")),
//                    DateUtils.utilToSql(DateUtils.strToUtil(goodsInfoMap.get("buytime"))),
//                    goodsInfoMap.get("buyof"), goodsInfoMap.get("broken"),
//                    goodsInfoMap.get("details"), imageName, uid, sort, 0, "public");
//            boolean result = goodsService.insert(goods);
//            if (result) {
//                ArrayList<Goods> newGoods = goodsService.selectNewGoods();
//                Goods newgoods = newGoods.get(1);
//                writer.write("<h2>添加商品成功!<a href=\"" + basePath + "goodsdetail.jsp?gid=" + newgoods.getGoodsId() + "\">去查看</a></h2></br>");
//                writer.write("<h2><a href=\"" + basePath + "addgoods.jsp\">继续添加</a></h2></br>");
//                writer.write("<h2><a href=\"" + basePath + "personal.jsp\">个人中心</a></h2></br>");
//                writer.write("<h2><a href=\"" + basePath + "index.jsp\">返回主页</a></h2></br>");
//            } else {
//                writer.write("<h2>添加商品失败!<a href=\"" + basePath + "detail.jsp\">返回主页</a></h2></br>");
//                writer.write("<h2><a href=\"" + basePath + "addgoods.jsp\">重新添加</a></h2></br>");
//            }
////			for (Entry<String, String> entry : goodsInfoMap.entrySet()) {
////				System.out.println(entry.getKey()+":"+entry.getValue());
////			}
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

}
