package Book;



import Accounts.Admin;
import System.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Book {
    private String Book_ID;
    private String Book_Title;
    private String Author;
    private String publication_year;
    private boolean Status;
    private double price_buying;

    private double price_borrowing;
    private int stock;
    private String category;

    public ArrayList<Review> reviews;

    private String book_image; // new 3mlt setter and getter w 3dltha fy constructor w toString()

    private double rating;

    // i should delete price borrowing from constructor parameter and body , and in toString() and in reading file
    public Book(String name , String author , double price_buying ,int stock , String category , String publication_year, String book_image){
        this.Book_Title = name;
        this.Author = author;
        this.price_buying = price_buying;
        this.stock = stock;
        if (stock > 0)
            this.Status = true;
        else
            this.Status = false;
        this.category = category;
        this.publication_year = publication_year;

        reviews = new ArrayList<Review>();
        this.book_image = book_image;
        calcRating();


        do {
            this.Book_ID = getRandomId(1000, 10000); // ID FOR Borrowers
        }while(isIdExists(this.Book_ID));


    }
    // To display information and to set them that will help us in GUI

    // overloading constructor
    public Book (String bookID ,String name , String author , double price_buying ,int stock , String category , String publication_year, String book_image){
        this.Book_ID = bookID;
        this.Book_Title = name;
        this.Author = author;
        this.price_buying = price_buying;
        this.stock = stock;
        if (stock > 0)
            this.Status = true;
        else
            this.Status = false;
        this.category = category;
        this.publication_year = publication_year;

        reviews = new ArrayList<Review>();
        this.book_image = book_image;
        calcRating();
    }


    public void setBook_ID(String book_ID) {
        Book_ID = book_ID;
    }

    public String getBook_ID(){
        return this.Book_ID;
    }

    public String getBook_Title() {
        return Book_Title;
    }

    public void setBook_Title(String book_Title) {
        this.Book_Title = book_Title;
    }

    public String getAuthor() {
        return this.Author;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public double getPriceBuying() {
        return this.price_buying;
    }

    /*public double getPrice_buying() { //just because javafx view table depends on netbeans naming convesions cuz it depends on accessors for the function to get data from
        return this.price_buying;
    }*/

    public void setPriceBuying(double price) {
        this.price_buying = price;
    }

    public double getPriceBorrowing() {
        return this.price_borrowing;
    }

    public void setPriceBorrowing(LocalDate order_date , LocalDate due_date){
        long difference = ChronoUnit.DAYS.between(order_date,due_date); // get the difference between dates in days
        int initial = 10;
        this.price_borrowing = difference * initial;
    }

    //overloading , we will change type of parameters
    public void setPriceBorrowing(String order_date , String due_date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate orderDate = LocalDate.parse(order_date,formatter);
        LocalDate dueDate = LocalDate.parse(due_date,formatter);
        long difference = ChronoUnit.DAYS.between(orderDate,dueDate); // get the difference between dates in days
        int initial = 10;
        this.price_borrowing = difference * initial;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        if (stock > 0 ){
            this.setStatus(true);
        } else{
            this.setStatus(false);
        }
    }

    public String getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getStatus(){
        if (stock > 0){
            return true;
        }
        else{
            return false;
        }

        /*return this.Status;*/
    }

    public void setStatus(boolean status) {
        this.Status = status;
    }

    /*public static void getAllAvailableBooks(JFrame frame_books){
        for (Book bookss: Library.books) {
            System.out.println(bookss.Book_Title);
        }
    }*/


    // override the function toString() to be able to print the object in good way
    // organize them as the trteb of parameters in constructor 3shan tshl fy reading w enta bt7ot values fy constructor
    public String toString(){
        return this.Book_ID + "," + this.Book_Title + "," + this.Author + "," + this.price_buying + "," + this.stock + "," + this.category + "," + this.publication_year + "," + this.book_image ;
    }

    public void displayReviewsForBook() {


        for (Review review : this.reviews) {

            System.out.println("Rating: " + review.getRating());
            System.out.println("Comment: " + review.getComment());
            System.out.println("By: " + review.getCustomer().getName());
            System.out.println("------");

        }
    }

    protected String getRandomId(int lower_bound , int upper_bound){
        Random random_no = new Random();
        return "#" + random_no.nextInt(upper_bound - lower_bound + 1) + lower_bound;

    }
    protected boolean isIdExists(String id){
        for (Book book : Library.books){
            if (book.getBook_ID().equals(id)){
                return true;
            }

        }
        return false;

    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void calcRating(){
        int counter = 0;
        double rating = 0.0;
        for (Review review : reviews){
            rating += review.getRating();
            counter++;
        }
        try {
            this.rating = rating / counter;
        }
        catch (ArithmeticException e){
            this.rating = 0;
        }
    }


    public ArrayList<String> getCustomerIdsReviewBook(){
        ArrayList<String> customerIds = new ArrayList<String>();
        for (Review review : reviews){
            customerIds.add(review.getCustomerID());
        }
        return customerIds;
    }

    /*public boolean customerReviewedBefore(String bookID){
        for (Book book : Library.books){
            if (book)
        }

    }*/

    /*
    private double calcPriceBorrowing(Date order_Date, Date due_date){
        int iniial = 10;
        int sub;
        return sub *10;
    }*/





}
