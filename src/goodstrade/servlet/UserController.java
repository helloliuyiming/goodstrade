package goodstrade.servlet;

import goodstrade.entity.Goods;
import goodstrade.entity.User;
import goodstrade.service.CollectService;
import goodstrade.service.GoodsService;
import goodstrade.service.UserService;
import goodstrade.service.impl.CollectServiceImpl;
import goodstrade.service.impl.GoodsServiceImpl;
import goodstrade.service.impl.UserServiceImpl;
import me.lym.dao.ImageDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * Servlet implementation class UserContruller
 */
@WebServlet("/user/*")
public class UserController extends BaseServlet {
    private static final long serialVersionUID = 1L;
    UserService userService;
    GoodsService goodsService;
    CollectService collectService;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        userService = new UserServiceImpl();
        goodsService = new GoodsServiceImpl();
        collectService = new CollectServiceImpl();

    }

    //	/user/checkUser--判断用户名是否可用
    public void checkUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String result = userService.isExistUser(username);
        writer.write(result);

    }

    //	user/register---用户注册
    public void register(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            FileItemFactory factory = new DiskFileItemFactory();
            // 文件上传处理器
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析请求信息
            List items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            // 对请求信息进行判断
            Iterator iter = items.iterator();
            String imageName = UUID.randomUUID().toString();
            Map<String, String> userMap = new HashMap();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                // 信息为普通的格式
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String value = item.getString("utf-8");
                    userMap.put(fieldName, value);
                } else {
                    String resource = "mybatis-config2.xml";
                    InputStream inputStream = null;
                    try {
                        inputStream = Resources.getResourceAsStream(resource);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                    SqlSession sqlSession = sqlSessionFactory.openSession();
                    ImageDao imageDao = sqlSession.getMapper(ImageDao.class);
                    try {
                        System.out.println("存储图片：");
                        imageDao.save(imageName,item.get());
                        sqlSession.commit();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }finally {
                        System.out.println("存储照片成功");
                        sqlSession.close();
                    }

                }
            }
            User user = new User(userMap.get("username"), userMap.get("password"), userMap.get("sex"), userMap.get("phonenumber"), userMap.get("email"), imageName);
            boolean registerResult = userService.register(user);
            if (registerResult) {
                writer.write("注册成功!  <a href=\"" + basePath + "login.jsp\">去登陆</a>");
            } else {
                writer.write("注册失败!");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //	/user/login--用户登录
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String loginResult = userService.checkLogin(username, password);
        if (loginResult.equals("登录成功!")) {
            User user = userService.getUser(username);
            //	将登录的用户保存到session中
            session.setAttribute("user", user);
            //	获取收藏商品的信息
            //	获取用户收藏的商品id
            ArrayList<Integer> collectGid = collectService.getCollectGid(user.getUserId());
            if (collectGid.size() != 0 && collectGid != null) {
                session.setAttribute("collectGid", collectGid);
                //	获取用户收藏的商品信息
                ArrayList<Goods> collectGoods = collectService.getCollectGoods(user.getUserId());
                session.setAttribute("collectGoods", collectGoods);
            }
            writer.write(loginResult);
        } else {
            writer.write(loginResult);
        }

    }

    //	/user/update--修改个人信息
    public void update(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String phonenumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");
        String image = request.getParameter("image");
        User user = new User(username, password, gender, phonenumber, email, image);
        String registerResult = userService.update(user);
        writer.write(registerResult);
        writer.flush();
        writer.close();
    }

    //	/user/validate--判断验证码是否输入正确
    public void validate(HttpServletRequest request, HttpServletResponse response) {
        String inputCodes = request.getParameter("veritycode");
        HttpSession session = request.getSession();
        String autoCodes = (String) session.getAttribute("codes");
        if (inputCodes.equalsIgnoreCase(autoCodes)) {
            writer.write("true");
        } else {
            writer.write("false");
        }

    }

    //	获取用户收藏的商品信息
    public void getCollectGoods(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        int uid = user.getUserId();
        ArrayList<Goods> collectGoods = collectService.getCollectGoods(uid);
        session.setAttribute("collectGoods", collectGoods);
    }

    //	获取用户收藏的商品id
    public void getCollectGid(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        int uid = user.getUserId();
        ArrayList<Integer> collectGid = collectService.getCollectGid(uid);
        session.setAttribute("collectGid", collectGid);
    }


    //	添加收藏商品
    public void addcollect(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        Integer gid = Integer.valueOf(request.getParameter("gid"));
        int uid = user.getUserId();
        //	收藏商品
        boolean collectResult = collectService.insertCollect(uid, gid);
        if (collectResult) {
            writer.write("收藏成功!");
        } else {
            writer.write("收藏失败,请稍后重试!!");
        }
    }

    //	取消收藏商品

}
