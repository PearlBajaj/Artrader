package au.usyd.artrader.controller;

import au.usyd.artrader.domain.*;
import au.usyd.artrader.service.ArtworkService;
import au.usyd.artrader.service.SaleService;
import au.usyd.artrader.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/sale/**")
public class SaleController {

    private static final Logger logger = LoggerFactory.getLogger(SaleController.class);
    private static final int PAGE_SIZE = 8;

    @Autowired
    private SaleService saleService;

    @Autowired
    private ArtworkService artworkService;

    @RequestMapping(value = "for-sale")
    public ModelAndView getForSales(HttpSession httpSession,
                                    @RequestParam(value = "page", defaultValue = "1") int page) {
        Map<String, Object> model = new HashMap<String, Object>();
        Long loginUserId = CommonUtil.getLoginUserId(httpSession);
        model.put("sales", saleService.getForSale(loginUserId, CommonUtil.getOffset(page, PAGE_SIZE), PAGE_SIZE));
        model.put("pagination", CommonUtil.getPagination(page, PAGE_SIZE, saleService.getForSaleCount(loginUserId)));

        return new ModelAndView("sale/saleList", "model", model);
    }

    @RequestMapping(value = "{saleId}", method = RequestMethod.GET)
    public ModelAndView getSaleDetails(HttpSession httpSession,
                                 @PathVariable("saleId") long saleId) {
        Sale sale = saleService.getSale(saleId);
        String viewName = "sale/saleDetail";

        Long loginUserId = CommonUtil.getLoginUserId(httpSession);

        if(CommonUtil.isEqual(loginUserId,sale.getSellerId())) {
            viewName = "sale/saleEdit";
        }

        return new ModelAndView(viewName, "sale", sale);
    }

    @RequestMapping(value = "{saleId}", method = RequestMethod.PUT)
    public String editSale(@PathVariable("saleId") long saleId,
                           @RequestParam("price") double price) {
        saleService.changePrice(saleId, price);

        return "redirect:for-sale";
    }

    @RequestMapping(value = "{saleId}", method = RequestMethod.DELETE)
    public String stopSelling(@PathVariable("saleId") long saleId) {
        saleService.stopSelling(saleId);

        return "redirect:for-sale";
    }

    @RequestMapping(value = "search")
    public ModelAndView search(HttpSession httpSession,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "category", defaultValue = "All") String category,
                               @RequestParam(value = "keyword", required = false) String keywordStr) {
        if(!Category.isValid(category)) {
            return new ModelAndView("error", "errorMessage", "The category is not available.");
        }

        if(Category.ALL.getValue().equals(category)) {
            category = null;
        }

        List<String> keywords = Collections.EMPTY_LIST;
        if(CommonUtil.isNotEmpty(keywordStr)) {
            keywords = Arrays.asList(keywordStr.split("\\s+"));
        }

        Long loginUserId = CommonUtil.getLoginUserId(httpSession);

        Map<String, Object> model = new HashMap<>();
        model.put("sales", saleService.getSales(loginUserId, category, keywords, CommonUtil.getOffset(page, PAGE_SIZE), PAGE_SIZE));
        model.put("pagination", CommonUtil.getPagination(page, PAGE_SIZE, saleService.getSalesCount(category, keywords)));
        model.put("keyword", keywordStr);

        return new ModelAndView("home", "model", model);
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView getResell(@RequestParam("artworkId") long artworkId) {
        Artwork artwork = artworkService.getArtwork(artworkId);

        return new ModelAndView("sale/saleAdd", "artwork", artwork);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String doResell(HttpSession httpSession,
                                 @RequestParam("artworkId") long artworkId,
                                 @RequestParam("price") double price) {
        long loginUserId = CommonUtil.getLoginUserId(httpSession);
        Artwork artwork = artworkService.getArtwork(artworkId);
        saleService.sellArtwork(artwork, loginUserId, price);

        return "redirect:for-sale";
    }
}
