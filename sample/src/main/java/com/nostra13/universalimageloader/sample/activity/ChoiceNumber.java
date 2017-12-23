package com.nostra13.universalimageloader.sample.activity;

import com.nostra13.universalimageloader.sample.DataBase.AllProductsActivity;
import com.nostra13.universalimageloader.sample.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abdullah on 7/14/16.
 */
public class ChoiceNumber {

    public static String[] IMAGES;
    public static String[] Names;
    public static String[] prices;

    //Desi
    public static String[] DesiIMAGES;
    public static String[] DesiNames;
    public static String[] Desiprices;

    //Indian
    public static String[] IndianIMAGES;
    public static String[] IndianNames;
    public static String[] Indianprices;

    //Chinese
    public static String[] ChineseIMAGES;
    public static String[] ChineseNames;
    public static String[] Chineseprices;

    //Italian
    public static String[] ItalianIMAGES;
    public static String[] ItalianNames;
    public static String[] Italianprices;

    private static final String TAG_NAME = "Name";
    private static final String TAG_PRICE = "Price";
    private static final String TAG_URL = "Url";

    private static ArrayList<String> nm = new ArrayList<>();
    private static ArrayList<String> prc = new ArrayList<>();
    private static ArrayList<String> url = new ArrayList<>();

    public int choice;
    public static  double inTotalBill;
    public ChoiceNumber()
    {
        choice=MainActivity.getChoice();
    }
    public static String[] getIMAGES()
    {
        return IMAGES;
    }

    public static String[] getNames() {
        return Names;
    }

    public static String[] getPrices() {
        return prices;
    }
    public static void loadDesi()
    {

        AllProductsActivity all = new AllProductsActivity();
        ArrayList results = new ArrayList();

        try {
            all.setTable("Desi");
            all.load();
            results = all.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < results.size(); i++) {
            HashMap hash = new HashMap();
            hash = (HashMap) results.get(i);
            nm.add((String) hash.get(TAG_NAME));
            prc.add((String) hash.get(TAG_PRICE));
            url.add((String) hash.get(TAG_URL));
        }
        DesiIMAGES= new String[url.size()];
        DesiIMAGES=url.toArray(DesiIMAGES);
        Desiprices= new String[prc.size()];
        Desiprices= prc.toArray(Desiprices);
        DesiNames= new String[nm.size()];
        DesiNames=nm.toArray(DesiNames);

        url.clear();
        prc.clear();
        nm.clear();
    }
    public static void loadIndian()
    {

        AllProductsActivity all = new AllProductsActivity();
        ArrayList results = new ArrayList();

        try {
            all.setTable("Indian");
            all.load();
            results = all.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < results.size(); i++) {
            HashMap hash = new HashMap();
            hash = (HashMap) results.get(i);
            nm.add((String) hash.get(TAG_NAME));
            prc.add((String) hash.get(TAG_PRICE));
            url.add((String) hash.get(TAG_URL));

        }
        IndianIMAGES= new String[url.size()];
        IndianIMAGES=url.toArray(IndianIMAGES);
        Indianprices= new String[prc.size()];
        Indianprices= prc.toArray(Indianprices);
        IndianNames= new String[nm.size()];
        IndianNames=nm.toArray(IndianNames);
        url.clear();
        prc.clear();
        nm.clear();
    }
    public static void loadChinese()
    {

        AllProductsActivity all = new AllProductsActivity();
        ArrayList results = new ArrayList();
        try {
            all.setTable("Chinese");
            all.load();
            results = all.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < results.size(); i++) {
            HashMap hash = new HashMap();
            hash = (HashMap) results.get(i);
            nm.add((String) hash.get(TAG_NAME));
            prc.add((String) hash.get(TAG_PRICE));
            url.add((String) hash.get(TAG_URL));
        }
        ChineseIMAGES= new String[url.size()];
        ChineseIMAGES=url.toArray(ChineseIMAGES);
        Chineseprices= new String[prc.size()];
        Chineseprices= prc.toArray(Chineseprices);
        ChineseNames= new String[nm.size()];
        ChineseNames=nm.toArray(ChineseNames);
        url.clear();
        prc.clear();
        nm.clear();
    }
    public void loadItalian()
    {

        AllProductsActivity all = new AllProductsActivity();
        ArrayList results = new ArrayList();
        try {
            all.setTable("Italian");
            all.load();
            results = all.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < results.size(); i++) {
            HashMap hash = new HashMap();
            hash = (HashMap) results.get(i);
            nm.add((String) hash.get(TAG_NAME));
            prc.add((String) hash.get(TAG_PRICE));
            url.add((String) hash.get(TAG_URL));
        }
        ItalianIMAGES= new String[url.size()];
        ItalianIMAGES=url.toArray(ItalianIMAGES);
        Italianprices= new String[prc.size()];
        Italianprices= prc.toArray(Italianprices);
        ItalianNames= new String[nm.size()];
        ItalianNames=nm.toArray(ItalianNames);
        url.clear();
        prc.clear();
        nm.clear();
    }
    //for AllProductsActivity Class
    public static void getImages()
    {
        ChoiceNumber ch = new ChoiceNumber();

        if (ch.choice == R.id.desi)
        {
            IMAGES=DesiIMAGES;
            prices=Desiprices;
            Names=DesiNames;
        } else if (ch.choice == R.id.indian)
        {
            IMAGES=IndianIMAGES;
            prices=Indianprices;
            Names=IndianNames;
        } else if (ch.choice == R.id.chinese)
        {
            IMAGES=ChineseIMAGES;
            prices=Chineseprices;
            Names=ChineseNames;

        } else if (ch.choice == R.id.italian)
        {
            IMAGES=ItalianIMAGES;
            prices=Italianprices;
            Names=ItalianNames;
        }
    }
    /*
    //for GetAllProduct Class
    public  static void  getImages()
    {
        url.clear();
        nm.clear();
        prc.clear();
        ChoiceNumber ch=new ChoiceNumber();
        GetAllProducts mysql = new GetAllProducts();

        if(ch.choice== R.id.desi) {
           // mysql.setTable("Desi");
            ArrayList results = new ArrayList();
            mysql.load();
            try {
                results = mysql.getList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < results.size(); i++) {
                GetAllProducts.MyObj obj = (GetAllProducts.MyObj) results.get(i);
                nm.add(obj.name);
                prc.add(obj.price);
                url.add(obj.url);
            }
        }

        else if (ch.choice== R.id.indian)
        {
            mysql.setTable("Indian");
            ArrayList results = new ArrayList();
            try {
                results = mysql.getList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < results.size(); i++) {
                GetAllProducts.MyObj obj = (GetAllProducts.MyObj) results.get(i);
                nm.add(obj.name);
                prc.add(obj.price);
                url.add(obj.url);
            }
        }
        else if (ch.choice == R.id.chinese)
        {
            mysql.setTable("Chinese");
            ArrayList results = new ArrayList();
            try {
                results = mysql.getList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < results.size(); i++) {
                GetAllProducts.MyObj obj = (GetAllProducts.MyObj) results.get(i);
                nm.add(obj.name);
                prc.add(obj.price);
                url.add(obj.url);

            }

        }
        else if (ch.choice == R.id.italian)
        {
            mysql.setTable("Italian");
            ArrayList results = new ArrayList();
            try {
                results = mysql.getList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < results.size(); i++) {
                GetAllProducts.MyObj obj = (GetAllProducts.MyObj) results.get(i);
                nm.add(obj.name);
                prc.add(obj.price);
                url.add(obj.url);

            }
        }*/
}

