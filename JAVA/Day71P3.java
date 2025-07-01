/*
You are building an E-Commerce Product Insights Engine for a marketplace like 
Amazon or Flipkart. The platform stores information about various products, 
their pricing history, and user ratings.

Your job is to:

    1. Accept data for multiple products.
    
    2. Each product has:
        🎯 Multiple price entries (date + price)
        🎯 Multiple ratings (user + stars out of 5)
        
    3. Calculate:
        🎯 Average price of the product
        🎯 Price volatility score: Standard deviation of prices
        🎯 Average rating

    4. Classify products into Insight Tiers:
        🟢 Stable & Loved: Volatility < 100 and Rating ≥ 4.0
        🟡 Unstable but Popular: Volatility ≥ 100 and Rating ≥ 4.0
        🔴 Unstable & Poorly Rated: Volatility ≥ 100 and Rating < 4.0
        ⚪ Stable but Low-Rated: Volatility < 100 and Rating < 4.0
        
Sample Input:
-------------
2               // Number of products
EchoDot         // ProductName
3               // Number of price entries
2024-06-01 3499 // priceDate priceAmount
2024-06-10 3299
2024-06-15 3599
2               // Number of ratings
Alice 5         // userName stars
Bob 4
OldTV           // ProductName
4               // Number of price entries
2024-05-01 9999 // priceDate priceAmount
2024-05-10 10999
2024-05-15 11999
2024-05-20 8999
3               // Number of ratings
Charlie 2       // userName stars
Diana 3
Eve 1

Sample Output:
--------------
Product: EchoDot, AvgPrice: 3465.7, Volatility: 124.7, AvgRating: 4.5, Tier: Unstable but Popular
Product: OldTV, AvgPrice: 10499.0, Volatility: 1118.0, AvgRating: 2.0, Tier: Unstable & Poorly Rated

 
*/

import java.util.*;

public class Day71P3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            int m = Integer.parseInt(sc.nextLine());

            List<PriceEntry> prices = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                String[] parts = sc.nextLine().split(" ");
                prices.add(new PriceEntry(parts[0], Double.parseDouble(parts[1])));
            }

            int k = Integer.parseInt(sc.nextLine());
            List<Rating> ratings = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                String[] parts = sc.nextLine().split(" ");
                ratings.add(new Rating(parts[0], Integer.parseInt(parts[1])));
            }

            products.add(new Product(name, prices, ratings));
        }

        InsightEngine engine = new InsightEngineImpl();
        System.out.println("=== Product Insights Summary ===");
        for (Product p : products) {
            ProductInsight insight = engine.analyze(p);
            System.out.println(insight);
        }
    }
}

// TODO: Complete this POJO
class PriceEntry {
    // String date; double amount
    private String date;
    private double amount;
    PriceEntry(String date, double amount){
        setDate(date);
        setAmount(amount);
    }
    
    void setDate(String date){
        this.date = date;
    }
    
    void setAmount(double amount){
        this.amount = amount;
    }
    
    String getDate(){
        return this.date;
    }
    
    double getAmount(){
        return this.amount;
    }
}

// TODO: Complete this POJO
class Rating {
    // String userName; int stars
    private String userName;
    private int stars;
    
    Rating(String userName, int stars){
        setUserName(userName);
        setStars(stars);
    }
    
    void setUserName(String userName){
        this.userName = userName;
    }
    
    void setStars(int stars){
        this.stars = stars;
    }
    
    String getUserName(){
        return this.userName;
    }
    
    int getStars(){
        return this.stars;
    }
}

// TODO: Complete this POJO
class Product {
    // String name; List<PriceEntry>; List<Rating>
    String name;
    List<PriceEntry> list1;
    List<Rating> list2;
    
    Product(String name, List<PriceEntry> list1, List<Rating> list2){
        this.name = name;
        this.list1 = list1;
        this.list2 = list2;
    }
}

// TODO: Complete this POJO
class ProductInsight {
    // Product; double avgPrice; double volatility; double avgRating; String insightTier
    // Override toString() for output
    Product p;
    double avgPrice;
    double volatility;
    double avgRating;
    String insightTier;
    
    ProductInsight(Product p, double avgPrice, double volatility, double avgRating, String insightTier){
        this.p = p;
        this.avgPrice = avgPrice;
        this.volatility = volatility;
        this.avgRating = avgRating;
        this.insightTier = insightTier;
    }
    
    public String toString(){
        return "Product: " + this.p.name + ", AvgPrice: " + String.format("%.1f",this.avgPrice) + ", Volatility: " + String.format("%.1f", this.volatility) + ", AvgRating: " + String.format("%.1f", this.avgRating) + ", Tier: " + this.insightTier;
    }
}

interface InsightEngine {
    ProductInsight analyze(Product p);
}

// TODO: Implement InsightEngineImpl using Math.pow and Math.sqrt for std deviation
class InsightEngineImpl implements InsightEngine {
    public ProductInsight analyze(Product p) {
        // Logic:
        List<PriceEntry> list1 = p.list1;
        List<Rating> list2 = p.list2;
        // - Calculate avgPrice
        double avgAmount = 0;
        for(PriceEntry pe : list1){
            avgAmount += pe.getAmount();
        }
        avgAmount = avgAmount/(double)list1.size();
        
        // - Calculate standard deviation
        double Variance = 0;
        for(PriceEntry pe : list1){
            Variance += Math.pow((pe.getAmount() - avgAmount), 2);
        }
        Variance = Variance/(double)list1.size();
        double sd = Math.sqrt(Variance);
        // - Calculate avgRating
        double rating = 0;
        for(Rating r : list2){
            rating += (double)r.getStars();
        }
        rating = rating / (double)list2.size();
        // - Tier assignment
        String tier = "";
        if(sd<100 && rating>=4) tier = "Stable & Loved";
        else if(sd>=100 && rating>=4) tier = "Unstable but Popular";
        else if(sd>=100 && rating<4) tier = "Unstable & Poorly Rated";
        else tier = "Stable but Low-Rated";
        return new ProductInsight(p, avgAmount, sd, rating, tier); // TODO
    }
}


