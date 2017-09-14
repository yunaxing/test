package com.qkxmall.mall.nets;

import android.content.Context;

import com.qkxmall.mall.beans.Category;
import com.qkxmall.mall.beans.ChildCategory;
import com.qkxmall.mall.beans.ChildItem;
import com.umeng.socialize.utils.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 2015/12/24.
 */
public class JD {
    Context context;

    public JD(Context context) {
        this.context = context;
    }

    public HashMap<String, Object> decode(String result, String type) throws JSONException {
        HashMap<String, Object> data = new HashMap<>();
        switch (type) {
            case API.ADVERTISEMENT: {
                JSONObject jsonObject = new JSONObject(result);
                data.put("total", jsonObject.getInt("total"));
                JSONArray jsonArray = jsonObject.getJSONArray("rows");
                List<HashMap<String, Object>> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    HashMap<String, Object> map = new HashMap<>();
                    JSONObject object = jsonArray.getJSONObject(i);
                    map.put("id", object.getString("id"));
                    map.put("link", object.getString("link"));
                    map.put("content", object.getString("content"));
                    list.add(map);
                }
                data.put("rows", list);
                return data;
            }
            case API.HUI_PRODUCTS_LIST: {
                JSONObject jsonObject = new JSONObject(result);
                data.put("total", jsonObject.get("total"));
                JSONArray jsonArray = jsonObject.getJSONArray("rows");
                data.put("is_rect",jsonObject.getInt("is_rect"));
                List<HashMap<String, Object>> rows = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    HashMap<String, Object> item = new HashMap<>();
                    item.put("id", object.get("id"));
                    item.put("name", object.get("name"));
                    item.put("thumb", object.get("thumb"));
                    item.put("kaixindou", object.get("kaixindou"));
                    item.put("sales_number", object.get("sales_number"));
                    item.put("commnum", object.get("commnum"));
                    item.put("goods_number", object.get("goods_number"));
                    item.put("shop_price", object.get("shop_price"));
                    rows.add(item);
                }
                if (jsonObject.has("cate_image")) {
                    data.put("cate_image", jsonObject.get("cate_image"));
                }
                data.put("rows", rows);

                return data;
            }
            case API.HUI_PRODUCTS_DETAIL:{
                JSONArray jsonArray = new JSONArray(result);
                List<HashMap<String,Object>> JSON = new ArrayList<>();
                for (int i = 0 ; i< jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HashMap<String ,Object> hashMap = new HashMap<>();
                    hashMap.put("id",jsonObject.getString("id"));
                    hashMap.put("name", jsonObject.get("name"));
                    hashMap.put("brand_id", jsonObject.get("brand_id"));
                    hashMap.put("cat_ids", jsonObject.get("cat_ids"));
                    hashMap.put("keyword", jsonObject.get("keyword"));
                    hashMap.put("brief", jsonObject.get("brief"));
                    hashMap.put("give_integral", jsonObject.get("give_integral"));
                    hashMap.put("descript", jsonObject.get("descript"));
                    hashMap.put("thumb", jsonObject.get("thumb"));
                    hashMap.put("warn_number", jsonObject.get("warn_number"));
                    hashMap.put("weight", jsonObject.get("weight"));
                    hashMap.put("hits", jsonObject.get("hits"));
                    hashMap.put("favorite", jsonObject.get("favorite"));
                    JSONArray spec_array = jsonObject.getJSONArray("spec_array");
                    List<HashMap<String,Object>> spec_arrayList = new ArrayList<>();
                    for (int l = 0 ; l < spec_array.length() ;l++){
                        HashMap<String ,Object> item = new HashMap<>();
                        JSONObject object = spec_array.getJSONObject(l);
                        item.put("id", object.get("id"));
                        item.put("goods_id", object.get("goods_id"));
                        item.put("products_sn", object.get("products_sn"));
                        item.put("products_bus", object.get("products_bus"));
                        item.put("products_kaixindou", object.get("products_kaixindou"));
                        item.put("products_barcode", object.get("products_barcode"));
                        item.put("spec_array", object.get("spec_array"));
                        item.put("shop_price", object.get("shop_price"));
                        item.put("market_price", object.get("market_price"));
                        item.put("cost_price", object.get("cost_price"));
                        item.put("min_shop_price", object.get("min_shop_price"));
                        item.put("max_shop_price", object.get("max_shop_price"));
                        item.put("goods_number", object.get("goods_number"));
                        spec_arrayList.add(item);
                    }
                    hashMap.put("spec_array", spec_arrayList);
                    hashMap.put("list_img", jsonObject.get("list_img"));
                    hashMap.put("status", jsonObject.get("status"));
                    hashMap.put("status_exy", jsonObject.get("status_exy"));
                    hashMap.put("model", jsonObject.get("model"));
                    hashMap.put("sales_number", jsonObject.get("sales_number"));
                    hashMap.put("sort", jsonObject.get("sort"));
                    hashMap.put("min_shop_price", jsonObject.get("min_shop_price"));
                    hashMap.put("max_shop_price", jsonObject.get("max_shop_price"));
                    hashMap.put("shop_price", jsonObject.get("shop_price"));
                    hashMap.put("prom_id", jsonObject.get("prom_id"));
                    hashMap.put("prom_type", jsonObject.get("prom_type"));
                    hashMap.put("small_pics", jsonObject.get("small_pics"));
                    hashMap.put("kaixindou", jsonObject.get("kaixindou"));
                    hashMap.put("commnum", jsonObject.get("commnum"));
                    hashMap.put("param", jsonObject.get("param"));
                    hashMap.put("fahuodi", jsonObject.get("fahuodi"));
                    JSON.add(hashMap);
                }
                data.put("JSON",JSON);
                return data;
            }
            case API.BRAND:{
                JSONArray jsonArray = new JSONArray(result);
                List<HashMap<String ,Object>> brand = new ArrayList<>();
                for (int i = 0 ; i< jsonArray.length() ;i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HashMap<String ,Object> map = new HashMap<>();
                    map.put("id",jsonObject.getString("id"));
                    map.put("name",jsonObject.getString("name"));
                    map.put("logo",jsonObject.getString("logo"));
                    map.put("descript",jsonObject.getString("descript"));
                    map.put("url",jsonObject.getString("url"));
                    map.put("status",jsonObject.getString("status"));
                    map.put("sort",jsonObject.getString("sort"));
                    map.put("pushstatus",jsonObject.getString("pushstatus"));
                    map.put("isrecommend",jsonObject.getString("isrecommend"));
                    brand.add(map);
                }
                data.put("brand",brand);
                return data;
            }
            case API.ALL_CATEGORY:{
                JSONArray jsonArray = new JSONArray(result);
                List<Category> JSON = new ArrayList<>();
                for (int i = 0 ; i< jsonArray.length() ;i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Category map = new Category();
                    map.setId(jsonObject.getString("id"));
                    map.setName(jsonObject.getString("name"));
                    if(jsonObject.has("children")){
                        JSONArray childArray = new JSONArray(jsonObject.getString("children"));


                        ArrayList<ChildCategory> chidcategorys = new ArrayList<>();
                        Log.v("allcate","childArray333666=="+map.getName());
                        for(int j = 0; j<childArray.length(); j++){
                            ChildCategory childCategory = new ChildCategory();
                            JSONObject childObject = childArray.getJSONObject(j);
                            childCategory.setId(childObject.getString("id"));
                            childCategory.setName(childObject.getString("name"));
                            childCategory.setThumb_image(childObject.getString("thumb_image"));
                            Log.v("allcate","childArray333=="+childCategory.getName());
                            if(childObject.has("children")){
                                JSONArray itemArray = new JSONArray(childObject.getString("children"));
                                List<ChildItem> chiditems = new ArrayList<>();
                                for(int m = 0; m<itemArray.length(); m++){
                                    JSONObject itemObject = itemArray.getJSONObject(m);
                                    ChildItem item = new ChildItem();
                                    item.setId(itemObject.getString("id"));
                                    item.setName(itemObject.getString("name"));
                                    item.setThumb_image(itemObject.getString("thumb_image"));
                                    chiditems.add(item);
                                    Log.v("allcate","childArray333888=="+item.getName());
                                }
                                childCategory.setChilditems(chiditems);
                                chidcategorys.add(childCategory);
                            }
//                            Log.v("allcate","chidcategorys=="+chidcategorys.get(j).getName()+"******************j=="+j);
                        }
                        map.setChidCategorys(chidcategorys);
                    }

                    JSON.add(map);
                }
                data.put("category",JSON);
            }
            default: {
                return data;
            }
        }
    }


}
