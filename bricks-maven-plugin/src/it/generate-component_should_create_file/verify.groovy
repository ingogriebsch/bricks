import static org.junit.Assert.assertTrue

def outputFile = new File(basedir, "target/bricks.json");

assertTrue "File '" + outputFile.getAbsoluteFile() + "' does not exist!", outputFile.exists()
assertTrue "File '" + outputFile.getAbsoluteFile() + "' is not a file!", outputFile.isFile()