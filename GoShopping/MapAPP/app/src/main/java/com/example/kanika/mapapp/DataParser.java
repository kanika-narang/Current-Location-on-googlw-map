package com.example.kanika.mapapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by navneet on 23/7/16.
 */
class offerdata{
    String Offer_title;
    String Offer_desc;
    offerdata(String title,String desc)
    {
        this.Offer_title=title;
        this.Offer_desc=desc;

    }
    offerdata(offerdata ob)
    {
        this.Offer_desc=ob.Offer_desc;
        this.Offer_title=ob.Offer_title;
    }
}
public class DataParser {


    HashMap offermale_egl ;
    String Sex;
    DataParser(String Sex)
    {
        this.Sex=Sex;
    }

    public void setoffers()
    {
        offermale_egl=new HashMap<String,offerdata>();
        offermale_egl.put("Jaipur Bandhani Fashions",new offerdata("Male Ethnic wear 50-80% off","Jaipuri kurta's available in all colors and sizes."));
        offermale_egl.put("JEALOUS 21",new offerdata("Jeans Buy1-Get1 Free","Offer Valid till 30th June Buy one pair of Jeans and get one pair Free. Good Quality Jeans."));
        offermale_egl.put("Malleswar Garments",new offerdata("Male suits at 20% off","Buy any Male suits at 20% off offer valid till 15th august."));
        offermale_egl.put("Krishni Designer Boutique",new offerdata("10% off on stitching","Get 10% off on the stitvhing charges on your first order off valid till 30th May "));
        offermale_egl.put("Keys Fashions Private Limited",new offerdata("Men's Shirts starting from 400 Rs.","Good quality Shirts available price starting from 400Rs. offer valid till the stocks last."));
        offermale_egl.put("clnch.com -Accelerate Shopping",new offerdata("Male Ethnic wear 50-80% off","Jaipuri kurta's available in all colors and sizes."));

    }
    public List<HashMap<String, String>> parse(String jsonData) {
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try {
            Log.d("Places", "parse");
            jsonObject = new JSONObject((String) jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            Log.d("Places", "parse error");
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }

    private List<HashMap<String, String>> getPlaces(JSONArray jsonArray) {
        int placesCount = jsonArray.length();
        List<HashMap<String, String>> placesList = new ArrayList<>();
        HashMap<String, String> placeMap = null;
        Log.d("Places", "getPlaces");

        for (int i = 0; i < placesCount; i++) {
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(i));
                placesList.add(placeMap);
                Log.d("Places", "Adding places");

            } catch (JSONException e) {
                Log.d("Places", "Error in Adding places");
                e.printStackTrace();
            }
        }
        return placesList;
    }

    private HashMap<String, String> getPlace(JSONObject googlePlaceJson) {
        setoffers();
        Log.d("get offers", "Got offers");
        HashMap<String, String> googlePlaceMap = new HashMap<String, String>();
        String placeName = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitude = "";
        String reference = "";
        String offerdesc="";
        String offertitle="";
        Log.d("getPlace", "Entered");

        try {
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
                Log.d("done", "done");
                if(Sex.equals("Male")) {
                    if (placeName.equals("Jaipur Bandhani Fashions")) {
                        offerdesc = "Jaipuri kurta's available in all colors and sizes.";
                        offertitle = "Male Ethnic wear 50-80% off";
                    } else if (placeName.equals("JEALOUS 21")) {
                        offerdesc = "Offer Valid till 30th June Buy one pair of Jeans and get one pair Free. Good Quality Jeans";
                        offertitle = "Jeans Buy1-Get1 Free";
                    } else if (placeName.equals("Malleswar Garments")) {
                        offerdesc = "Male suits at 20% off\",\"Buy any Male suits at 20% off offer valid till 15th august.";
                        offertitle = "Male suits at 20% off";
                    } else if (placeName.equals("Krishni Designer Boutique")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May ";
                        offertitle = "10% off on stitching";
                    } else if (placeName.equals("Kayes Fashions Private Limited")) {
                        offerdesc = "Jaipuri kurta's available in all colors and sizes.";
                        offertitle = "Men's Shirts starting from 400 Rs.";
                    } else if (placeName.equals("clnch.com - Accelerate Shopping")) {
                        offerdesc = "Good quality Shirts available price starting from 400Rs. offer valid till the stocks last.";
                        offertitle = "Male Ethnic wear 50-80% off";
                    } else if (placeName.equals("United Colors Of Benetton")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's wear at 50% off";
                    } else if (placeName.equals("Ladies & Children Wear")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off for childrens , Till the stocks last.. Hurry...";
                        offertitle = "Men's wear at 50% off";
                    } else if (placeName.equals("Shilaikaa-Online Fashion Stores")) {
                        offerdesc = " All Fashionable clothes on sale Buy one get one free....... , Till the stocks last.. Hurry...";
                        offertitle = "Buy-one-Get-one-Free";

                    } else if (placeName.equals("Rycla Enterprises")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's wear at 50% off";
                    } else if (placeName.equals("United Colors Of Benetton")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's wear at 50% off";
                    } else if (placeName.equals("Hung Shoes")) {
                        offerdesc = "shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's shoes at 50% off";
                    } else if (placeName.equals("BarBin's Rainbow Rapture")) {
                        offerdesc = "shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's shoes at 50% off";
                    } else if (placeName.equals("Weave & stich Boutique")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Men's suit stitching at 10% off";
                    } else if (placeName.equals("Monisha Fashion Centre")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Men's suit stitching at 10% off";
                    } else if (placeName.equals("Sun Glory Fashion ")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Men's suit stitching at 10% off";
                    } else if (placeName.equals("Prani Fashion")) {
                        offerdesc = "shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's shoes at 50% off";
                    } else if (placeName.equals("JOGMAYA TEXTILES")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Men's suit stitching at 10% off";
                    } else if (placeName.equals("Buckle")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Men's suit stitching at 10% off";
                    } else if (placeName.equals("Venus Collections")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Men's suit stitching at 10% off";
                    } else if (placeName.equals("Helmet shoppe")) {
                        offerdesc = "Helmets at 50 % off for childrens , Till the stocks last.. Hurry...";
                        offertitle = "Men's Helmet at 50% off";
                    } else if (placeName.equals("Biswasji Das School Uniform")) {
                        offerdesc = " All School Uniform clothes on sale Buy one get one free....... , Till the stocks last.. Hurry...";
                        offertitle = "Buy-one-Get-one-Free";

                    } else if (placeName.equals("Foreever New Apparels")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's wear at 50% off";
                    } else if (placeName.equals("United Colors Of Benetton")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's wear at 50% off";
                    } else if (placeName.equals("Soma shop ")) {
                        offerdesc = "shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's shoes at 50% off";
                    } else if (placeName.equals("Van Heusen")) {
                        offerdesc = "shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's shoes at 50% off";
                    } else if (placeName.equals("JACK & JONES")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Men's suit stitching at 10% off";
                    } else if (placeName.equals("Buckle")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Men's suit stitching at 10% off";
                    } else if (placeName.equals("Allen Solly")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's wear at 50% off";
                    } else if (placeName.equals("Wildcraft")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Men's suit stitching at 10% off";
                    } else if (placeName.equals("Eighteen Plus")) {
                        offerdesc = "Offer Valid till 30th June Buy one pair of Jeans and get one pair Free. Good Quality Jeans";
                        offertitle = "Jeans Buy1-Get1 Free";
                    } else if (placeName.equals("Van Heusen")) {
                        offerdesc = "Male suits at 20% off\",\"Buy any Male suits at 20% off offer valid till 15th august.";
                        offertitle = "Male suits at 20% off";
                    } else if (placeName.equals("ROYAL AVENUE")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May ";
                        offertitle = "10% off on stitching";
                    } else if (placeName.equals("Fab India clothes")) {
                        offerdesc = "Jaipuri kurta's available in all colors and sizes.";
                        offertitle = "Men's Shirts starting from 400 Rs.";
                    } else if (placeName.equals("Shree Kridhna Collection")) {
                        offerdesc = "Good quality Shirts available price starting from 400Rs. offer valid till the stocks last.";
                        offertitle = "Male Ethnic wear 50-80% off";
                    } else if (placeName.equals("The Kashmir Store")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Men's wear at 50% off";
                    } else if (placeName.equals("Ladies & Children Wear")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off for childrens , Till the stocks last.. Hurry...";
                        offertitle = "Men's wear at 50% off";
                    } else if (placeName.equals("Shilaikaa-Online Fashion Stores")) {
                        offerdesc = " All Fashionable clothes on sale Buy one get one free....... , Till the stocks last.. Hurry...";
                        offertitle = "Buy-one-Get-one-Free";

                    }
                }
                else
                {
                    if (placeName.equals("Jaipur Bandhani Fashions")) {
                        offerdesc = "Jaipuri kurta's available in all colors and sizes.";
                        offertitle = "Female Ethnic wear 50-80% off";
                    } else if (placeName.equals("JEALOUS 21")) {
                        offerdesc = "Offer Valid till 30th June Buy one pair of Jeans and get one pair Free. Good Quality Jeans";
                        offertitle = "Jeans Buy1-Get1 Free";
                    } else if (placeName.equals("Malleswar Garments")) {
                        offerdesc = "Female suits at 20% off\",\"Buy any Male suits at 20% off offer valid till 15th august.";
                        offertitle = "Female suits at 20% off";
                    } else if (placeName.equals("Krishni Designer Boutique")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May ";
                        offertitle = "10% off on stitching";
                    } else if (placeName.equals("Kayes Fashions Private Limited")) {
                        offerdesc = "Jaipuri kurta's available in all colors and sizes.";
                        offertitle = "Women's Shirts starting from 400 Rs.";
                    } else if (placeName.equals("clnch.com - Accelerate Shopping")) {
                        offerdesc = "Good quality Shirts available price starting from 400Rs. offer valid till the stocks last.";
                        offertitle = "Female Ethnic wear 50-80% off";
                    } else if (placeName.equals("United Colors Of Benetton")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women wear at 50% off";
                    } else if (placeName.equals("Ladies & Children Wear")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off for childrens , Till the stocks last.. Hurry...";
                        offertitle = "Women wear at 50% off";
                    } else if (placeName.equals("Shilaikaa-Online Fashion Stores")) {
                        offerdesc = " All Fashionable clothes on sale Buy one get one free....... , Till the stocks last.. Hurry...";
                        offertitle = "Buy-one-Get-one-Free";

                    } else if (placeName.equals("Rycla Enterprises")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women wear at 50% off";
                    } else if (placeName.equals("United Colors Of Benetton")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women wear at 50% off";
                    } else if (placeName.equals("Hung Shoes")) {
                        offerdesc = "shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women shoes at 50% off";
                    } else if (placeName.equals("BarBin's Rainbow Rapture")) {
                        offerdesc = "shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women shoes at 50% off";
                    } else if (placeName.equals("Weave & stich Boutique")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Women suit stitching at 10% off";
                    } else if (placeName.equals("Monisha Fashion Centre")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Women suit stitching at 10% off";
                    } else if (placeName.equals("Sun Glory Fashion ")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Women suit stitching at 10% off";
                    } else if (placeName.equals("Prani Fashion")) {
                        offerdesc = "shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women shoes at 50% off";
                    } else if (placeName.equals("JOGMAYA TEXTILES")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Women suit stitching at 10% off";
                    } else if (placeName.equals("Buckle")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Women suit stitching at 10% off";
                    } else if (placeName.equals("Venus Collections")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Women suit stitching at 10% off";
                    } else if (placeName.equals("Helmet shoppe")) {
                        offerdesc = "Helmets at 50 % off for childrens , Till the stocks last.. Hurry...";
                        offertitle = "Women Helmet at 50% off";
                    } else if (placeName.equals("Biswasji Das School Uniform")) {
                        offerdesc = " All School Uniform clothes on sale Buy one get one free....... , Till the stocks last.. Hurry...";
                        offertitle = "Buy-one-Get-one-Free";

                    } else if (placeName.equals("Foreever New Apparels")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women wear at 50% off";
                    } else if (placeName.equals("United Colors Of Benetton")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women wear at 50% off";
                    } else if (placeName.equals("Soma shop ")) {
                        offerdesc = "shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women shoes at 50% off";
                    } else if (placeName.equals("Van Heusen")) {
                        offerdesc = "shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women shoes at 50% off";
                    } else if (placeName.equals("JACK & JONES")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Men's suit stitching at 10% off";
                    } else if (placeName.equals("Buckle")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Women suit stitching at 10% off";
                    } else if (placeName.equals("Allen Solly")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women wear at 50% off";
                    } else if (placeName.equals("Wildcraft")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May";
                        offertitle = "Women suit stitching at 10% off";
                    } else if (placeName.equals("Eighteen Plus")) {
                        offerdesc = "Offer Valid till 30th June Buy one pair of Jeans and get one pair Free. Good Quality Jeans";
                        offertitle = "Jeans Buy1-Get1 Free";
                    } else if (placeName.equals("Van Heusen")) {
                        offerdesc = "Feale suits at 20% off\",\"Buy any Male suits at 20% off offer valid till 15th august.";
                        offertitle = "Feale suits at 20% off";
                    } else if (placeName.equals("ROYAL AVENUE")) {
                        offerdesc = "Get 10% off on the stitching charges on your first order off valid till 30th May ";
                        offertitle = "10% off on stitching";
                    } else if (placeName.equals("Fab India clothes")) {
                        offerdesc = "Jaipuri kurta's available in all colors and sizes.";
                        offertitle = "Women's Shirts starting from 400 Rs.";
                    } else if (placeName.equals("Shree Kridhna Collection")) {
                        offerdesc = "Good quality Shirts available price starting from 400Rs. offer valid till the stocks last.";
                        offertitle = "Women Ethnic wear 50-80% off";
                    } else if (placeName.equals("The Kashmir Store")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off , Till the stocks last.. Hurry...";
                        offertitle = "Women's wear at 50% off";
                    } else if (placeName.equals("Ladies & Children Wear")) {
                        offerdesc = "Shirts, pants, shoes all at 50 % off for childrens , Till the stocks last.. Hurry...";
                        offertitle = "Women's wear at 50% off";
                    } else if (placeName.equals("Shilaikaa-Online Fashion Stores")) {
                        offerdesc = " All Fashionable clothes on sale Buy one get one free....... , Till the stocks last.. Hurry...";
                        offertitle = "Buy-one-Get-one-Free";

                    }
                }
               /*offerdata of=new offerdata((offerdata) offermale_egl.get(placeName));
                Log.d("offerdata", of.Offer_desc);
                offerdesc=of.Offer_desc;
                offertitle=of.Offer_title;*/
                Log.d("gooooooooooo", "goooooo");
            }
            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity");
            }
            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = googlePlaceJson.getString("reference");

            googlePlaceMap.put("place_name", placeName);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", reference);
            googlePlaceMap.put("offerdesc", offerdesc);
            googlePlaceMap.put("offertitle", offertitle);
            Log.d("getPlace", "Putting Places");
        } catch (JSONException e) {
            Log.d("getPlace", "Error");
            e.printStackTrace();
        }
        return googlePlaceMap;
    }
}
