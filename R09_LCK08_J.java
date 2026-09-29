public final class Client {
  private final Lock lock = new ReentrantLock();

  public void doSomething(File file) {
    InputStream in = null;
    try {
      in = new FileInputStream(file);
      lock.lock();
      // Perform operations on the open file
    } catch (FileNotFoundException fnf) {
      // Forward to handler
    } finally {
      lock.unlock();
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          // Forward to handler
        }
      }
    }
  }
}
