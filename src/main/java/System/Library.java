package System;


import Accounts.*;
import Book.Book;
import Orders.*;

import java.util.ArrayList;

public abstract class Library {
    public static ArrayList<Book> books = new ArrayList<Book>();
    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static ArrayList<Borrower> borrowers = new ArrayList<Borrower>();

    public static ArrayList<Admin> admins = new ArrayList<Admin>();



    public static boolean loginCustomer(String email, String password){
        //boolean valid_user = false;
        for (Customer customer : customers){
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)){
                //valid_user = true;
                customer.setLoggedIn(true);
                return true;

            }
        }
        return false;

    } // in event handling gui after he presses submit button and we call that function and it returns true fa n2dr nnklh l elsaf7a eltanya

    public static Customer logged_in_customer(){
        //boolean isCustomerLoggedin = false;
        for (Customer customer : customers){
            if(customer.getIsLoggedIn()){
                //isCustomerLoggedin = true;
                return customer;
            }
        }
        return null;
    }

    public static void logOutCustomer(){ // ask elmo3ed hoa hykon feh multiple users logged in or only one user

        //cust.setLoggedIn(false);

        Customer customer = logged_in_customer(); // that only logsout the first customer logged in if we have many users logged in
        customers.get(customers.indexOf(customer)).setLoggedIn(false);

        /*for (Accounts.Customer customer : customers){
            if(customer.getIsLoggedIn()){
                customers.get(customers.indexOf(customer)).setLoggedIn(false);
            }
        }*/

    }

    /*private static boolean checkUniquenessOfEmailCustomer(String Email){
        //boolean email_Found = false;
        for (Customer customer : customers){
            if (customer.getEmail().equals(Email)){
                //email_Found = true;
                return false;
            }
        }
        return true;
    }*/

    private static boolean checkPasswordLength(String password){
        if (password.length() > 8){
            return true;
        }
        return false;

    }
    // Notice the encapsulation when the methods its access modifier is private
    private static boolean checkEmailExistBefore (String email){
        boolean email_found = false;
        for (Customer customer : customers){
            if (customer.getEmail().equals(email)){

                email_found = true;
            }
        }

        for (Borrower borrower : borrowers){
            if (borrower.getEmail().equals(email)){
                email_found = true;
            }
        }

        for (Admin admin : admins){
            if (admin.getEmail().equals(email)){
                email_found = true;
            }
        }

        if (email_found){
            return false;
        }
        return true; // if the email is not found , It is not logically right but I will use it in the below functions

    }

    private static boolean checkEmailFormat(String email){
        boolean atmark_found= false;
        boolean dot_found = false;

        // to loop inside the array and find . and @ , to get element in array we use charAt() , String is char array
        for (int i = 0 ; i < email.length();i++){
            if (email.charAt(i) == '@'){
                atmark_found = true;
            }
            if (email.charAt(i) == '.'){
                dot_found = true;
            }
        }

        if (atmark_found && dot_found){
            return true;
        }
        return false;
    }


    public static boolean checkEmailAndPassword (String email , String password){ // I make it public cuz I need to use it in different class but the correct to be private
        if (checkEmailExistBefore(email) && checkEmailFormat(email) && checkPasswordLength(password)){
            return true;
        }
        return false;
    }

    public static ArrayList<String> getAllBooksNames(){
        ArrayList<String> booksNames = new ArrayList<String>();
        for (Book book: Library.books){
            booksNames.add(book.getBook_Title());
        }
        return booksNames;
    }

    public static ArrayList<String> getAllAuthorsNames(){
        ArrayList<String> authorsNames = new ArrayList<String>();
        for (Book book: Library.books){
            authorsNames.add(book.getAuthor());
        }
        return authorsNames;
    }



    /*public static boolean registerCustomer(Customer acc){

        if (checkUniquenessOfEmailCustomer(acc.getEmail()) && checkPasswordLength(acc.getPassword())){
            customers.add(acc);
            return true;
        }
        return false;


    } // if that returns true , that means it will be added into the array of customers , and we must pass in the parameters object of customer
    */

    public static boolean registerCustomer(String name , String email , String password){
        if (checkEmailAndPassword(email,password)){
            Customer customer = new Customer(name,email,password);
            customers.add(customer);
            return true;
        }
        return false;
    }
    // ---------------------------------------------------------------------------
    // for borrower
    public static boolean loginBorrower(String email, String password){
        for (Borrower borrower : borrowers){
            if (borrower.getEmail().equals(email) && borrower.getPassword().equals(password)){
                borrower.setLoggedIn(true);
                return true;
            }
        }
        return false;
    }

    public static Borrower logged_in_borrower(){
        for (Borrower borrower : borrowers){
            if(borrower.getIsLoggedIn()){
                return borrower;
            }
        }
        return null;
    }

    public static void logOutBorrower(){ // ask elmo3ed hoa hykon feh multiple users logged in or only one user
        Borrower borrower = logged_in_borrower(); // that only logsout the first customer logged in if we have many users logged in
        borrowers.get(borrowers.indexOf(borrower)).setLoggedIn(false);
    }

    /*public static boolean registerBorrower(Borrower acc){
        if (checkUniquenessOfEmailCustomer(acc.getEmail()) && checkPasswordLength(acc.getPassword())){
            borrowers.add(acc);
            return true;
        }
        return false;
    }*/

    public static boolean registerBorrower(String name , String email , String password){
        if (checkEmailAndPassword(email,password)){
            Borrower borrower = new Borrower(name,email,password);
            borrowers.add(borrower);
            return true;
        }
        return false;
    }
    //-------------------------------------------------------------------
    // Admin
    public static boolean loginAdmin(String email, String password){
        for (Admin admin : admins){
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)){
                admin.setLoggedIn(true);
                return true;
            }
        }
        return false;
    }

    public static Admin logged_in_admin(){
        for (Admin admin : admins){
            if(admin.getIsLoggedIn()){
                return admin;
            }
        }
        return null;
    }

    public static void logOutAdmin(){ // ask elmo3ed hoa hykon feh multiple users logged in or only one user
        Admin admin = logged_in_admin(); // that only logsout the first customer logged in if we have many users logged in
        admins.get(admins.indexOf(admin)).setLoggedIn(false);
    }

    private static boolean checkForSecretKey(String secretKey){
        if (secretKey.equals(Admin.getSecretCode())){
            return true;
        }
        return false;
    }
    /*public static boolean registerAdmin(Admin acc,String secretKey){
        if (checkForSecretKey(secretKey)){
            if (checkUniquenessOfEmailCustomer(acc.getEmail()) && checkPasswordLength(acc.getPassword())){
                admins.add(acc);
                return true;
            }
        }
        return false;
    }*/

    public static boolean registerAdmin(String name , String email , String password,String secretKey){
        if (secretKey.equals(Admin.getSecretCode())){
            if (checkEmailAndPassword(email,password)){
                Admin admin = new Admin(name,email,password);
                admins.add(admin);
                return true;
            }
        }
        return false;
    }




    public static int BookIndex(Book book){
        return books.indexOf(book);
    }

    public static void updateBook(Book book,int index){
        books.set(index,book);
    }

    public static boolean checkBookId(String BookId){
        for (Book book : Library.books){
            if (book.getBook_ID().equals(BookId)){
                return true; // it will not complete the loop if it find id cuz return statement
            }


        }
        return false;
    }








}
