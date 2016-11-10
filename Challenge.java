import beans.ListingBean;
import beans.MatchingBean;
import beans.ProductsBean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hamza on 10/31/2016.
 * Contact Hamza for problems
 */

public class Challenge {

    static int lowerRecall = 7;
    static int incorrectMatches = 5;

    public static void main(String args[]){
        try {
            //long sTime = System.currentTimeMillis();
            System.out.print("Processing. Please wait...\n");
            /*We collect the data from the listings.txt*/
	        ArrayList<ListingBean> listOfListingBeans = FileManipulations.readListingDataFromFile("data/listings.txt");
            /*We collect the data from products.txt*/
            System.out.print(".\n");
	        HashMap<String, ArrayList<ProductsBean>> resultFromProducts = FileManipulations.readProductsDataFromFile("data/products.txt");
	        /*We match products with the listings*/
            System.out.print(".\n");
            ArrayList<MatchingBean> result = Sorting.matchProductsAndListings(resultFromProducts,listOfListingBeans,lowerRecall);
	        /*We sort the result into the format required*/
            System.out.print(".\n");
            HashMap<String,ArrayList<ListingBean>> resultMap = Sorting.sortResultAccordingToFormat(result);
	        /*We write the data to file*/
            System.out.print(".\n");
            FileManipulations.writeResultToFile(resultMap,"data/results.txt");
            System.out.println("Sorting done. Please find the results in data folder :)");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
