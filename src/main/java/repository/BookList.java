package repository;

import entity.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class BookList {
    ArrayList<Book> booksList;

    public BookList(ArrayList<Book> booksList) {
        this.booksList = booksList;
    }

    public ArrayList<Book> all(){
        return booksList;
    }

    public BookList() {
        this.booksList = new ArrayList<Book>();
    }
    private int comparatorName(Book b1,Book b2,String value){
        String a1=b1.getName();
        String a2=b2.getName();
        return sortBySubstringCount(value, a1, a2);
    }
    private int comparatorAuthorName(Book b1,Book b2,String value){
        String a1=b1.getAuthorName();
        String a2=b2.getAuthorName();
        return sortBySubstringCount(value, a1, a2);
    }
    private int comparatorIsbn(Book b1,Book b2,String value) {
        String a1=b1.getIsbn();
        String a2=b2.getIsbn();
        return sortBySubstringCount(value, a1, a2);
    }

    private int sortBySubstringCount(String value, String a1, String a2) {
        int v1=(a1.length()-a1.replace(value,"").length())/value.length();
        int v2=(a2.length()-a2.replace(value,"").length())/value.length();
        if (v1>v2) return -1;
        else if (v1<v2) return 1;
        else return 0;
    }

    private int comparatorAnnotation(Book b1,Book b2,String value) {
        String a1=b1.getAnnotation();
        String a2=b2.getAnnotation();
        return sortBySubstringCount(value, a1, a2);
    }

    public BookList findBy(String by,String value) throws Exception {
        ArrayList<Book> sortedBooksList= (ArrayList<Book>)this.booksList.clone();

        if (by.equals("name")){
            sortedBooksList.sort((b1, b2) -> this.comparatorName(b1,b2,value));
            sortedBooksList = new ArrayList<Book>(
                sortedBooksList.stream().filter(
                    a -> (a.getName().length() - a.getName().replace(value,"").length()) > 0
                ).collect(Collectors.toList())
            );

        } else if (by.equals("isbn")){
            sortedBooksList.sort((b1, b2) -> this.comparatorIsbn(b1,b2,value));
            sortedBooksList = new ArrayList<Book>(
                sortedBooksList.stream().filter(
                    a -> (a.getIsbn().length() - a.getIsbn().replace(value,"").length()) > 0
                ).collect(Collectors.toList())
            );

        } else if (by.equals("authorName")){
            sortedBooksList.sort((b1, b2) -> this.comparatorAuthorName(b1,b2,value));
            sortedBooksList = new ArrayList<Book>(
                sortedBooksList.stream().filter(
                    a -> (a.getAuthorName().length() - a.getAuthorName().replace(value,"").length()) > 0
                ).collect(Collectors.toList())
            );

        } else if (by.equals("annotation")){
            sortedBooksList.sort((b1, b2) -> this.comparatorAnnotation(b1,b2,value));
            sortedBooksList = new ArrayList<Book>(
                sortedBooksList.stream().filter(
                    a -> (a.getAnnotation().length() - a.getAnnotation().replace(value,"").length()) > 0
                ).collect(Collectors.toList())
            );

        } else throw new Exception("Wrong by parameter");


        return new BookList(sortedBooksList);
        }



    public void addBook(Book book){
        booksList.add(book);
    }


    @Override
    public String toString() {
        String output="";
        for (Book b: booksList){
            output+="Name: "+b.getName()+
                    "\nAuthor: "+b.getAuthorName()+
                    "\nGenre: "+b.getGenre()+
                    "\nDate: "+b.getPublishDate()+
                    "\nISBN: "+b.getIsbn()+"\n\n";

        }
        return output;
    }
}
