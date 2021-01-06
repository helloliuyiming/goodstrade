package goodstrade.filter;

import goodstrade.entity.Goods;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet Filter implementation class IndexShowFilter
 */
public class IndexShowFilter implements Filter {

    /**
     * Default constructor.
     */
    public IndexShowFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        ArrayList<Goods> hotGoodsList = (ArrayList<Goods>) session.getAttribute("hotGoodsList");
        if (hotGoodsList == null) {
            request.getRequestDispatcher("goods/getAllGoods").forward(request, response);
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
