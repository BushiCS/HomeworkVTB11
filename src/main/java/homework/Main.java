package homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Buyer.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Purchase.class)
                .buildSessionFactory();

        while (true) {
            Session session = factory.getCurrentSession();
            System.out.println("Выберите команду или введите end");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            if (input.equals("end")) {
                break;
            }
            List<Buyer> resultList;
            List<Product> productList;
            switch (parts[0]) {
                case "/buy":
                    session.beginTransaction();
                    Buyer buyer = session
                            .createQuery("from Buyer where title = :name", Buyer.class)
                            .setParameter("name", parts[1])
                            .getSingleResult();
                    Product product = session
                            .createQuery("from Product where title = :name", Product.class)
                            .setParameter("name", parts[2])
                            .getSingleResult();
                    //buyer.getProducts().add(product); // для ManyToMany
                    session.persist(new Purchase(buyer, product, product.getPrice()));
                    session.getTransaction().commit();
                    break;
                case "/showProductsByPerson":
                    session.beginTransaction();
                    resultList = session
                            .createQuery("from Buyer where title = :name", Buyer.class)
                            .setParameter("name", parts[1])
                            .getResultList();
                    for (Buyer b : resultList) {
                        System.out.println("Покупатель: " + b.getTitle());
                        for (Purchase p : b.getPurchases()) {
                            System.out.println("продукт: " + p.getProduct().getTitle() + " | цена: " + p.getPrice());
                        }
                    }
                    session.getTransaction().commit();
                    break;
                case "/findPersonsByProductTitle":
                    session.beginTransaction();
                    productList = session
                            .createQuery("from Product where title = :name", Product.class)
                            .setParameter("name", parts[1])
                            .getResultList();
                    for (Product pr : productList) {
                        System.out.println("Продукт: " + pr.getTitle());
                        for (Purchase p : pr.getPurchases()) {
                            System.out.println("покупатель:  " + p.getBuyer().getTitle() + "| цена: " + p.getPrice());
                        }
                    }
                    session.getTransaction().commit();
                    break;
                case "/remove":
                    session.beginTransaction();
                    switch (parts[1]) {
                        case "product":
                            Product pr = session
                                    .createQuery("from Product where title = :name", Product.class)
                                    .setParameter("name", parts[2])
                                    .getSingleResult();
                            session.remove(pr);
                            break;
                        case "buyer":
                            Buyer b = session
                                    .createQuery("from Buyer where title = :name", Buyer.class)
                                    .setParameter("name", parts[2])
                                    .getSingleResult();
                            session.remove(b);
                            break;
                    }
                    session.getTransaction().commit();
                    break;
            }
        }
    }
}