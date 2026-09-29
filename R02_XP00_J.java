// Rule 00. Input Validation and Data Sanitization (IDS)
// IDS03-J. Do not log unsanitized user input

public void deleteFile() {
  File someFile = new File("someFileName.txt");
  // Do something with someFile
  if (!someFile.delete()) {
    // Handle failure to delete the file
  }
}
