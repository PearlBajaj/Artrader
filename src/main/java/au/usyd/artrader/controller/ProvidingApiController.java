package au.usyd.artrader.controller;

import au.usyd.artrader.domain.*;
import au.usyd.artrader.service.SaleService;
import au.usyd.artrader.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/external/**")
public class ProvidingApiController {
    private static final int PAGE_SIZE = 8;

    @Autowired
    SaleService saleService;

    @RequestMapping(value = "search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Response externalSearch(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "category", defaultValue = "All") String category,
                            @RequestParam(value = "keyword", required = false) String keywordStr) {
        if(!Category.isValid(category)) {
            return new Response("The category is not available.");
        }

        if(Category.ALL.getValue().equals(category)) {
            category = null;
        }

        List<String> keywords = Collections.EMPTY_LIST;
        if(CommonUtil.isNotEmpty(keywordStr)) {
            keywords = Arrays.asList(keywordStr.split("\\s+"));
        }

        List<Sale> sales = saleService.getSales(null, category, keywords, CommonUtil.getOffset(page, PAGE_SIZE), PAGE_SIZE);

        return new Response(sales.stream().map(sale -> {
            sale.getArtwork().setPhoto(null);
            User artist = new User();
            artist.setName(((SaleVo)sale).getArtist().getName());
            ((SaleVo)sale).setArtist(artist);
            return sale;
        }).collect(Collectors.toList()));
    }
}
