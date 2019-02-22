import static org.junit.Assert.assertTrue

def outputFilename = "target/bricks.json";
def outputFile = new File(basedir, outputFilename);

assertTrue "File '" + outputFile.getAbsoluteFile() + "' does not exist!", outputFile.exists()
assertTrue "File '" + outputFile.getAbsoluteFile() + "' is not a file!", outputFile.isFile()
