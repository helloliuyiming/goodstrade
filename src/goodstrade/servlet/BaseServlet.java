package goodstrade.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public String basePath;
    public HttpSession session = null;
    public PrintWriter writer = null;
    String uploadPath;

    public BaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
        session = arg0.getSession();
        writer = arg1.getWriter();
        uploadPath = arg0.getServletContext().getRealPath("image");//	文件上传路径
        String path = arg0.getContextPath();
        basePath = arg0.getScheme() + "://" + arg0.getServerName() + ":" + arg0.getServerPort() + path + "/";
        session.setAttribute("basePath", basePath);
        //	获取请求的URI地址信息
        String url = arg0.getRequestURI();
        //	解决中文乱码
        url = java.net.URLDecoder.decode(new String(url.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8), "UTF-8");
        //	截取其中的方法名
        String methodName = url.substring(url.lastIndexOf("/") + 1);

        java.lang.reflect.Method method = null;
        Class clazz = this.getClass();
        try {
            //	使用反射机制获取在本类中声明了的方法
            method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            System.out.println(methodName + ":" + method);
            //	执行方法
            method.invoke(this, arg0, arg1);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
