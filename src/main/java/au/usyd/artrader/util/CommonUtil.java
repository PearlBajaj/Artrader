package au.usyd.artrader.util;

import au.usyd.artrader.domain.User;
import au.usyd.artrader.interceptor.LoginInterceptor;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {

    private static final int PAGE_NAV_SIZE = 5;

    public static long getLoginUserId(HttpSession httpSession) {
        return ((User)httpSession.getAttribute(LoginInterceptor.LOGIN)).getUserId();
    }

    public static Map<String, Integer> getPagination(int page, int size, long totalCount) {
        if(totalCount == 0) {
            return null;
        }

        Map<String, Integer> pagination = new HashMap<String, Integer>();

        int startPage = ((page-1)/PAGE_NAV_SIZE)*PAGE_NAV_SIZE + 1;
        int prevPage = startPage == 1 ? 1 : startPage - 1;
        int endPage = ((page-1)/PAGE_NAV_SIZE + 1)*PAGE_NAV_SIZE;
        int lastPage = (int)(totalCount/size) + (totalCount%size == 0 ? 0 : 1);
        endPage = endPage > lastPage ? lastPage : endPage;
        int nextPage = endPage == lastPage ? lastPage : endPage + 1;

        pagination.put("firstPage", 1);
        pagination.put("prevPage", prevPage);
        pagination.put("nextPage", nextPage);
        pagination.put("startPage", startPage);
        pagination.put("endPage", endPage);
        pagination.put("lastPage", lastPage);
        pagination.put("currPage", page);

        return pagination;
    }

    public static int getOffset(int page, int size) {
        return (page - 1) * size;
    }

    public static boolean isEqual(Long long1, Long long2) {
        return long1.compareTo(long2) == 0;
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
