package System;

import System.*;
import Orders.*;
import Accounts.*;
import Book.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Notification {

    Borrower borrowerLoggedIn = Library.logged_in_borrower();
    // calling when each borrower login
    public String DueDate() {
        String msg="";
        for(Transaction transaction:borrowerLoggedIn.transactionsHistory){
            for(LocalDate date:transaction.getReturnDate()){
                if (LocalDate.now().isAfter(date)){
                    msg += "\nDue date is her.\nDear: " + borrowerLoggedIn.getName() +"\nfor books:[ " + transaction.getAllBooks() + " ]. Due date: " + date+"\nKindly Return them.";
                }
            }
        }
        return msg;
    }

    // calling when each borrower login
    public String PickUp() {
        String msg="";
        ArrayList<String> reservedBooks = borrowerLoggedIn.getReserveBook();
        for (Book book : Library.books) {
            for (String res_book : reservedBooks) {
                if(book.getBook_ID().equals(res_book))
                    if(book.getStock()> 0)
                        msg += "\nDear: " + borrowerLoggedIn.getName() + "\nThis book is available to be Picked Up: " + book.getBook_Title();
            }
        }
        return msg;
    }




}