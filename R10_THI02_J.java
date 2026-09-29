// Rule 10. Thread APIs (THI)
// THI02-J. Notify all waiting threads rather than a single thread

public final class ProcessStep implements Runnable {
  private static final Object lock = new Object();
  private static int time = 0;
  private final int step; // Perform operations when field time 
                          // reaches this value
  public ProcessStep(int step) {
    this.step = step;
  }

  @Override public void run() {
    try {
      synchronized (lock) {
        while (time != step) {
          lock.wait();
        }
  
        // Perform operations
  
        time++;
        lock.notifyAll(); // Use notifyAll() instead of notify()
      }
    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt(); // Reset interrupted status
    }
  }

}
