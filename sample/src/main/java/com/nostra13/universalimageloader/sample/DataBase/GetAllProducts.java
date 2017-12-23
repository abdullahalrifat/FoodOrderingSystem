package com.nostra13.universalimageloader.sample.DataBase;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetAllProducts {

    // Progress Dialog
    private ProgressDialog pDialog;
    public static String ip = "10.0.2.2";
    public static String Table = "Desi";
    public static String php = "get_Desi_Products";

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    // url to get all products list
    private static String url_all_products = "http://" + ip + "/android_connect/" + php + ".php";
    ArrayList<MyObj> results = new ArrayList<>();
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";
    private static final String TAG_PRICE = "price";
    private static final String TAG_URL = "url";

    // products JSONArray
    JSONArray products = null;


    public void load() {
        // Loading products in Background Thread
        new LoadAllProducts().execute();
    }

    public ArrayList getList() {
        return results;
    }
    public void setTable(String table)
    {
        Table = table;
        if (Table.equals("Desi")) {
            php = "get_Desi_Products";
            url_all_products = "http://" + ip + "/android_connect/" + php + ".php";
        } else if (Table.equals("Chinese")) {
            php = "get_Chinese_Products";
            url_all_products = "http://" + ip + "/android_connect/" + php + ".php";
        } else if (Table.equals("Indian")) {
            php = "get_Indian_Products";
            url_all_products = "http://" + ip + "/android_connect/" + php + ".php";
        } else if (Table.equals("Italian")) {
            php = "get_Italian_Products";
            url_all_products = "http://" + ip + "/android_connect/" + php + ".php";
        }
    }
    /**
     * Background Async Task to Load all product by making HTTP Request
     */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        /**
         * getting All products from url
         */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    JSONObject jsonObj = new JSONObject(String.valueOf(json));
                    products = jsonObj.getJSONArray(TAG_PRODUCTS);

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        MyObj obj = new MyObj();
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);
                        String price = c.getString(TAG_PRICE);
                        String url = c.getString(TAG_URL);


                        obj.id = Integer.parseInt(id);
                        obj.name = name;
                        obj.price = price;
                        obj.url = url;
                        results.add(obj);
                    }
                } else {
                    // no products found
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            Thread th = new Thread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    // Building Parameters
                }
            });
        }
    }
        public class MyObj {
            public int id = 0;
            public String name = "";
            public String price = "";
            public String url = "";
        }
}