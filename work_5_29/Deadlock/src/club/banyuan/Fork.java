package club.banyuan;

public class Fork {
  private boolean status;

  public Fork() {
    this.status = false;
  }

  public void takeFork(){
    status = true;
  }
  public void putFork(){
    status = false;
  }
}
