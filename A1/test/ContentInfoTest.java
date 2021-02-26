import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This is for Assignment 1 Test - ContentInfo
 *
 * @author Xue Wu
 * @since 2021-01-28
 */
public class ContentInfoTest {

    /**
     * Test method for {@link ContentInfo#ContentInfo(URL)}.
     */
    @Test
    public void testConstructor0() {
        try {
            new ContentInfo(new URL(null));
        } catch (MalformedURLException e) {
            System.out.println("Correct catch");
        }
    }

    /**
     * Test method for {@link ContentInfo#ContentInfo(URL)}.
     * @throws MalformedURLException if invalid url string
     */
    @Test
    public void testConstructor1() throws MalformedURLException {
        URL testUrl = new URL("https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/");
        try {
            new ContentInfo(testUrl);
        } catch (NullPointerException e) {
            Assert.fail("Catch NullPointerException");
        }
    }

    /**
     * Test method for {@link ContentInfo#ContentInfo(URL)}.
     * @throws MalformedURLException if invalid url string
     */
    @Test
    public void testConstructor2() throws MalformedURLException {
        URL testUrl = new URL("https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/");
        try {
            new ContentInfo(testUrl);
        } catch (NullPointerException e) {
            System.out.println("Correct catch");
        }
    }

    /**
     * Test method for {@link ContentInfo#ContentInfo(String)}.
     */
    @Test
    public void testConstructor3() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/";
        try {
            new ContentInfo(testString);
        } catch (MalformedURLException e) {
            fail("Catch MalformedURLException");
        }

    }

    /**
     * Test method for {@link ContentInfo#ContentInfo(String)}.
     */
    @Test
    public void testConstructor4() {
        String testString = "dfadaf.url";
        try {
            new ContentInfo(testString);
        } catch (MalformedURLException e) {
            System.out.println("Correct catch");
        }
    }

    /**
     * Test method for {@link ContentInfo#ContentInfo(String)}.
     */
    @Test
    public void testConstructor5() {
        try {
            new ContentInfo(((String) null));
        } catch (MalformedURLException e) {
            System.out.println("Correct catch");
        }
    }

    /**
     * Test method for {@link ContentInfo#getContentLength()}.
     * @throws MalformedURLException if invalid url string
     */
    @Test
    public void testGetContentLength1() throws MalformedURLException {
        URL testUrl = new URL("https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.pdf");
        try {
            ContentInfo contentInfo = new ContentInfo(testUrl);
            assertEquals(12811, contentInfo.getContentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getContentLength()}.
     * @throws MalformedURLException if invalid url string
     */
    @Test
    public void testGetContentLength2() throws MalformedURLException {
        URL testUrl = new URL("https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.txt");
        try {
            ContentInfo contentInfo = new ContentInfo(testUrl);
            assertEquals( 649, contentInfo.getContentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getContentLength()}.
     * @throws MalformedURLException if invalid url string
     */
    @Test
    public void testGetContentLength3() throws MalformedURLException {
        URL testUrl = new URL("https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/500x200.png");
        try {
            ContentInfo contentInfo = new ContentInfo(testUrl);
            assertEquals(68643, contentInfo.getContentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getContentType()}.
     */
    @Test
    public void testGetContentType1() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/500x200.png";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertEquals("image/png", contentInfo.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getContentType()}.
     */
    @Test
    public void testGetContentType2() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.txt";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertEquals("text/plain; charset=UTF-8", contentInfo.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getContentType()}.
     */
    @Test
    public void testGetContentType3() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.pdf";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertEquals("application/pdf", contentInfo.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getImageSize()}.
     */
    @Test
    public void testGetImageSize1() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/500x200.png";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertEquals(new Dimension(500, 200), contentInfo.getImageSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getImageSize()}.
     */
    @Test
    public void testGetImageSize2() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.pdf";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            contentInfo.getImageSize();
        } catch (IllegalStateException | MalformedURLException e) {
            System.out.println("Correct Catch");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getLastModified()}.
     */
    @Test
    public void testGetLastModified1() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.pdf";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertEquals("2021", new SimpleDateFormat("YYYY").format(contentInfo.getLastModified()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getLastModified()}.
     */
    @Test
    public void testGetLastModified2() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.txt";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertEquals("2021", new SimpleDateFormat("YYYY").format(contentInfo.getLastModified()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getLastModified()}.
     */
    @Test
    public void testGetLastModified3() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/500x200.png";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertEquals("2021", new SimpleDateFormat("YYYY").format(contentInfo.getLastModified()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getLineCount()}.
     */
    @Test
    public void testGetLineCount1() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.txt";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertEquals(12, contentInfo.getLineCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getLineCount()}.
     */
    @Test
    public void testGetLineCount2() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/500x200.png";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            contentInfo.getLineCount();
        } catch (IllegalStateException | MalformedURLException e) {
            System.out.println("Correct Catch");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Test method for {@link ContentInfo#getLocation()}.
     */
    @Test
    public void testGetLocation1() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.txt";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertEquals("https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.txt", contentInfo.getLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getLocation()}.
     */
    @Test
    public void testGetLocation2() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/500x200.png";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertEquals("https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/500x200.png", contentInfo.getLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#getLocation()}.
     */
    @Test
    public void testGetLocation3() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.pdf";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertEquals("https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.pdf", contentInfo.getLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#isAvailable()}.
     */
    @Test
    public void testIsAvailable1() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.pdf";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            contentInfo.isAvailable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#isAvailable()}.
     */
    @Test
    public void testIsAvailable2() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/500x200.png";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            contentInfo.isAvailable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#isAvailable()}.
     */
    @Test
    public void testIsAvailable3() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.txt";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            contentInfo.isAvailable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#isAvailable()}.
     */
    @Test
    public void testIsAvailable4() {
        String testString = "https://test-not-available.txt";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertFalse(contentInfo.isAvailable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#isImage()}.
     */
    @Test
    public void testIsImage1() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/500x200.png";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            contentInfo.isImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#isImage()}.
     */
    @Test
    public void testIsImage2() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.pdf";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertFalse(contentInfo.isImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#isText()}.
     */
    @Test
    public void testIsText1() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.pdf";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            assertFalse(contentInfo.isText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link ContentInfo#isText()}.
     */
    @Test
    public void testIsText2() {
        String testString = "https://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2021/Spring/resources/assignment-1/12lines.txt";
        try {
            ContentInfo contentInfo = new ContentInfo(testString);
            contentInfo.isText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}