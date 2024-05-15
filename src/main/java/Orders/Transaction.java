package Orders;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import System.*;
import Orders.*;
import Accounts.*;
import Book.*;

/*
Date class is outdated
LocalDate is new



if you have normal array and you want add its items in arraylist
mynf3sh t3mlhm equal bb3d

lazm t3ml loop 3la array el3ady b3d kda t3ml lkol kema add fy array list




*/
public class Transaction implements IdGenerator {

    private String transactionId;
    private ArrayList<String> booksID;
    private ArrayList<Integer> booksQuantity;

    private ArrayList<LocalDate> returnDate;
    private double totalPrice;
    private LocalDate borrowDate;


    public Transaction(ArrayList<Book> book, ArrayList<Integer> quantity, LocalDate borrowDate , ArrayList<LocalDate> returnDate) {

        /*if (transactionId == null){
            //this.transactionId = getRandomID();

        }else{
            this.transactionId = transactionId;
        }*/
        do {
            this.transactionId = getRandomId(1000, 10000); // ID FOR Borrowers
        }while(isIdExists(this.transactionId));

        totalPrice = 0;
        this.booksID = new ArrayList<String>();
        this.booksQuantity = new ArrayList<Integer>();
        this.returnDate = new ArrayList<LocalDate>();


        this.booksQuantity = quantity;
        this.returnDate = returnDate;

        for(int i=0;i<book.size();i++) // length of passed array that will come from shooping cart
        {
            booksID.add(book.get(i).getBook_ID());
            //booksQuantity.add(quantity.get(i));
            //this.returnDate.add(returnDate.get(i));
            totalPrice += book.get(i).getPriceBorrowing()*quantity.get(i);
        }


        this.borrowDate = borrowDate;


        //Borrower.addBorrowingHistory(this);
    }

    public Transaction(String transactionId , String bookID , int quantity , LocalDate returnDate, double totalprice, LocalDate transactionDate){
        this.transactionId = transactionId;
        this.booksID = new ArrayList<String>();
        this.booksQuantity = new ArrayList<Integer>();
        this.returnDate = new ArrayList<LocalDate>();
        this.borrowDate = transactionDate;

        booksID.add(bookID);
        booksQuantity.add(quantity);
        this.returnDate.add(returnDate);

        this.totalPrice = totalprice;


    }

    public ArrayList<String> getBooksID(){return booksID;}




    // dh hydef ktab fy nfs order mstkhdmenh fy file handling
    public void addBook(String bookID, int quantity, LocalDate returndate){
        booksID.add(bookID);
        booksQuantity.add(quantity);
        returnDate.add(returndate);
    }




    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }



    public ArrayList<Integer> getQuantity() {
        return booksQuantity;
    }

    public void setQuantity(ArrayList<Integer> quantity) {
        this.booksQuantity = quantity;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double price) {
        totalPrice = price;
    }

    public LocalDate getBorrowDate() {
        return this.borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public ArrayList<LocalDate> getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(ArrayList<LocalDate> returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook (String bookID){ //mn hna t2dr tgeb title w kol byanat elktab
        for (Book book : Library.books){
            if (book.getBook_ID().equals(bookID)){
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> allBooks = new ArrayList<Book>();
        for (String bookid : booksID){
            for (Book book : Library.books){
                if (book.getBook_ID().equals(bookid)){
                    allBooks.add(book);
                }
            }
        }
        return allBooks;
    }

    public boolean isIdExists(String id){
        for (Borrower borrower : Library.borrowers){
            for (Transaction transaction : borrower.getTransactionsHistory()){
                if (transaction.getTransactionId().equals(id)){
                    return true;
                }
            }

        }
        return false;

    }





/*
    public String[] getBookTitle(){
        String[] bookNames = new String[book.length];
        for (int i = 0; i < book.length; i++) {
            bookNames[i] = book[i].getBook_Title();
        }

        return bookNames;
    }

    public String[] getBookID(){
        String[] bookIds = new String[book.length];
        for (int i = 0; i < book.length; i++) {
            bookIds[i] = book[i].getBook_ID();
        }

        return bookIds;
    }*/

}

