package club.banyuan;

public abstract class Cake {

  protected int id;
  protected double price;

  public Cake(int n, double r) {
    id = n;
    price = r;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public abstract double calcPrice();

  public String toString() {
    return id + "\t" + price;
  }
}