public class Song 
{
    private String title;
    private String artist;
    private int durationSeconds;
    private int hypeRating;

    Song next;

    public Song(String title, String artist, int durationSeconds, int hypeRating) 
	{
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
        this.hypeRating = hypeRating;
        this.next = null;
    }

    public String getTitle() 
	{
        return title;
    }

    public String getArtist() 
	{
        return artist;
    }

    public int getDurationSeconds() 
	{
        return durationSeconds;
    }

    public int getHypeRating() 
	{
        return hypeRating;
    }


    public String toString() 
	{
        return String.format("%s by %s (%ds, hype %d)", title, artist, durationSeconds, hypeRating);
    }
}