import com.dgut.ssm.bean.*;
import com.dgut.ssm.dao.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    private SqlSession session;
    private ConfigurableApplicationContext context;

    @BeforeEach
    public void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSessionFactory factory = context.getBean("sqlSessionFactory", SqlSessionFactory.class);
        session = factory.openSession();
    }

    @AfterEach
    public void close() {
        context.close();
    }
    @Test
    void testAll() {
        try {
            System.out.println(session);
            UserDao goodsDao = session.getMapper(UserDao.class);
            User aa = goodsDao.getUserByName("hxh");
            System.out.println(aa);
        } finally {
            session.close();
        }
    }
    @Test
    void  testUser(){
        GoodsDao goodsDao=session.getMapper(GoodsDao.class);
        List<Goods> allGoods = goodsDao.getAllGoods();
        for (Goods good : allGoods) {
            System.out.println(good);
        }
        session.close();
    }
    @Test
    void testQueryById(){
        GoodsDao goodsDao=session.getMapper(GoodsDao.class);
        Goods goodsById = goodsDao.getGoodsById(1);
        System.out.println(goodsById);
        session.close();
    }
    @Test
    void Update(){
        GoodsDao goodsDao=session.getMapper(GoodsDao.class);
        Goods goods = new Goods(1, "water", 1.3, 20);
        goodsDao.updateGoods(goods);
        session.close();
    }
    @Test
    void Del(){
        GoodsDao goodsDao=session.getMapper(GoodsDao.class);
        goodsDao.deleteGoods(3);
        session.close();
    }
    @Test
    void add(){
        GoodsDao goodsDao=session.getMapper(GoodsDao.class);
        Goods goods = new Goods(null, "a", 31.1, 22);
        goodsDao.addGoods(goods);
        session.close();
    }
    @Test
    void query(){
        OrderDao mapper = session.getMapper(OrderDao.class);
        List<Orders> orderByContractId = mapper.getOrderByContractId(1);
        for (Orders orders : orderByContractId) {
            System.out.println(orders);
        }
        session.close();
    }
    @Test
    void TestHard(){
        ContractDao mapper = session.getMapper(ContractDao.class);
        List<Contract> allContract = mapper.getAllContract();
        for (Contract contract : allContract) {
            System.out.println(contract);
        }
        session.close();
    }
    @Test
    void testQgoods(){
        GoodsDao mapper = session.getMapper(GoodsDao.class);
        List<Goods> goodsByContractId = mapper.getGoodsByOrderId(2);
        for (Goods goods : goodsByContractId) {
            System.out.println(goods);
        }
        session.close();
    }

}
