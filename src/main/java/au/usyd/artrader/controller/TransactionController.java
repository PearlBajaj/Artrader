package au.usyd.artrader.controller;

import au.usyd.artrader.domain.*;
import au.usyd.artrader.service.ArtworkService;
import au.usyd.artrader.service.TransactionService;
import au.usyd.artrader.service.UserService;
import au.usyd.artrader.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/transaction/**")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    TransactionService transactionService;

    @Autowired
    ArtworkService artworkService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String buyArtwork(HttpSession httpSession,
                             @RequestParam("saleId") long saleId) {
        long loginUserId = CommonUtil.getLoginUserId(httpSession);
        transactionService.buyArtwork(saleId, loginUserId);

        return "redirect:orders";
    }

    @RequestMapping(value = "orders", method = RequestMethod.GET)
    public ModelAndView getOrders(HttpSession httpSession) {

        Long loginUserId = CommonUtil.getLoginUserId(httpSession);

        List<Transaction> transactions = transactionService.getTransactionsByBuyerId(loginUserId);


        List<TransactionVo> transactionVos = new LinkedList<>();

        for(int i=0; i<transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            Artwork artwork = artworkService.getArtwork(transaction.getArtworkId());
            User artist = userService.getUser(artwork.getArtistId());
            User buyer = userService.getUser(transaction.getBuyerId());
            User seller = userService.getUser(transaction.getSellerId());

            transactionVos.add(new TransactionVo(transaction, artist, seller, buyer, artwork));
        }

        return new ModelAndView("transaction/transactionList", "transactionVos", transactionVos);
    }

    @RequestMapping(value = "{transactionId}", method = RequestMethod.GET)
    public ModelAndView getOrder(@PathVariable("transactionId") long transactionId) {
        Transaction transaction = transactionService.getTransaction(transactionId);
        Long artworkId = transaction.getArtworkId();

        User buyer = userService.getUser(transaction.getBuyerId());
        User seller = userService.getUser(transaction.getSellerId());
        Artwork artworkVo = artworkService.getArtwork(artworkId);

        Map<String, Object> myModel = new HashMap<String, Object>();

        myModel.put("buyer", buyer);
        myModel.put("seller", seller);
        myModel.put("artworkVo", artworkVo);
        myModel.put("transaction", transaction);

        String viewName = "transaction/transactionDetail";

    return new ModelAndView(viewName, myModel);
    }

    @RequestMapping(value = "shipping")
    public ModelAndView getShipping(HttpSession httpSession) {
        Long loginUserId = CommonUtil.getLoginUserId(httpSession);

        List<Transaction> transactions = transactionService.getTransactionsBySellerId(loginUserId);


        List<TransactionVo> transactionVos = new LinkedList<>();

        for(int i=0; i<transactions.size(); i++) {

            if(!transactions.get(i).isShipping()) {
                Transaction transaction = transactions.get(i);
                Artwork artwork = artworkService.getArtwork(transaction.getArtworkId());
                User artist = userService.getUser(artwork.getArtistId());
                User buyer = userService.getUser(transaction.getBuyerId());
                User seller = userService.getUser(transaction.getSellerId());

                transactionVos.add(new TransactionVo(transaction, artist, seller, buyer, artwork));
            }
        }

        return new ModelAndView("transaction/shippingList", "transactionVos", transactionVos);
    }

    @RequestMapping(value = "shipping/{transactionId}")
    public ModelAndView getShippingView(HttpSession httpSession,
                                        @PathVariable("transactionId") long transactionId) {
        TransactionVo transaction = (TransactionVo)transactionService.getShipping(transactionId);
        long loginUserId = CommonUtil.getLoginUserId(httpSession);
        if(transaction == null || transaction.isShipping() || loginUserId != transaction.getSellerId()) {
            return new ModelAndView("error", "errorMessage", "There is no shipping information.");
        }
        return new ModelAndView("transaction/shippingDetail", "transaction", transaction);
    }

    @RequestMapping(value = "shipping/{transactionId}", method = RequestMethod.PUT)
    public String completeToShip(@PathVariable("transactionId") long transactionId) {
        transactionService.completeToShip(transactionId);
        return "redirect:/transaction/shipping";
    }
}
