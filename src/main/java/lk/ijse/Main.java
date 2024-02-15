package lk.ijse;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Book where public_year > 2010");
        List<Book> bookList = query.list();

        for (Book book : bookList){
            System.out.println(book.getBookId()+", "+book.getTitle()+", "+book.getPublic_year()+", "+book.getPrice()+", "
                    +book.getAuthor());
        }

        Query query1 = session.createQuery("update Book b set b.price = b.price*1.1 where b.author.AuthorId = ?1");
        query1.setParameter(1, 1);
        int update = query1.executeUpdate();
        System.out.println(update);

        delete(3);

        Query query2 = session.createQuery("select avg(price) from Book");
        double avgPrice = (double) query2.uniqueResult();
        System.out.println(avgPrice);


        Query query3 = session.createQuery("select a.Name, count(b.bookId) from Book b join Author a on " +
                "b.author.AuthorId = a.AuthorId group by b.author.AuthorId");
        List<Object[]> list = query3.list();

        for (Object[] arr : list) {
            System.out.println(Arrays.toString(arr));
        }

        Query query4 = session.createQuery("from Book b where b.author.Country = ?1");
        query4.setParameter(1, "Sri Lanka");
        List<Book> bookList1 = query4.list();

        for (Book book : bookList1) {
            System.out.println(book.getBookId()+", "+book.getTitle()+", "+book.getPublic_year()+", "+book.getPrice()+", "
                    +book.getAuthor());
        }

        Query query5 = session.createQuery("select a.Name from Book b join Author a on b.author.AuthorId = a.AuthorId group by " +
                "b.author.AuthorId having count(b.bookId) > (select avg(sub.avgBookCount) from (select count(b1.bookId) as avgBookCount from " +
                "Book b1 group by b1.author.AuthorId) as sub)");
        List<String> nameList = query5.list();

        for (String name : nameList) {
            System.out.println(name);
        }


        transaction.commit();
        session.close();
    }

    public static void delete(int Id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from Book b where b.author.AuthorId = ?1");
        query.setParameter(1, Id);
        int deleteBooks = query.executeUpdate();
        System.out.println(deleteBooks);


        Query query1 = session.createQuery("delete from Author where AuthorId = ?1");
        query1.setParameter(1, Id);
        int deleteAuthor = query1.executeUpdate();
        System.out.println(deleteAuthor);

        transaction.commit();
        session.close();
    }
}

