import beans.ListingBean;
import beans.MatchingBean;
import beans.ProductsBean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hamza on 11/8/2016.
 * Contact Hamza for problems
 */
class Sorting {


    private static MatchingBean findMatchForTheListing(ListingBean listing, ArrayList<ProductsBean> listOfPossibleProducts, int tolerance){
        String listingTitle = listing.getTitle().toLowerCase();
        HashMap<String,Integer> productMatchRating = new HashMap<>();
        for(ProductsBean product: listOfPossibleProducts){
            String productName = product.getProduct_name();
            int matchFlagCount = 0;
            String[] matchArray = productName.split("_");
            for(String temp : matchArray){
                temp = temp.toLowerCase();
                if(listingTitle.contains(temp+" ") || listingTitle.contains(" "+temp+"\n") ||
                        listingTitle.contains(" "+temp+" ") && !temp.equals(""))
                    matchFlagCount++;
            }
            String productFamily = product.getFamily().toLowerCase();
            if(listingTitle.contains(productFamily+" ") || listingTitle.contains(" "+productFamily+"\n")
                    || listingTitle.contains(" "+productFamily+" ") && !productFamily.equals(""))
                matchFlagCount+=2;
            String productModel = product.getModel().toLowerCase();
            if(listingTitle.contains(productModel+" ") || listingTitle.contains(" "+productModel+"\n")
                    || listingTitle.contains(" "+productModel+" ") && !productModel.equals(""))
                matchFlagCount+=2;
            productMatchRating.put(productName,matchFlagCount);
        }
        MatchingBean bean = null;
        int finalCount = 0;
        for(String productName : productMatchRating.keySet()){
            int matchCount = productMatchRating.get(productName);
            if(matchCount > finalCount && matchCount >= tolerance) {
                bean = new MatchingBean();
                bean.setProductName(productName);
                bean.setMatchFlagCount(matchCount);
                bean.setListingBean(listing);
                finalCount = matchCount;
            }
        }

        return bean;
    }

    static ArrayList<MatchingBean> matchProductsAndListings(HashMap<String, ArrayList<ProductsBean>> mapOfManufactures, ArrayList<ListingBean> listOfListingBeans,int tolerance){
        ArrayList<MatchingBean> matchingBeanArrayList = new ArrayList<>();
        for(ListingBean listing: listOfListingBeans) {
            String listingManufacturer = listing.getManufacturer().toLowerCase();
            for (String manufacturer : mapOfManufactures.keySet()) {
                if(listingManufacturer.contains(manufacturer.toLowerCase())){
                    MatchingBean matchingBean = findMatchForTheListing(listing,mapOfManufactures.get(manufacturer),tolerance);
                    if (matchingBean != null)
                        matchingBeanArrayList.add(matchingBean);
                    break;
                }
            }
        }

        return matchingBeanArrayList;
    }

    static HashMap<String,ArrayList<ListingBean>> sortResultAccordingToFormat(ArrayList<MatchingBean> listOfMatchedBeans){
        HashMap<String,ArrayList<ListingBean>> resultMap = new HashMap<>();
        for(MatchingBean bean: listOfMatchedBeans){
            String productName = bean.getProductName();
            if(!resultMap.containsKey(productName)){
               resultMap.put(productName,new ArrayList<>());
            }
            resultMap.get(productName).add(bean.getListingBean());
        }
        return resultMap;
    }
}
