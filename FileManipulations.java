import beans.ListingBean;
import beans.ProductsBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Hamza on 11/8/2016.
 * Contact Hamza for problems
 */
public class FileManipulations {
    public static ArrayList<ListingBean> readListingDataFromFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        ArrayList<ListingBean> listingBeanArrayList = new ArrayList<>();
        JsonParser parser = new JsonParser();
        String line;
        while ((line = br.readLine()) != null) {
            JsonObject object = parser.parse(line).getAsJsonObject();
            ListingBean bean = new ListingBean();
            bean.setCurrency((object.get("currency") == null ? "" : object.get("currency").getAsString()));
            bean.setManufacturer((object.get("manufacturer") == null ? "" : object.get("manufacturer").getAsString()));
            bean.setPrice((object.get("price") == null ? "" : object.get("price").getAsString()));
            bean.setTitle((object.get("title") == null ? "" : object.get("title").getAsString()));
            listingBeanArrayList.add(bean);
        }
        return listingBeanArrayList;
    }

    public static HashMap<String, ArrayList<ProductsBean>> readProductsDataFromFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        ArrayList<ProductsBean> listingBeanArrayList = new ArrayList<>();
        HashMap<String, ArrayList<ProductsBean>> mapOfProducts = new HashMap<>();
        JsonParser parser = new JsonParser();
        String line;


        while ((line = br.readLine()) != null) {
            JsonObject object = parser.parse(line).getAsJsonObject();
            ProductsBean bean = new ProductsBean();
            String manufacturer = (object.get("manufacturer") == null ? "" : object.get("manufacturer").getAsString());
            bean.setManufacturer(manufacturer);
            if (!mapOfProducts.containsKey(manufacturer)) {
                mapOfProducts.put(manufacturer, new ArrayList<>());
            }

            bean.setProduct_name((object.get("product_name") == null ? "" : object.get("product_name").getAsString()));
            bean.setAnnounced_date((object.get("announced-date") == null ? "" : object.get("announced-date").getAsString()));
            bean.setModel((object.get("model") == null ? "" : object.get("model").getAsString()));
            bean.setFamily((object.get("family") == null ? "" : object.get("family").getAsString()));
            mapOfProducts.get(manufacturer).add(bean);
        }

        HashMap<Object, Object> result = new HashMap<>();
        result.put("listOfProductBeans", listingBeanArrayList);
        result.put("listOfManufacturer", mapOfProducts);

        return mapOfProducts;

    }

    static void writeResultToFile(HashMap<String, ArrayList<ListingBean>> result, String path) {

        try {
            Writer writer = new FileWriter(path);
            Gson gson = new GsonBuilder().create();
            for(String key: result.keySet()){
                HashMap<String,Object> temp = new LinkedHashMap<>();
                temp.put("product_name",key);
                temp.put("listings",result.get(key));
                gson.toJson(temp, writer);
                writer.write("\n");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
