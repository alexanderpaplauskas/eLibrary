package ua.mk.par.elibrary.utils;

import ua.mk.par.elibrary.entity.*;
import ua.mk.par.elibrary.service.role.*;

public class FillBase {

    static void testSaveUser() throws Exception {
        /*System.out.println("uuuuuser-------------------------------------------------------------");
        DefaultUserService serviceUser = new DefaultUserService();
        DefaultRoleService serviceRole = new DefaultRoleService();
        User u1 = new User();
        u1.setName("wwwwww111112");
        User u2 = new User();
        u2.setName("wwwwww222222");

        Role r1 = new Role();
        r1.setName("rrrw12");
        r1 = serviceRole.add(r1);

        Role r2 = new Role();
        r2.setName("rrrw22");
        r2 = serviceRole.add(r2);

        Role r3 = new Role();
        r3.setName("rrrw32");
        r3 = serviceRole.add(r3);

        u1.getRoles().add(r1);
        u1.getRoles().add(r2);
        u1.getRoles().add(r3);

        u2.getRoles().add(r2);

        User u = serviceUser.add(u1);
        System.out.println("user1: "+u);
        User u222 = serviceUser.add(u2);
        System.out.println("user2: "+u222); */
    }

    static void testSaveBook() throws Exception {
        /*Book b1 = new Book();
        b1.setName("bbbbb52");
        Book b2 = new Book();
        b2.setName("bbbbb53");

        Permition permition = new Permition();
        permition.setName("perm1");
        permition = servicePerm.add(permition);

        Author author = new Author();
        author.setFirstName("FNqqqqqqqq1");
        author.setLastName("LNddddddddd1");
        author = serviceAuthor.add(author);

        Author author2 = new Author();
        author2.setFirstName("FNqqqqqqqq2");
        author2.setLastName("LNddddddddd2");
        author2 = serviceAuthor.add(author2);

        Category category = new Category();
        category.setName("Categ1");
        category.getPermitions().add(permition);
        category = serviceCateg.add(category);

        Category category2 = new Category();
        category2.setName("Categ2");
        category2.getPermitions().add(permition);
        category2 = serviceCateg.add(category2);

        Publisher publisher = new Publisher();
        publisher.setName("publ1");
        publisher = servicePub.add(publisher);

        b1.setPublisher(publisher);
        b1.getAutors().add(author);  b1.getAutors().add(author2);
        b1.getCategories().add(category);
        b1.getCategories().add(category2);

        b2.setPublisher(publisher);
        b2.getAutors().add(author);
        b2.getCategories().add(category2);

        b1 = serviceBook.add(b1);
        ServiceEntityManager.em.refresh(b1);
        b2 = serviceBook.add(b2);
        ServiceEntityManager.em.refresh(b2);

        System.out.println("Book1: "+b1+"   authors: "+b1.getAutors());
        System.out.println("Book2: "+b2+"   authors: "+b2.getAutors());

        Author author11 = serviceAuthor.get(author.getId());
        Author author22 = serviceAuthor.get(author2.getId());
        System.out.println("Author1: "+author11+"   books: "+author11.getBooks());
        System.out.println("Author2: "+author22+"   books: "+author22.getBooks());

        Book b11 = serviceBook.get(b1.getId());
        System.out.println("---------Book1: "+b11+"   authors: "+b11.getAutors()); */
    }
}
