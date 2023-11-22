package org.example.DataBase;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.entities.IpInfo;
import org.example.persistence.CustomPesistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class DBConnection {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    Map<String,String> props;

    public DBConnection() {

        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql" , "true");
        props.put("hibernate.hbm2ddl.auto" , "none");

        entityManagerFactory = new HibernatePersistenceProvider().
                createContainerEntityManagerFactory(new CustomPesistenceUnitInfo("pu_name"),props);

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    public void Add(IpInfo ipInfo)
    {
        ipInfo = entityManager.find(IpInfo.class , ipInfo.getIp());
        entityManager.persist(ipInfo);
    }

    public void QueryAll()
    {
        String jpql = "SELECT i FROM IpInfo i";
        TypedQuery<IpInfo> q = entityManager.createQuery( jpql , IpInfo.class);
        System.out.println("ip info from DB");
        q.getResultList().forEach(System.out::println);
    }

    public void Close()
    {
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    
}








