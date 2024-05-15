package Orders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import System.*;
import Orders.*;
import Accounts.*;
import Book.*;
import Accounts.Customer;
import com.example.librarysystem.Starter;
import javafx.scene.control.Alert;

public class CustomerCart extends ShoppingCart{


    public void addToCart(Book book, int quantity) {
        CartItem item = findItemInCart(book);
        if (item != null) { // if book found in shopping cart just add quantity
            if (item.getBook().getStock() >= quantity && quantity <=5){
                item.getBook().setStock(item.getBook().getStock() - quantity);
                if (item.getBook().getStock() <=0){
                    item.getBook().setStatus(false);
                }
            }
            item.setQuantity(quantity);
            Library.updateBook(item.getBook(),Library.BookIndex(book));
        } else {
            Book tempBook = book;
            if (book.getStock() >= quantity && quantity <=5){
                book.setStock(book.getStock() - quantity);
                if (book.getStock() <=0){
                    book.setStatus(false);
                }
            }
            CartItem cartitem = new CartItem (book,quantity);
            Library.updateBook(cartitem.getBook(),Library.BookIndex(tempBook));
            cartItems.add(cartitem);// if book not found in shopping cart


        }
    }

    /*public void addToCart(Book book){
        CartItem item = findItemInCart(book);
        if (item != null) { // if book found in shopping cart just add quantity
            int newQuantity = item.getQuantity() +1;
            if (item.getBook().getStock() >= newQuantity && newQuantity <=5){
                item.getBook().setStock(item.getBook().getStock() - newQuantity);
                if (item.getBook().getStock() <=0){
                    item.getBook().setStatus(false);
                }
            }
            item.setQuantity(newQuantity); // ++Oldquantity
            Library.updateBook(item.getBook(),Library.BookIndex(book));
        } else {
            Book tempBook = book;
            int quantity = 1;
            if (book.getStock() >= quantity){
                book.setStock(book.getStock() - quantity);
                if (book.getStock() <=0){
                    book.setStatus(false);
                }
            }
            CartItem cartitem = new CartItem (book,quantity);
            Library.updateBook(cartitem.getBook(),Library.BookIndex(tempBook));
            cartItems.add(cartitem);// if book not found in shopping cart


        }
    }*/
    public void addToCart(Book book){
        CartItem item = findItemInCart(book);
        if (item != null) { // if book found in shopping cart just add quantity
            Starter.showAlert(Alert.AlertType.ERROR,"Error","You It's already in the cart",null);
            return;
        } else {
            Book tempBook = book;
            int quantity = 1;
            if (book.getStock() >= quantity){
                book.setStock(book.getStock() - quantity);
                if (book.getStock() <=0){
                    book.setStatus(false);
                }
            }
            CartItem cartitem = new CartItem (book,quantity);
            Library.updateBook(cartitem.getBook(),Library.BookIndex(tempBook));
            cartItems.add(cartitem);// if book not found in shopping cart


        }
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        double discount = applyDiscount();

        for (CartItem item : cartItems) {
            double itemPrice = item.getBook().getPriceBuying() * item.getQuantity();
            double discountedItemPrice = itemPrice - (itemPrice * discount);
            totalPrice += discountedItemPrice;
        }
        return totalPrice + deliveryServicePrice;
    }


    public double calculatepriceBeforeDiscount() {
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getBook().getPriceBuying() * item.getQuantity();
        }
        return totalPrice;
    }


    public void completePurchase(){
        Customer customerLoggedIn = Library.logged_in_customer();
        Order order = new Order(getAllBooksInCart(),getAllQuantitiesOfBooksInCart(), LocalDate.now());
        if (customerLoggedIn != null){
            customerLoggedIn.ordersHistory.add(order);
            clearCart();
        }

    }
}
