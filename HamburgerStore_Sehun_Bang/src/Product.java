public class Product implements ProductFormat{

    private int id;
    private String name;
    private float price=0;

    private  int count =0;


    private String descriptions;
    /// fucntions
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name=name;
    }
    public float getprice() {
        return price;
    }
    public void setprice(float price) {
        this.price = price;
    }
    public void setDescriptions(String s){
        this.descriptions = s;
    }
    public String getDescriptions(){
        return this.descriptions;
    }
    public void AddCount(){
        count++;
    }
    public void DivCount(){
        count/=2;
    }
    public int getCount() {
        return count;
    }
    public void size_upgrade(){ price = price+2;}
public Product(){

}


}
