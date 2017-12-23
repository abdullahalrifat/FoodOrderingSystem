package com.nostra13.universalimageloader.sample.DataBase;

import android.app.ProgressDialog;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllProductsActivity{


	// Progress Dialog
	private ProgressDialog pDialog;
	public static String ip = "theicthub.com";
	public static String Table = "Desi";
	public static String php = "get_Desi_Products";

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	public static ArrayList<HashMap<String, String>> productsList=new ArrayList<>();

	// url to get all products list
	private static String url_all_products = "http://" + ip + "/orderfood/" + php + ".php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static  String TAG_PRODUCTS = "products";
	private static final String TAG_PID = "id";
	private static final String TAG_NAME = "Name";
	private static final String TAG_PRICE = "Price";
	private static final String TAG_URL = "Url";

	// products JSONArray
	JSONArray products = null;
	public void setTable(String table)
	{
		Table = table;
		if (Table.equals("Desi")) {
			php = "get_Desi_Products";
			url_all_products = "http://" + ip + "/orderfood/" + php + ".php";
		} else if (Table.equals("Chinese")) {
			php = "get_Chinese_Products";
			url_all_products = "http://" + ip + "/orderfood/" + php + ".php";
		} else if (Table.equals("Indian")) {
			php = "get_Indian_Products";
			url_all_products = "http://" + ip + "/orderfood/" + php + ".php";
		} else if (Table.equals("Italian")) {
			php = "get_Italian_Products";
			url_all_products = "http://" + ip + "/orderfood/" + php + ".php";
		}
		TAG_PRODUCTS=table;
	}

	public void load()
	{
		productsList.clear();
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
				products = json.getJSONArray(TAG_PRODUCTS);

				// looping through All Products
				for (int i = 0; i < products.length(); i++) {
					JSONObject c = products.getJSONObject(i);

					// Storing each json item in variable
					String id = c.getString(TAG_PID);
					String name = c.getString(TAG_NAME);
					String price = c.getString(TAG_PRICE);
					String url=c.getString(TAG_URL);

					// creating new HashMap
					HashMap<String, String> map = new HashMap<String, String>();

					// adding each child node to HashMap key => value
					map.put(TAG_PID, id);
					map.put(TAG_NAME, name);
					map.put(TAG_PRICE, price);
					map.put(TAG_URL, url);

					// adding HashList to ArrayList
					productsList.add(map);
				}
			} else {

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public ArrayList getList() {
		return productsList;
	}
}