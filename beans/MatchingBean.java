package beans;

/**
 * Created by Hamza on 11/8/2016.
 * Contact Hamza for problems
 */
public class MatchingBean {
    private String productName;
    private ListingBean listingBean;
    int matchFlagCount;

    public MatchingBean() {
    }

    public MatchingBean(String productName, ListingBean listingBean) {
        this.productName = productName;
        this.listingBean = listingBean;
    }

    public int getMatchFlagCount() {
        return matchFlagCount;
    }

    public void setMatchFlagCount(int matchFlagCount) {
        this.matchFlagCount = matchFlagCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ListingBean getListingBean() {
        return listingBean;
    }

    public void setListingBean(ListingBean listingBean) {
        this.listingBean = listingBean;
    }
}
