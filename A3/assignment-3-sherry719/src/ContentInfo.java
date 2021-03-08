import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Updated for Assignment 3 - ContentInfo - Audio
 *
 * @author Xue Wu
 * @since 2021-02-25
 */

public class ContentInfo {
    /** Instance string value */
    URL url;

    /**
     * Create an instance with the content URL.
     * @param url an url, the content location
     * @throws NullPointerException if url is null
     */
    public ContentInfo(URL url) {
        if (url == null) {
            throw new NullPointerException();
        }
        this.url = url;
    }

    /**
     * Create an instance with a string representing the content URL.
     * @param s a string, the content location
     * @throws MalformedURLException if invalid url string
     */
    public ContentInfo(String s) throws MalformedURLException {
        this.url = new URL(s);
    }


    /**
     * Return a URLConnection for the instance URL.
     * @return a URLConnection for the instance URL
     * @throws IOException if the content is not available
     */
    private URLConnection getConnection() throws IOException {
        URLConnection urlConnection = url.openConnection();
        //If the type of URLConnection returned by URL.connect() is HttpURLConnection,
        // special handling is required for HTTP/HTTPS protocols
        if (urlConnection instanceof HttpURLConnection){
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                return httpURLConnection;
            }else {
                throw new IOException();
            }
        }else {
            //For other protocols, the URLConnection can be returned.
            return urlConnection;
        }
    }

    /**
     * Get the length of this content in bytes.
     * @return the length of this content in bytes
     * @throws IOException if content unavailable
     */
    public int getContentLength() throws IOException {
        URLConnection urlConnection = getConnection();
        return urlConnection.getContentLength();
    }


    /**
     * Gets the content type for this content (type/subtype).
     * @return the content type for this content
     * @throws IOException if content unavailable
     */
    public String getContentType() throws IOException {
        URLConnection urlConnection = getConnection();
        return urlConnection.getContentType();
    }

    /**
     * Gets the dimension for image content.
     * @return the dimension for image content
     * @throws IllegalStateException if not image content
     * @throws IOException if content unavailable
     */
    public Dimension getImageSize() throws IllegalStateException, IOException {
        if (!isImage()) {
            throw new IllegalStateException();
        }
        URLConnection urlConnection = getConnection();
        InputStream in = urlConnection.getInputStream();
        BufferedImage image = ImageIO.read(in);
        int width = image.getWidth();
        int height = image.getHeight();
        return new Dimension(width, height);
    }


    /**
     * Gets the date that this content was last modified.
     * @return the date that this content was last modified
     * @throws IOException if content unavailable
     */
    public Date getLastModified() throws IOException {
        URLConnection urlConnection = getConnection();
        return new Date(urlConnection.getLastModified());
    }

    /**
     * Gets the line count for text content.
     * @return the line count for text content
     * @throws IllegalStateException if not text content
     * @throws IOException if content unavailable
     */
    public int getLineCount() throws IllegalStateException, IOException {
        if (!isText()) {
            throw new IllegalStateException();
        }
        URLConnection urlConnection = getConnection();
        int lineCount = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        while (bufferedReader.readLine() != null) {
            lineCount ++;
        }
        bufferedReader.close();
        return lineCount;
    }

    /**
     * Get the location for this content.
     * @return the location for this content
     */
    public String getLocation() {
        return url.toString();
    }

    /**
     * Determines whether the content is available.
     * @return true if the content is available, otherwise false
     */
    public boolean isAvailable() {
        try {
            URLConnection urlConnection = getConnection();
            urlConnection.connect();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    /**
     * Determines whether the content is an image.
     * @return true if the content is an image, otherwise false
     * @throws IOException if content unavailable
     */
    public boolean isImage() throws IOException {
        URLConnection urlConnection = getConnection();
        return urlConnection.getHeaderField("Content-type").startsWith("image/");
    }

    /**
     * Determines whether the content is text.
     * @return true if the content is text, otherwise false
     * @throws IOException if content unavailable
     */
    public boolean isText() throws IOException {
        URLConnection urlConnection = getConnection();
        return urlConnection.getHeaderField("Content-Type").startsWith("text/");
    }


    /**
     * Return true if the source is audio, else false.
     * @return true if the source is audio, else false
     * @throws IOException if the given content is unavailable
     */
    public boolean isAudio() throws IOException {
        URLConnection urlConnection = getConnection();
        String contentType = urlConnection.getHeaderField("Content-Type");
        return contentType.startsWith("audio/");

    }

    /**
     * Returns the duration of an audio file in seconds.
     * @return the duration of an audio file in seconds
     * @throws IOException if the audio file is not supported
     * @throws UnsupportedAudioFileException if the audio file is not supported
     */
    public double getAudioDuration() throws IOException, UnsupportedAudioFileException {
        if (isAudio()) {
            //get an AudioInputStream from the AudioSystem
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.url);
            //Get the AudioFormat from the AudioInputStream
            AudioFormat audioformat = audioInputStream.getFormat();

            int channels = audioformat.getChannels();
            int frameSize = audioformat.getFrameSize() * 2;
            float frameRate = audioformat.getFrameRate();
            int sampleSize = audioformat.getSampleSizeInBits() * 2;
            float sampleRate = audioformat.getSampleRate();

            if (audioformat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                audioformat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, sampleSize, channels,
                        frameSize, frameRate, true);
                audioInputStream = AudioSystem.getAudioInputStream(audioformat, audioInputStream);
            }

            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat(),
                    (int) (audioInputStream.getFrameLength() * audioformat.getFrameSize()));

            Clip clip = null;
            try {
                clip = (Clip) AudioSystem.getLine(info);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }

            clip.close();
            double duration = clip.getBufferSize() / (clip.getFormat().getFrameSize() * clip.getFormat().getFrameRate());
            //returns the duration of an audio file in seconds.
            return Math.round(duration * 1000) / 1000.0d;

        } else {
            System.out.println("Not audio file");
            return 0;
        }
    }

}
