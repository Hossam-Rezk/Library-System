package Orders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import System.*;
import Orders.*;
import Accounts.*;
import Book.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
// i want to limit how many copies the user and borrower will buy
// i wanna limit the no of days that the borrower will borrow the book
public class BorrowerCart extends ShoppingCart{
    ArrayList <LocalDate> returnDates = new ArrayList<LocalDate>();
     // calc setPricing for borrowing
    public boolean addToCart(Book book, int quantity, LocalDate returnDate) {
        CartItem item = findItemInCart(book);
        if (item != null) { // if book found in shopping cart just add quantity
            if (item.getBook().getStock() >= quantity && quantity <=5){
                item.getBook().setStock(item.getBook().getStock() - quantity);
                if (item.getBook().getStock() <=0){
                    item.getBook().setStatus(false);

                }
            }
            item.setQuantity(quantity);// lw eshtret elktab fy cart w 3ayz tzwd kmya bs
            Library.updateBook(item.getBook(),Library.BookIndex(book));
            return true;
        } else { // if book not found in shopping cart
            Book tempBook = book;
            if (book.getStock() >= quantity && quantity <=5){
                book.setStock(book.getStock() - quantity);
                if (book.getStock() <=0){
                    book.setStatus(false);
                }
                CartItem cartitem = new CartItem (book,quantity);
                cartitem.getBook().setPriceBorrowing(LocalDate.now(),returnDate);
                Library.updateBook(cartitem.getBook(),Library.BookIndex(tempBook));
                cartItems.add(cartitem);
                returnDates.add(returnDate);
                return true;
                //calculate the price borrowing
                //cartItems.add(new CartItem(book, quantity));
            }





        }
        return false;
    }

    public boolean updateDueDate(Book book, LocalDate newDueDate) {
        // max due date = 40 days
        CartItem item = findItemInCart(book);
        if (item != null) {
            if (ChronoUnit.DAYS.between(LocalDate.now(),newDueDate) > 40){
                return false;
            }
            int index = cartItems.indexOf(item);
            returnDates.set(index,newDueDate);

            item.getBook().setPriceBorrowing(LocalDate.now(),newDueDate);
            // int index of old book details = Library.BookIndex(book)
            // new version of book .getBook() will be updated by details
            Library.updateBook(item.getBook(),Library.BookIndex(book));

            return true;


        }
        return false;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        double discount = applyDiscount();

        for (CartItem item : cartItems) {
            double itemPrice = item.getBook().getPriceBorrowing() * item.getQuantity();
            double discountedItemPrice = itemPrice - (itemPrice * discount);
            totalPrice += discountedItemPrice;
        }
        return totalPrice + deliveryServicePrice;
    }

    public double calculatepriceBeforeDiscount() {
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getBook().getPriceBorrowing() * item.getQuantity();
        }
        return totalPrice;
    }


    public void completePurchase(){

        Borrower borrowerLoggedIn = Library.logged_in_borrower();
        Transaction transaction = new Transaction(getAllBooksInCart(),getAllQuantitiesOfBooksInCart(), LocalDate.now() ,returnDates);
        if (borrowerLoggedIn != null){
            borrowerLoggedIn.transactionsHistory.add(transaction);
            ArrayList<String> reservedBooks = borrowerLoggedIn.getReserveBook();
            ArrayList<Book> CartBooks=getAllBooksInCart();
            for (String res_book : reservedBooks) {
                for(Book book :CartBooks){
                    if(book.getBook_ID().equals(res_book))
                        borrowerLoggedIn.removeReserveBook(res_book);
                }
            }
        }

    }



}
