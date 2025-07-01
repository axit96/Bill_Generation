package com.solution;
import com.inputEntity.Model;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Path.of("com/data/data.json")));
        JSONArray js = new JSONArray(jsonContent);
        calCulateTotal(js);
    }

    public static void calCulateTotal(JSONArray js){
        List<String> category = Arrays.asList("book","food");
        List<Model> item_data = new ArrayList<>();
        js.forEach(jsObj -> {
            System.out.println("\nOrder Number: "+new JSONObject(jsObj.toString()).get("order_no"));
            JSONArray order_items = (JSONArray) new JSONObject(jsObj.toString()).get("order_items");
            order_items.forEach(item -> {
                JSONObject data = new JSONObject(item.toString());
                item_data.add(new Model(
                        data.getBoolean("isImported"),
                        data.getString("item_category"),
                        data.getInt("item_count"),
                        data.getString("item_name"),
                        data.getDouble("item_value"),
                        0d));
            });


            item_data.forEach(data -> {
                double original_value = data.getItem_value();
                if(data.isImported()) {
                    data.setTotal_tax(roundToNearestFiveCents(original_value*0.05));
                }
                if(!(data.getItem_category().equals("food") || data.getItem_category().equals("book") || data.getItem_category().equals("medical"))){
                    data.setTotal_tax(data.getTotal_tax() + roundToNearestFiveCents(original_value*0.1));
                }
                data.setItem_value(Double.parseDouble(new DecimalFormat("#.##").format(original_value+data.getTotal_tax())));
            });


            item_data.forEach(c-> {
                System.out.println(String.format("%s %s %s",c.getItem_count(),c.getItem_name(),c.getItem_value()*c.getItem_count()));
            });


            System.out.println(String.format("%s %s","Sales Taxes",roundToNearestFiveCents(item_data.stream().mapToDouble(c -> c.getTotal_tax()).sum())));
            System.out.println(String.format("%s %s","Total",new DecimalFormat("#.##").format(item_data.stream().mapToDouble(c -> c.getItem_value()*c.getItem_count()).sum())));
            item_data.clear();
        });
    }

    public static double roundToNearestFiveCents(double value) {
        if (value < 0) {
            return -roundToNearestFiveCents(-value); // Apply logic to positive, then negate
        }
        double inNickels = value * 100.0 / 5.0;
        double roundedNickelsCeiling = Math.ceil(inNickels);
        double result = roundedNickelsCeiling * 5.0 / 100.0;
        return new BigDecimal(String.valueOf(result)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
