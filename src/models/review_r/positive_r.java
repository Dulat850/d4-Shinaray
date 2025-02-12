package models.review_r;

public class positive_r extends Review {
    private Boolean rating = true;

    public positive_r() {
        super();
    }

    public Boolean getr() {
        return rating;
    }

    public String toString(){
        return "Rating: " + rating;
    }
}
