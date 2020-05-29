package au.usyd.artrader.controller;

import au.usyd.artrader.domain.*;
import au.usyd.artrader.service.*;
import au.usyd.artrader.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value="/artwork/**")
public class ArtworkController {

    private static final Logger logger = LoggerFactory.getLogger(ArtworkController.class);
    private static final int PAGE_SIZE = 8;

    @Autowired
    private ArtworkService artworkService;

    @Autowired
    private FavouriteService favouriteService;

    @Autowired
    private UserService userService;

    @Autowired
    private SaleService saleService;

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String addArtwork(Model model) {
        model.addAttribute("artwork", new Artwork());
        return "artwork/artworkAdd";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String addArtwork(@ModelAttribute("artwork")Artwork artwork, HttpSession httpSession) throws Exception {
        long userId = CommonUtil.getLoginUserId(httpSession);
        artwork.setArtistId(userId);
        artwork.setOwnerId(userId);
        artwork.setPhoto(Base64.getEncoder().encodeToString(artwork.getPhotoData().getBytes()));
        artworkService.addArtwork(artwork);

        return "redirect:/artwork/creations";
    }

    @RequestMapping(value="creations", method= RequestMethod.GET)
    public ModelAndView getCreations(HttpSession httpSession,
                                     @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                     @RequestParam(value = "category", defaultValue = "All") String category) {
        if(!Category.isValid(category)) {
            return new ModelAndView("error", "errorMessage", "The category is not available.");
        }

        if(Category.ALL.getValue().equals(category)) {
            category = null;
        }

        Map<String, Object> model = new HashMap<String, Object>();
        int offset = CommonUtil.getOffset(page, PAGE_SIZE);
        long loginUserId = CommonUtil.getLoginUserId(httpSession);
        model.put("artworks", artworkService.getCreations(loginUserId, category, offset, PAGE_SIZE));
        model.put("pagination", CommonUtil.getPagination(page, PAGE_SIZE, artworkService.getCreationsCount(loginUserId, category)));

        return new ModelAndView("artwork/creationList", "model", model);
    }

    @RequestMapping(value="collections", method= RequestMethod.GET)
    public ModelAndView getCollections(HttpSession httpSession,
                                       @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                       @RequestParam(value = "category", defaultValue = "All") String category) {
        if(!Category.isValid(category)) {
            return new ModelAndView("error", "errorMessage", "The category is not available.");
        }

        if(Category.ALL.getValue().equals(category)) {
            category = null;
        }

        Map<String, Object> model = new HashMap<String, Object>();
        int offset = CommonUtil.getOffset(page, PAGE_SIZE);
        long loginUserId = CommonUtil.getLoginUserId(httpSession);
        model.put("artworks", artworkService.getCollections(loginUserId, category, offset, PAGE_SIZE));
        model.put("pagination", CommonUtil.getPagination(page, PAGE_SIZE, artworkService.getCollectionsCount(loginUserId, category)));

        return new ModelAndView("artwork/collectionList", "model", model);
    }

    @RequestMapping(value = "{artworkId}", method = RequestMethod.GET)
    public ModelAndView getArtwork(HttpSession httpSession,
                                   @PathVariable("artworkId") long artworkId) {
        Artwork artwork = artworkService.getArtwork(artworkId);

        String viewName = "artwork/artworkDetail";

        return new ModelAndView(viewName, "artwork", artwork);
    }

    @RequestMapping(value = "favourites", method = RequestMethod.GET)
    public ModelAndView getFavourites(HttpSession httpSession,
                                      @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                      @RequestParam(value = "category", defaultValue = "All") String category){
        Long loginUserId = CommonUtil.getLoginUserId(httpSession);
        List<Favourite> favs = favouriteService.getFavouritesbyuserid(loginUserId);
        List<FavouriteVo> favVo = new LinkedList<>();
        for(int i = 0; i < favs.size(); i++){
            Favourite fav = favs.get(i);
            Artwork artwork = artworkService.getArtwork(fav.getArtworkId());
            User artist = userService.getUser(fav.getUserId());
            favVo.add(new FavouriteVo(fav, artwork, artist));
        }
        return new ModelAndView("artwork/favouriteList","favVo",favVo);
    }

    @RequestMapping(value = "{artworkId}/favourite", method = RequestMethod.POST)
    public String favourite(HttpSession httpSession,
                                  @PathVariable("artworkId") long artworkId ) {
        long loginUserId = CommonUtil.getLoginUserId(httpSession);
        favouriteService.favourite(loginUserId, artworkId);
        return "redirect:/artwork/favourites";
    }

    @RequestMapping(value = "{artworkId}/favourite", method = RequestMethod.DELETE)
    public String unfavourite(HttpSession httpSession,
                                  @PathVariable("artworkId") long artworkId ) {
        long loginUserId = CommonUtil.getLoginUserId(httpSession);
        favouriteService.unfavourite(loginUserId, artworkId);
        return "redirect:/artwork/favourites";
    }

    @RequestMapping(value = "{artworkId}/favourite", method = RequestMethod.GET)
    public String getFavouritesDetails(@PathVariable("artworkId") long artworkId) {
        List<Sale> sales = saleService.getSales(Arrays.asList(artworkId));

        if(CollectionUtils.isEmpty(sales)) {
            return "redirect:/artwork/" + artworkId;
        }

        Sale sale = sales.get(0);
        return "redirect:/sale/" + sale.getSaleId();
    }
}
