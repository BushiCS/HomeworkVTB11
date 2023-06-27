package org.example.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestMain {
    public static void main(String[] args) {


            SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Reader.class)
                .addAnnotatedClass(Catalog.class)
                .buildSessionFactory();
// CRUD
//
// CREATE
//        session = factory.getCurrentSession();
//        Catalog catalog = new Catalog("Fantasy#10");
//        session.beginTransaction();
//        session.persist(catalog);
//        session.getTransaction().commit();
// READ
//        session = factory.getCurrentSession();
//        session.beginTransaction();
//        Catalog catalog2 = session.get(Catalog.class, 2L);
//        session.getTransaction().commit();
//        System.out.println(catalog2);

// UPDATE
//        session = factory.getCurrentSession();
//        session.beginTransaction();
//        Catalog catalog2 = session.get(Catalog.class, 2L);
//        // session.detach(catalog2); // отвязывает объект от hibernate
//        // session.remove(catalog2); // удаляет объект из БД
//        // session.refresh(catalog2); // обновляет состояние объекта
//        // session.merge(catalog2); // возвращает удаленный объект
//        catalog2.setTitle("Fantasy#10");
//        session.getTransaction().commit();
//        System.out.println(catalog2);

// DELETE
//        session = factory.getCurrentSession();
//        session.beginTransaction();
//        Catalog catalog2 = session.get(Catalog.class,2L);
//        session.remove(catalog2);
//        session.getTransaction().commit();
//        System.out.println(catalog2);

//  List
//        List<Book> books = session.createQuery("from Book", Book.class).getResultList(); // Вытаскиваем список всех книг
//        // from Book b where b.title = 'Harry Potter' or b.authorName = 'Rowling'
//        // from Book b where b.title LIKE 'Harry%'
//        // from Book b where b.title = :title
//        // List<Book> books = session.createQuery("from Book b where b.title = :title", Book.class).setParameter("title","Java 1").getResultList(); // маска
//        System.out.println(books);
//        session.getTransaction().commit();

//  QUERIES
//        session = factory.getCurrentSession();
//        session.beginTransaction();
//        session.createQuery("update Book set title = 'A'", Book.class).executeUpdate();
//        session.createQuery("delete from Book where id = 3", Book.class).executeUpdate();
//        session.getTransaction().commit();
//  Отдаем в books_readers книгу и читателя
//        session = factory.getCurrentSession();
//        session.beginTransaction();
//        Reader reader = session.get(Reader.class, 1);
//        Book book = session.get(Book.class, 1);
//        reader.getBooks().add(book);
//        reader.getBooks().clear(); // почистить список книг читателя
//        session.getTransaction().commit();
    }
}
