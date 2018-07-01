package ua.mk.par.elibrary.repository;

import javax.persistence.EntityManager;

import ua.mk.par.elibrary.entity.Role;
import ua.mk.par.elibrary.entity.User;

public class Constants {

    private static Role allUserRole;
    private static Role adminRole;
    private static User adminUser;

    public static void initAllConstants(EntityManager em) {
        if (allUserRole == null) {
            allUserRole = em.find(Role.class, 1L);
            adminRole = em.find(Role.class, 2L);
            adminUser = em.find(User.class, 1L);
        }
    }

    public static Role getAllUserRole() {
        return allUserRole;
    }

    public static Role getAdminRole() {
        return adminRole;
    }

    public static User getAdminUser() {
        return adminUser;
    }
}
